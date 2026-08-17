package uz.joka.wedding.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GuestUpdateForm(

        @NotBlank
        @Size(max = 150)
        String displayName,

        @NotBlank
        @Size(max = 250)
        String greeting,

        @Min(1)
        @Max(20)
        int maxGuest
) {
}