package uz.joka.wedding.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@ConfigurationProperties(prefix = "wedding")
public record WeddingProperties(
        Couple couple,
        Event event
) {
    public record Couple(String personOne, String personTwo, String tagline) {}

    public record Event(
            LocalDateTime dateTime,
            String venue,
            String address,
            String dressCode,
            String mapUrl
    ) {
        private static final Locale DISPLAY_LOCALE = Locale.ENGLISH;

        public String dayName() {
            return dateTime.format(DateTimeFormatter.ofPattern("EEEE", DISPLAY_LOCALE));
        }

        public String longDate() {
            return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", DISPLAY_LOCALE));
        }

        public String fullDate() {
            return dateTime.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", DISPLAY_LOCALE));
        }

        public String time() {
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm", DISPLAY_LOCALE));
        }

        public String numericDate() {
            return dateTime.format(DateTimeFormatter.ofPattern("dd · MM · yyyy", DISPLAY_LOCALE));
        }
    }
}
