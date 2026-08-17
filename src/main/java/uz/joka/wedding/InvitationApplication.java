package uz.joka.wedding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "uz.joka.wedding.properties")
@SpringBootApplication
public class InvitationApplication {

    static void main(String[] args) {
        SpringApplication.run(InvitationApplication.class, args);
    }

}
