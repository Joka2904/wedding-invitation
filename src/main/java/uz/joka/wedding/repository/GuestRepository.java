package uz.joka.wedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.joka.wedding.entity.Guest;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findByCode(UUID code);
}