package uz.joka.wedding.invitation;

import org.springframework.stereotype.Service;
import uz.joka.wedding.properties.WeddingProperties;

@Service
public class InvitationService {
    private final WeddingProperties properties;

    public InvitationService(WeddingProperties properties) {
        this.properties = properties;
    }

    public InvitationPage invitationFor(String code) {
        var guest = properties.guests().stream()
                .filter(item -> item.code().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new InvitationNotFoundException(code));

        return new InvitationPage(
                guest.code(),
                guest.displayName(),
                guest.greeting(),
                guest.maxGuests(),
                properties.couple(),
                properties.event()
        );
    }
}
