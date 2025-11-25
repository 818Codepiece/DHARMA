package com.DHARMA.userTicketBooking.repository;
import com.DHARMA.userTicketBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
