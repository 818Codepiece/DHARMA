package com.DHARMA.userTicketBooking.repository;
import com.DHARMA.userTicketBooking.entity.TempleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TempleAdminRepository extends JpaRepository<TempleAdmin, Long> {
    Optional<TempleAdmin> findByUsername(String username);
}
