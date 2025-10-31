package com.crudActivity.camila_acosta_mobileFix.service.impl;

import com.crudActivity.camila_acosta_mobileFix.exception.ResourceNotFoundException;
import com.crudActivity.camila_acosta_mobileFix.model.Device;
import com.crudActivity.camila_acosta_mobileFix.repository.DeviceRepository;
import com.crudActivity.camila_acosta_mobileFix.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado: " + id));
    }

    @Override
    public Device createDevice(Device device) {
        // Aquí podrías agregar validaciones, ej: no duplicar serialNumber
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Long id, Device deviceDetails) {
        Device device = findDeviceById(id); // Reusa el método que ya lanza 404

        device.setBrand(deviceDetails.getBrand());
        device.setModel(deviceDetails.getModel());
        device.setSerialNumber(deviceDetails.getSerialNumber());

        return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) {
        Device device = findDeviceById(id);
        // Aquí deberías verificar si el dispositivo está en una orden activa
        // (Por simplicidad del taller, lo borramos directamente)
        deviceRepository.delete(device);
    }
}