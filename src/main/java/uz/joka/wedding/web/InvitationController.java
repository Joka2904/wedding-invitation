package uz.joka.wedding.web;

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
import uz.joka.wedding.exception.InvitationNotFoundException;
import uz.joka.wedding.invitation.InvitationService;

@Controller
@RequestMapping("/invite")
public class InvitationController {
    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping("/{code}")
    public String invitation(@PathVariable String code, Model model) {
        model.addAttribute("invitation", invitationService.invitationFor(code));
        model.addAttribute("rsvp", new RsvpForm());
        return "invitation";
    }

    @PostMapping("/{code}/rsvp")
    public String rsvp(@PathVariable String code,
                       @ModelAttribute("rsvp") RsvpForm form,
                       Model model) {
        // Replace this with your own persistence / email / API logic later.
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
