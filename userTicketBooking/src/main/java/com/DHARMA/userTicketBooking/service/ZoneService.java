package com.DHARMA.userTicketBooking.service;

import com.DHARMA.userTicketBooking.entity.Zones;
import com.DHARMA.userTicketBooking.repositiory.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zones> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zones getZoneByType(String zoneType) {
        Optional<Zones> zones = zoneRepository.findByZoneType(zoneType);
        if (zones.isPresent()){
            return zones.get();
        }
        else throw new RuntimeException("NO ZONE FOUND FOR ZONETYPE "+zoneType);
    }


    public Zones addZone(ZoneDto zoneDto) {
        Zones zones = new Zones();

        zones.setZoneType(zoneDto.getZoneType());
        zones.setZoneCapacity(zones.getZoneCapacity());
        zones.setZoneDuration(zones.getZoneDuration());
        zones.setId(zones.getId());
        zones.setZoneClosingInterval(zones.getZoneClosingInterval());
        zones.setZoneOpeningInterval(zones.getZoneOpeningInterval());

        return zoneRepository.save(zones);
    }

    public Zones updateZone(Long id, ZoneDto zoneDto) {
        Zones zones = zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO ZONE FOUND FOR ID "+id));

        zones.setZoneType(zoneDto.getZoneType());
        zones.setZoneCapacity(zones.getZoneCapacity());
        zones.setZoneDuration(zones.getZoneDuration());
        zones.setId(zones.getId());
        zones.setZoneClosingInterval(zones.getZoneClosingInterval());
        zones.setZoneOpeningInterval(zones.getZoneOpeningInterval());

        return zoneRepository.save(zones);
    }

    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }
}
