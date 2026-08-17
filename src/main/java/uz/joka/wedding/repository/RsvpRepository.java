package uz.joka.wedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.joka.wedding.entity.Rsvp;

import java.util.Optional;

public interface RsvpRepository extends JpaRepository<Rsvp, Long> {

    Optional<Rsvp> findByInvitationCode(String invitationCode);
}