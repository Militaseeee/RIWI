package com.crudActivity.camila_acosta_mobileFix.controller;

import com.crudActivity.camila_acosta_mobileFix.dto.DeviceRequest;
import com.crudActivity.camila_acosta_mobileFix.model.Device;
import com.crudActivity.camila_acosta_mobileFix.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * Endpoint: GET /api/devices
     * Permisos: USER, TECH, ADMIN (Todos los autenticados)
     * Lista todos los dispositivos del catálogo.
     */
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.findAllDevices());
    }

    /**
     * Endpoint: GET /api/devices/{id}
     * Permisos: USER, TECH, ADMIN
     */
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findDeviceById(id)); // El servicio lanza 404 si no existe
    }

    /**
     * Endpoint: POST /api/devices
     * Permisos: ADMIN
     * Crea un nuevo dispositivo.
     */
    @PostMapping
    public ResponseEntity<Device> createDevice(@Valid @RequestBody DeviceRequest request) {
        // Convertimos el DTO a la entidad
        Device device = new Device();
        device.setBrand(request.brand());
        device.setModel(request.model());
        device.setSerialNumber(request.serialNumber());

        Device newDevice = deviceService.createDevice(device);

        URI location = URI.create("/api/devices/" + newDevice.getId());
        return ResponseEntity.created(location).body(newDevice);
    }

    /**
     * Endpoint: PUT /api/devices/{id}
     * Permisos: ADMIN
     * Actualiza un dispositivo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(
            @PathVariable Long id,
            @Valid @RequestBody DeviceRequest request) {

        // El servicio se encarga de buscar el ID o lanzar 404
        Device deviceDetails = new Device();
        deviceDetails.setBrand(request.brand());
        deviceDetails.setModel(request.model());
        deviceDetails.setSerialNumber(request.serialNumber());

        Device updatedDevice = deviceService.updateDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

    /**
     * Endpoint: DELETE /api/devices/{id}
     * Permisos: ADMIN
     * Elimina un dispositivo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id); // El servicio lanza 404 si no existe
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}