package dev.cloudnative.k8s.contact.store.controller;

import dev.cloudnative.k8s.contact.store.dto.ContactDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @LocalServerPort
    private int port;

    private WebTestClient webClient;

    @BeforeAll
    public void setup() {
        this.webClient = WebTestClient.bindToServer()
            .baseUrl("https://localhost:" + this.port)
            .build();
    }

    @Test
    public void testContactsGetEndpoint() {
        this.webClient.get().uri("/contacts")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBodyList(ContactDTO.class).hasSize(10);
    }
}
