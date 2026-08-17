package uz.joka.wedding.invitation;

public class InvitationNotFoundException extends RuntimeException {
    public InvitationNotFoundException(String code) {
        super("Invitation not found: " + code);
    }
}
