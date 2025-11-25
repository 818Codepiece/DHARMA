package com.DHARMA.userTicketBooking.repository;

import com.DHARMA.userTicketBooking.entity.Booking;
import com.DHARMA.userTicketBooking.entity.Enum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_UserId(Long userId);
    List<Booking> findByDarshan_Id(Long darshanId);
    List<Booking> findByBookingStatus(BookingStatus status);
}
