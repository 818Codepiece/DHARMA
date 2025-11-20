package com.DHARMA.userTicketBooking.repositiory;

import com.DHARMA.userTicketBooking.entity.Darshan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DarshanRepository extends JpaRepository<Darshan, Long> {
}
