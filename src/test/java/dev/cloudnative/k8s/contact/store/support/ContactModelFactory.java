package dev.cloudnative.k8s.contact.store.support;

import dev.cloudnative.k8s.contact.store.model.Contact;

/**
 * Factory class to create test data for model classes used in unit tests
 */
public class ContactModelFactory {

    public static Contact aContact() {
        return Contact.builder()
            .firstName("Joe")
            .lastName("Blogs")
            .phoneNumber("0787878787")
            .build();
    }
}
