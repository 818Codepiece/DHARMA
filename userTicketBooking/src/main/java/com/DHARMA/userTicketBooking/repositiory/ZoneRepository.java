package com.DHARMA.userTicketBooking.repositiory;

import com.DHARMA.userTicketBooking.entity.Zones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository <Zones, Long> {

    Optional<Zones> findByZoneType(String zoneType);
}
