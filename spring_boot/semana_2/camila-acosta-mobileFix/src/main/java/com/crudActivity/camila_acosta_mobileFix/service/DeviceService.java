package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.model.Device;
import java.util.List;

public interface DeviceService {
    List<Device> findAllDevices();
    Device findDeviceById(Long id);
    Device createDevice(Device device);
    Device updateDevice(Long id, Device deviceDetails);
    void deleteDevice(Long id);
}
