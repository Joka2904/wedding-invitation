package uz.joka.wedding.model;

import uz.joka.wedding.properties.WeddingProperties;

public record InvitationPage(
        GuestView guest,
        WeddingProperties.Couple couple,
        WeddingProperties.Event event
) {}
