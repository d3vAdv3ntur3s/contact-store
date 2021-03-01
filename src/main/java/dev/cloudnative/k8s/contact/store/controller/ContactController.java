package dev.cloudnative.k8s.contact.store.controller;

import dev.cloudnative.k8s.contact.store.controller.api.ContactsApi;
import dev.cloudnative.k8s.contact.store.dto.ContactDTO;
import dev.cloudnative.k8s.contact.store.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;


/**
 * This is typical declarative means of developing controllers and request mappings when using Webflux.
 * Spring 5 introduced a functional approach, called RouterFunction and HandlerFunctions
 * <p>
 * RESTful controller implementing auto generated API Interface.
 * <p>
 * TODO: Create a workaround for complex return signatures issue, see discussion: https://github.com/OpenAPITools/openapi-generator/issues/1309
 */
@Slf4j
@RequiredArgsConstructor
@RestController("/contacts")
public class ContactController implements ContactsApi {

    private final ContactService contactService;

    /**
     * POST /contacts : Add a new contract to the contact store
     *
     * @param contactDTO (optional)
     * @param exchange
     * @return Created contact (status code 201)
     * or Bad Request (status code 400)
     * or The specified resource was not found (status code 404)
     * or The method used was not allowed (status code 405)
     * or Server Error (status code 500)
     */
    @Override
    public Mono<ResponseEntity<ContactDTO>> addContact(@Valid Mono<ContactDTO> contactDTO, ServerWebExchange exchange) {
        return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
    }

    /**
     * DELETE /contacts/{contactId} : Delete contact resource
     *
     * @param contactId ID number tied to a particular Contact (required)
     * @param exchange
     * @return Updated contact resource (status code 204)
     * or Bad Request (status code 400)
     * or The specified resource was not found (status code 404)
     * or The method used was not allowed (status code 405)
     * or Server Error (status code 500)
     */
    @Override
    public Mono<ResponseEntity<Void>> deleteContact(UUID contactId, ServerWebExchange exchange) {
        return contactService.delete(contactId)
            .then(Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    /**
     * GET /contacts : List all contacts in contact store
     * Returns all contacts in the list
     *
     * @param offset   The number of items to skip before starting to collect the result set. (optional)
     * @param limit    The numbers of items to return. (optional, default to 20)
     * @param exchange
     * @return successful operation (status code 200)
     * or Contacts not found (status code 404)
     * or Unexpected error (status code 200)
     */
    @Override
    public Mono<ResponseEntity<Flux<ContactDTO>>> getAllContacts(@Min(0) @Valid Integer offset, @Min(20) @Max(100) @Valid Integer limit, ServerWebExchange exchange) {
        return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
    }

    /**
     * GET /contacts/{contactId} : Returns a user by ID.
     *
     * @param contactId ID number tied to a particular Contact (required)
     * @param exchange
     * @return OK (status code 200)
     * or Bad Request (status code 400)
     * or The specified resource was not found (status code 404)
     * or The method used was not allowed (status code 405)
     * or Server Error (status code 500)
     */
    @Override
    public Mono<ResponseEntity<ContactDTO>> getContactById(UUID contactId, ServerWebExchange exchange) {
        return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
    }


    /**
     * PUT /contacts/{contactId} : Update contact resource
     *
     * @param contactId  ID number tied to a particular Contact (required)
     * @param contactDTO (optional)
     * @param exchange
     * @return Updated contact resource (status code 204)
     * or Bad Request (status code 400)
     * or The specified resource was not found (status code 404)
     * or The method used was not allowed (status code 405)
     * or Server Error (status code 500)
     */
    @Override
    public Mono<ResponseEntity<Void>> updateContact(UUID contactId, @Valid Mono<ContactDTO> contactDTO, ServerWebExchange exchange) {
        return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
    }
}
