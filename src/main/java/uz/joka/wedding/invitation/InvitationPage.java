package uz.joka.wedding.invitation;

import uz.joka.wedding.properties.WeddingProperties;

public record InvitationPage(
        String code,
        String displayName,
        String greeting,
        int maxGuests,
        WeddingProperties.Couple couple,
        WeddingProperties.Event event
) {}
