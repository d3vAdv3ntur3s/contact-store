package dev.cloudnative.k8s.contact.store.service;

import dev.cloudnative.k8s.contact.store.dto.ContactDTO;
import dev.cloudnative.k8s.contact.store.mapper.ContactMapper;
import dev.cloudnative.k8s.contact.store.model.Contact;
import dev.cloudnative.k8s.contact.store.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public Flux<Contact> findAllContacts(final Integer offset, final Integer limit) {
        return contactRepository.findAll(offset, limit);
    }

    public Mono<Contact> findById(final UUID id) {
        return contactRepository.findById(id)
            .switchIfEmpty(monoResponseStatusNotFoundException("Contact not found: " + id));
    }

    public Mono<Void> delete(final UUID contactId) {
        return contactRepository.deleteById(contactId);
    }

    public Mono<Contact> save(final ContactDTO contactDTO) {
        return contactRepository.save(contactMapper.from(contactDTO));
    }

    <T> Mono<T> monoResponseStatusNotFoundException(final String message) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, message));
    }
}


