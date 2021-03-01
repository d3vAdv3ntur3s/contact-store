package dev.cloudnative.k8s.contact.store.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Data Model representation of a Contact
 * <p>
 * See Request/Response generic DTO for contract: {@link dev.cloudnative.k8s.contact.store.dto.ContactDTO}
 */
@Table("contact")
@Data
@Builder
public class Contact {

    /**
     * In the hibernate world it would be something as follows:
     *
     * @Id
     * @GeneratedValue(generator = "uuid2")
     * @GenericGenerator(name = "uuid2", strategy = "uuid2")
     * @Column(name = "id", updatable = false, nullable = false)
     */
    @Id
    private UUID id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("phone_number")
    private String phoneNumber;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Tolerate
    public Contact() {
        // no args constructor is required for JPA which conflicts with lombok Builder
    }
}
