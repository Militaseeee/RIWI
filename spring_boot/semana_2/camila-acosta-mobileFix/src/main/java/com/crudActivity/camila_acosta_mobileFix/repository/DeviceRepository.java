package com.crudActivity.camila_acosta_mobileFix.repository;

import com.crudActivity.camila_acosta_mobileFix.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findBySerialNumber(String serialNumber);
}
