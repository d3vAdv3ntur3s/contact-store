package dev.cloudnative.k8s.contact.store.repository;

import dev.cloudnative.k8s.contact.store.model.Contact;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.SerializationUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static dev.cloudnative.k8s.contact.store.support.ContactModelFactory.aContact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * R2DBC test to validate persistence of the model {@link Contact}
 */
@Disabled
@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public class ContactRepositoryTest {

    /* DatabaseClient is for R2DBC what JdbcTemplate is for JDBC */
    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testDatabaseClientExisted() {
        assertNotNull(databaseClient);
    }

    @Test
    public void testContactRepositoryExisted() {
        assertNotNull(contactRepository);
    }

    @Test
    public void testCreateContact() {
        Mono<Contact> createdContact = contactRepository.save(aContact());
        //R2dbcEntityTemplate template = new R2dbcEntityTemplate(databaseClient, H2Dialect.INSTANCE);
        //template.insert(Contact.class).using(aContact()).then().as(StepVerifier::create).verifyComplete();

        createdContact.as(StepVerifier::create)
            .assertNext(actual -> {
                assertThat(actual.getFirstName()).isEqualTo("Joe");
                assertThat(actual.getLastName()).isEqualTo("Blogs");
                assertThat(actual.getId()).isNotNull();
            })
            .verifyComplete();
    }

    @Test
    public void testFindContactById() {
        Contact contact = aContact();

        R2dbcEntityTemplate template = new R2dbcEntityTemplate(databaseClient, H2Dialect.INSTANCE);
        template.insert(Contact.class).using(contact).then().as(StepVerifier::create).verifyComplete();
        Mono<Contact> retrievedContact = contactRepository.findById(contact.getId());


        retrievedContact.as(StepVerifier::create)
            .assertNext(actual -> {
                assertThat(actual.getFirstName()).isEqualTo("Joe");
                assertThat(actual.getLastName()).isEqualTo("Blogs");
            })
            .verifyComplete();
    }
}
