package uz.joka.wedding.model;

import java.util.UUID;

public record GuestView(
        Long id,
        UUID code,
        String displayName,
        String greeting,
        int maxGuest
) {
    public String invitationPath() {
        return "/invite/" + code;
    }
}