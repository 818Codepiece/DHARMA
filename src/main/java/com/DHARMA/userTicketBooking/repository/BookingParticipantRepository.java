package com.DHARMA.userTicketBooking.repository;
import com.DHARMA.userTicketBooking.entity.BookingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingParticipantRepository extends JpaRepository<BookingParticipant, Long> {}
