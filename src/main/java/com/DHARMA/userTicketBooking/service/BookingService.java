package com.DHARMA.userTicketBooking.service;

import com.DHARMA.userTicketBooking.dto.*;
import com.DHARMA.userTicketBooking.entity.*;
import com.DHARMA.userTicketBooking.entity.Enum.BookingStatus;
import com.DHARMA.userTicketBooking.entity.Enum.PaymentStatus;
import com.DHARMA.userTicketBooking.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DarshanRepository darshanRepository;
    private final ZoneRepository zoneRepository;
    private final BookingParticipantRepository participantRepository;
    private final PaymentRepository paymentRepository;
    private final SpecialBookingDetailsRepository specialDetailsRepository;

    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Darshan darshan = darshanRepository.findById(dto.getDarshanId())
                .orElseThrow(() -> new RuntimeException("Darshan not found"));

        Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        Booking booking = Booking.builder()
                .user(user)
                .darshan(darshan)
                .zone(zone)
                .bookerType(dto.getBookerType())
                .bookingStatus(BookingStatus.PENDING)
                .bookingDateTime(LocalDateTime.now().plusMinutes(15))
                .build();

        booking = bookingRepository.save(booking);

        if (dto.getParticipants() != null && !dto.getParticipants().isEmpty()) {
            for (BookingParticipantDTO pDto : dto.getParticipants()) {
                BookingParticipant participant = new BookingParticipant();
                participant.setBooking(booking);

                if (pDto.getUserId() != null) {
                    userRepository.findById(pDto.getUserId()).ifPresent(participant::setUser);
                }

                participant.setName(pDto.getName());
                participant.setAge(pDto.getAge());
                participant.setGender(pDto.getGender());

                participant = participantRepository.save(participant);

                if (pDto.getSpecialBookingDetails() != null) {
                    SpecialBookingDetailDTO sdto = pDto.getSpecialBookingDetails();
                    SpecialBookingDetails special = SpecialBookingDetails.builder()
                            .participant(participant)
                            .specialType(sdto.getSpecialType())
                            .details(sdto.getDetails())
                            .requiresAssistance(sdto.getRequiresAssistance())
                            .build();
                    special = specialDetailsRepository.save(special);
                    participant.setSpecialBookingDetails(special);
                    participantRepository.save(participant);
                }
            }
        }

        if (dto.getPayment() != null) {
            PaymentDTO payDto = dto.getPayment();

            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAmount(payDto.getAmount());
            payment.setPaymentMethod(payDto.getPaymentMethod());
            payment.setPaymentStatus(PaymentStatus.INITIATED);
            payment.setTransactionId(payDto.getTransactionId());
            payment.setPaymentAt(Instant.now());
            payment.setPaymentRef(payDto.getPaymentRef());

            payment = paymentRepository.save(payment);

            booking.setPayment(payment);
            bookingRepository.save(booking);
        }

        return mapToResponse(booking);
    }

    @Transactional
    public BookingResponseDTO confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking = bookingRepository.save(booking);

        return mapToResponse(booking);
    }

    @Transactional
    public GeneralResponseDTO cancelBooking(Long id, CancelBookingDTO cancelDto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setCancelled(true);
        booking.setCancelReason(cancelDto.getReason());
        booking.setCancelledBy(cancelDto.getCancelledBy());
        booking.setCancelDateTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.CANCELLED);

        if (booking.getPayment() != null) {
            booking.getPayment().setPaymentStatus(PaymentStatus.CANCELLED);
            paymentRepository.save(booking.getPayment());
        }

        bookingRepository.save(booking);

        return new GeneralResponseDTO(true, "Booking cancelled successfully", null);
    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getUserBooking(Long userId) {
        return bookingRepository.findByUser_UserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getDarshanBookings(Long darshanId) {
        return bookingRepository.findByDarshan_Id(darshanId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getBookingByStatus(com.DHARMA.userTicketBooking.entity.Enum.BookingStatus status) {
        return bookingRepository.findByBookingStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BookingResponseDTO mapToResponse(Booking booking) {
        List<BookingParticipantDTO> participants = booking.getParticipants() == null
                ? List.of()
                : booking.getParticipants().stream().map(p -> {
            SpecialBookingDetailDTO specialDto = null;
            SpecialBookingDetails s = p.getSpecialBookingDetails();
            if (s != null) {
                specialDto = SpecialBookingDetailDTO.builder()
                        .specialId(s.getSpecialId())
                        .specialType(s.getSpecialType())
                        .details(s.getDetails())
                        .requiresAssistance(s.getRequiresAssistance())
                        .build();
            }
            return BookingParticipantDTO.builder()
                    .participantId(p.getParticipantId())
                    .userId(p.getUser() != null ? p.getUser().getUserId() : null)
                    .name(p.getName())
                    .age(p.getAge())
                    .gender(p.getGender())
                    .specialBookingDetails(specialDto)
                    .build();
        }).collect(Collectors.toList());

        return BookingResponseDTO.builder()
                .bookingId(booking.getBookingId())
                .userId(booking.getUser() != null ? booking.getUser().getUserId() : null)
                .darshanId(booking.getDarshan() != null ? booking.getDarshan().getId() : null)
                .zoneId(booking.getZone() != null ? booking.getZone().getZoneId() : null)
                .templeId(booking.getDarshan() != null && booking.getDarshan().getTemple() != null
                        ? booking.getDarshan().getTemple().getTempleId()
                        : null)
                .bookingStatus(booking.getBookingStatus())
                .paymentStatus(booking.getPayment() != null ? booking.getPayment().getPaymentStatus() : null)
                .bookingDateTime(booking.getBookingDateTime())
                .participants(participants)
                .totalParticipants(participants.size())
                .build();
    }
}
