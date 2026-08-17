package uz.joka.wedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.joka.wedding.entity.Guest;
import uz.joka.wedding.model.GuestCreateForm;
import uz.joka.wedding.model.GuestUpdateForm;
import uz.joka.wedding.model.GuestView;
import uz.joka.wedding.repository.GuestRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestService {

    private final GuestRepository guestRepository;

    public List<GuestView> findAll() {
        return guestRepository.findAll()
                .stream()
                .map(this::toView)
                .toList();
    }

    public GuestView getByCode(UUID code) {
        return guestRepository.findByCode(code)
                .map(this::toView)
                .orElseThrow();
    }

    public GuestView findById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guest not found: " + id));

        return toView(guest);
    }

    public GuestUpdateForm getUpdateForm(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guest not found: " + id));

        return new GuestUpdateForm(
                guest.getDisplayName(),
                guest.getGreeting(),
                guest.getMaxGuest()
        );
    }

    @Transactional
    public GuestView create(GuestCreateForm form) {

        Guest guest = Guest.builder()
                .code(UUID.randomUUID())
                .displayName(form.displayName())
                .greeting(form.greeting())
                .maxGuest(form.maxGuest())
                .build();

        return toView(guestRepository.save(guest));
    }

    @Transactional
    public void update(Long id, GuestUpdateForm form) {

        Guest guest = guestRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guest not found: " + id));

        guest.setDisplayName(form.displayName());
        guest.setGreeting(form.greeting());
        guest.setMaxGuest(form.maxGuest());
    }

    @Transactional
    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    private GuestView toView(Guest guest) {
        return new GuestView(
                guest.getId(),
                guest.getCode(),
                guest.getDisplayName(),
                guest.getGreeting(),
                guest.getMaxGuest()
        );
    }
}