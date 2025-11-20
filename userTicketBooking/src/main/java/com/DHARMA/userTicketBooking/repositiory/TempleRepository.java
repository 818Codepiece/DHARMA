package com.DHARMA.userTicketBooking.repositiory;

import com.DHARMA.userTicketBooking.entity.Temple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TempleRepository extends JpaRepository<Temple, Long> {

    Optional<List<Temple>> findBySpeciality(String speciality);

    Optional<Temple> findByName(String title);
}
