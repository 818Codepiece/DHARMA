package com.DHARMA.userTicketBooking.service;

import com.DHARMA.userTicketBooking.dto.BookingDto;
import com.DHARMA.userTicketBooking.entity.Booking;
import com.DHARMA.userTicketBooking.entity.BookingStatus;
import com.DHARMA.userTicketBooking.entity.Darshan;
import com.DHARMA.userTicketBooking.entity.User;
import com.DHARMA.userTicketBooking.repositiory.BookingRepository;
import com.DHARMA.userTicketBooking.repositiory.DarshanRepository;
import com.DHARMA.userTicketBooking.repositiory.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//public class BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private DarshanRepository darshanRepository;
//
//    public Booking createBooking(BookingDto bookingDto) {
//        Darshan darshan = darshanRepository.findById(bookingDto.getDarshanId())
//                .orElseThrow(() -> new RuntimeException("NOT FOUND"));
//
//        if (!isSeatsAvailable(darshan.getId(), bookingDto.getNumberOfSeats())){
//            throw new RuntimeException("NOT ENOUGH SEATS ARE LEFT");
//        }
//
//        if (bookingDto.getSeatNumbers().size() != bookingDto.getNumberOfSeats()){
//            throw new RuntimeException("SEATS NUMBERS AND NUM OF SEATS MUST BE EQUAL");
//        }
//    }
//
//    private boolean isSeatsAvailable(Long darshanId, Integer numberOfSeats) {
//        Darshan darshan = darshanRepository.findById(darshanId)
//                .orElseThrow(() -> new RuntimeException("NOT FOUND"));
//
//        int bookedSeats = darshan.getBookings().stream()
//                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
//                .mapToInt(Booking::getNumberOfSeats)
//                .sum();
//
//        return (darshan.getZones().getZoneCapacity() - bookedSeats) >= numberOfSeats;
//    }
//
//    public List<Booking> getUserBooking(Long id) {
//    }
//
//    public List<Booking> getDarshanBookings(Long id) {
//    }
//
//    public Booking confirmBooking(Long id) {
//    }
//
//    public Booking cancelBooking(Long id) {
//    }
//
//    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
//
//    }
//}


//package com.DHARMA.userTicketBooking.service;
//
//import com.DHARMA.userTicketBooking.dto.BookingDto;
//import com.DHARMA.userTicketBooking.entity.Booking;
//import com.DHARMA.userTicketBooking.entity.BookingStatus;
//import com.DHARMA.userTicketBooking.entity.Darshan;
//import com.DHARMA.userTicketBooking.entity.User;
//import com.DHARMA.userTicketBooking.repository.BookingRepository;
//import com.DHARMA.userTicketBooking.repository.DarshanRepository;
//import com.DHARMA.userTicketBooking.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DarshanRepository darshanRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingDto bookingDto) {
        // validate darshan
        Darshan darshan = darshanRepository.findById(bookingDto.getDarshanId())
                .orElseThrow(() -> new RuntimeException("Darshan not found"));

        // validate user
        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // seat availability
        if (!isSeatsAvailable(darshan.getId(), bookingDto.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seats are left");
        }

        if (bookingDto.getSeatNumbers() == null || bookingDto.getSeatNumbers().size() != bookingDto.getNumberOfSeats()) {
            throw new RuntimeException("Seat numbers and number of seats must be equal");
        }

        Booking booking = new Booking();
        booking.setBookingTime(
                bookingDto.getBookingTime() != null ? bookingDto.getBookingTime() : LocalDateTime.now()
        );
        booking.setPrice(bookingDto.getPrice());
        booking.setBookingStatus(bookingDto.getBookingStatus() != null ? bookingDto.getBookingStatus() : BookingStatus.PENDING);
        booking.setNumberOfSeats(bookingDto.getNumberOfSeats());
        booking.setIdNumbers(bookingDto.getSeatNumbers());
        booking.setUser(user);
        booking.setDarshan(darshan);

        Booking saved = bookingRepository.save(booking);

        if (darshan.getBookings() != null) {
            darshan.getBookings().add(saved);
        }

        return saved;
    }

    private boolean isSeatsAvailable(Long darshanId, Integer numberOfSeats) {
        Darshan darshan = darshanRepository.findById(darshanId)
                .orElseThrow(() -> new RuntimeException("Darshan not found"));

        int bookedSeats = darshan.getBookings().stream()
                .filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();

        return (darshan.getZones().getZoneCapacity() - bookedSeats) >= numberOfSeats;
    }

    public List<Booking> getUserBooking(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getDarshanBookings(Long darshanId) {
        return bookingRepository.findByDarshanId(darshanId);
    }

    public Booking confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (booking.getBookingStatus() == BookingStatus.CONFIRMED) {
            return booking;
        }
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Cannot confirm a cancelled booking");
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            return booking;
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
        return bookingRepository.findByBookingStatus(bookingStatus);
    }
}