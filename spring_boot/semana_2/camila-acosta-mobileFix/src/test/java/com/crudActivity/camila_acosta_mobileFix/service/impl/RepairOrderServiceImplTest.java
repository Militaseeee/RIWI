package com.crudActivity.camila_acosta_mobileFix.service.impl;

import com.crudActivity.camila_acosta_mobileFix.exception.ConflictException;
import com.crudActivity.camila_acosta_mobileFix.exception.ForbiddenAccessException;
import com.crudActivity.camila_acosta_mobileFix.exception.ResourceNotFoundException;
import com.crudActivity.camila_acosta_mobileFix.model.*;
import com.crudActivity.camila_acosta_mobileFix.repository.DeviceRepository;
import com.crudActivity.camila_acosta_mobileFix.repository.RepairOrderRepository;
import com.crudActivity.camila_acosta_mobileFix.repository.UserRepository;

// Importaciones clave para JUnit 5 y Mockito
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Le decimos a JUnit 5 que use Mockito
@ExtendWith(MockitoExtension.class)
class RepairOrderServiceImplTest {

    // 1. Creamos "Mocks" (simuladores) para las dependencias
    @Mock
    private RepairOrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DeviceRepository deviceRepository;

    // 2. Inyectamos esos Mocks en la clase que queremos probar
    @InjectMocks
    private RepairOrderServiceImpl repairOrderService; // Esta es la clase real


    // --- Pruebas para el método deleteOrder (como pide el taller) ---

    @Test
    void testDeleteOrder_ByUser_Success_IfPending() {
        // 1. Preparación
        User customer = new User();
        customer.setId(100L);
        customer.setRole(Role.USER);

        RepairOrder order = new RepairOrder();
        order.setId(1L);
        order.setStatus(Status.PENDING);
        order.setCustomer(customer);

        // Fingimos la base de datos
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(userRepository.findById(100L)).thenReturn(Optional.of(customer));

        // 2. Actuación
        repairOrderService.deleteOrder(1L, 100L); // (orderId, actorId)

        // 3. Verificación
        assertEquals(Status.CANCELED, order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDeleteOrder_ByUser_Fails_IfNotOwner() {
        // 1. Preparación
        User owner = new User();
        owner.setId(100L);

        User otherUser = new User();
        otherUser.setId(101L);
        otherUser.setRole(Role.USER);

        RepairOrder order = new RepairOrder();
        order.setId(1L);
        order.setStatus(Status.PENDING);
        order.setCustomer(owner); // La orden es del 100

        // Fingimos la base de datos
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(userRepository.findById(101L)).thenReturn(Optional.of(otherUser)); // El actor es el 101

        // 2. y 3. Actuación y Verificación
        // Verificamos que SÍ lanza la excepción esperada
        assertThrows(ForbiddenAccessException.class, () -> {
            repairOrderService.deleteOrder(1L, 101L); // El 101 intenta borrar la orden del 100
        });

        // Verificamos que NUNCA se guardó (no se canceló)
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testDeleteOrder_ByUser_Fails_IfNotPending() {
        // 1. Preparación
        User customer = new User();
        customer.setId(100L);
        customer.setRole(Role.USER);

        RepairOrder order = new RepairOrder();
        order.setId(1L);
        order.setStatus(Status.IN_PROGRESS); // La orden ya está en progreso
        order.setCustomer(customer);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(userRepository.findById(100L)).thenReturn(Optional.of(customer));

        // 2. y 3. Actuación y Verificación
        assertThrows(ConflictException.class, () -> {
            repairOrderService.deleteOrder(1L, 100L);
        });

        verify(orderRepository, never()).save(any());
    }

    // --- Pruebas para el método createOrder ---

    @Test
    void testCreateOrder_Valid() {
        // --- 1. Given (Preparación) ---

        // Datos de entrada
        Long customerId = 100L;
        Long deviceId = 101L;
        String description = "La pantalla está rota";

        // Creamos el cliente que está creando la orden
        User customer = new User();
        customer.setId(customerId);
        customer.setRole(Role.USER);

        // Creamos el dispositivo que se va a reparar
        Device device = new Device();
        device.setId(deviceId);
        device.setBrand("Samsung");

        // Fingimos las respuestas de la base de datos
        when(userRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        // Le decimos a Mockito que CUALQUIER objeto RepairOrder que le pasemos para guardar,
        // nos lo devuelva tal cual.
        when(orderRepository.save(any(RepairOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- 2. When (Actuación) ---

        // Llamamos al método a probar
        RepairOrder nuevaOrden = repairOrderService.createOrder(deviceId, description, customerId);

        // --- 3. Then (Verificación) ---

        // Verificamos que la orden devuelta no sea nula
        assertNotNull(nuevaOrden);

        // Verificamos que el estado es PENDING (¡El requisito clave!)
        assertEquals(Status.PENDING, nuevaOrden.getStatus());

        // Verificamos que los datos coinciden
        assertEquals(customer, nuevaOrden.getCustomer());
        assertEquals(device, nuevaOrden.getDevice());
        assertEquals(description, nuevaOrden.getIssueDescription());

        // Verificamos que el método save() SÍ fue llamado
        verify(orderRepository, times(1)).save(any(RepairOrder.class));
    }

    // --- Prueba para createOrder (Inválido) ---

    @Test
    void testCreateOrder_Fails_IfDeviceNotFound() {
        // --- 1. Given (Preparación) ---

        // Datos de entrada
        Long customerId = 100L;
        Long nonExistentDeviceId = 999L; // Un ID de dispositivo que no existe
        String description = "La pantalla está rota";

        // Creamos el cliente (él sí existe)
        User customer = new User();
        customer.setId(customerId);
        customer.setRole(Role.USER);

        // Fingimos las respuestas de la base de datos
        when(userRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // ¡LA CLAVE! Le decimos a Mockito que el dispositivo NO fue encontrado
        when(deviceRepository.findById(nonExistentDeviceId)).thenReturn(Optional.empty());

        // --- 2. When & 3. Then (Actuar y Verificar) ---

        // Verificamos que se lanza la excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {

            // Cuando llamamos al método con el ID de dispositivo incorrecto
            repairOrderService.createOrder(nonExistentDeviceId, description, customerId);

        }); // El paréntesis de assertThrows cierra aquí

        // --- Verificación Extra ---

        // Verificamos que el método save() NUNCA fue llamado, porque falló antes
        verify(orderRepository, never()).save(any(RepairOrder.class));
    }
}