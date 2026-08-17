package uz.joka.wedding.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.joka.wedding.model.GuestCreateForm;
import uz.joka.wedding.model.GuestUpdateForm;
import uz.joka.wedding.service.GuestService;

@Controller
@RequestMapping("/admin/guests")
@RequiredArgsConstructor
public class GuestAdminController {

    private final GuestService guestService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("guests", guestService.findAll());

        return "admin/guests";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute(
                "guest",
                new GuestCreateForm("", "", 1)
        );

        return "admin/guest-create";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("guest") GuestCreateForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/guest-create";
        }

        guestService.create(form);

        return "redirect:/admin/guests";
    }

    @GetMapping("/{id}/edit")
    public String editForm(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("guest", guestService.getUpdateForm(id));
        model.addAttribute("guestId", id);

        return "admin/guest-edit";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("guest") GuestUpdateForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("guestId", id);
            return "admin/guest-edit";
        }

        guestService.update(id, form);

        return "redirect:/admin/guests";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        guestService.delete(id);

        return "redirect:/admin/guests";
    }
}