package com.DHARMA.userTicketBooking.repositiory;

import com.DHARMA.userTicketBooking.entity.Booking;
import com.DHARMA.userTicketBooking.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    List<Booking> findByDarshanId(Long darshanId);

    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}
