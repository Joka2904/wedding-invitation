package uz.joka.wedding.web;

public class RsvpForm {
    private String attendance;
    private Integer guestCount;
    private String message;

    public String getAttendance() { return attendance; }
    public void setAttendance(String attendance) { this.attendance = attendance; }
    public Integer getGuestCount() { return guestCount; }
    public void setGuestCount(Integer guestCount) { this.guestCount = guestCount; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
