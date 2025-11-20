package com.DHARMA.userTicketBooking.repositiory;

import com.DHARMA.userTicketBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
