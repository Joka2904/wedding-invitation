package uz.joka.wedding.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.joka.wedding.entity.Rsvp;
import uz.joka.wedding.exception.InvitationNotFoundException;
import uz.joka.wedding.model.GuestView;
import uz.joka.wedding.model.InvitationPage;
import uz.joka.wedding.repository.RsvpRepository;
import uz.joka.wedding.service.GuestService;

import java.time.Instant;
import java.util.UUID;

@Controller
@RequestMapping("/invite")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;
    private final RsvpRepository rsvpRepository;
    private final GuestService guestService;

    @GetMapping("/{code}")
    public String invitation(
            @PathVariable UUID code,
            Model model
    ) {
        InvitationPage invitationPage = invitationService.invitationFor(code);

        model.addAttribute("invitation", invitationPage);
        model.addAttribute("rsvp", new RsvpForm());
        return "invitation";
    }

    @PostMapping("/{code}/rsvp")
    public String rsvp(@PathVariable UUID code,
                       @ModelAttribute("rsvp") RsvpForm form,
                       Model model) {
        rsvpRepository.save(Rsvp.builder()
                .invitationCode(code)
                .attending(form.attend())
                .guestCount(form.getGuestCount())
                .message(form.getMessage())
                .submittedAt(Instant.now()).build());
        model.addAttribute("invitation", invitationService.invitationFor(code));
        model.addAttribute("rsvp", form);
        return "rsvp-success";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvitationNotFoundException.class)
    public String notFound() {
        return "not-found";
    }
}
