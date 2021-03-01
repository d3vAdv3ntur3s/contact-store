package dev.cloudnative.k8s.contact.store.mapper;

import dev.cloudnative.k8s.contact.store.dto.ContactDTO;
import dev.cloudnative.k8s.contact.store.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Map DTO to Domain/Entity
 * Auto generates {@link ContactMapperImpl}
 * <p>
 * componentModel spring will create a Java bean via @Component
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface ContactMapper {
    ContactDTO from(Contact contact);

    Contact from(ContactDTO contactDTO);
}
