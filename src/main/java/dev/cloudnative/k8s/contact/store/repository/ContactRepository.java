package dev.cloudnative.k8s.contact.store.repository;

import dev.cloudnative.k8s.contact.store.model.Contact;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;


/**
 * Reactive Sorting Repository for {@link Contact} model operations
 */
public interface ContactRepository extends ReactiveSortingRepository<Contact, UUID> {

    @Query("SELECT * FROM contact_store WHERE first_name like :name")
    Flux<Contact> findByFirstNameContains(String name);

    /* No Reactive Pagination equivalent in R2DBC as of yet */
    @Query("SELECT * FROM contact_store OFFSET :offset LIMIT :limit")
    Flux<Contact> findAll(Integer offset, Integer limit);
}
