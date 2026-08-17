package uz.joka.wedding.web;

import lombok.Data;

@Data
public class RsvpForm {
    private String attendance;
    private Integer guestCount = 1;
    private String message;

    public boolean attend() {
        return "YES".equals(attendance) && guestCount >= 1;
    }
}
