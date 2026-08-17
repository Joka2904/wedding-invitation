package uz.joka.wedding.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.joka.wedding.model.GuestView;
import uz.joka.wedding.model.InvitationPage;
import uz.joka.wedding.properties.WeddingProperties;
import uz.joka.wedding.service.GuestService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationService {
    private final WeddingProperties properties;
    private final GuestService guestService;

    public InvitationPage invitationFor(UUID code) {
        GuestView guest = guestService.getByCode(code);

        InvitationPage invitationPage = new InvitationPage(
                guest,
                properties.couple(),
                properties.event()
        );
        log.info("Invitation for {} is {}", code, invitationPage);
        return invitationPage;
    }
}
