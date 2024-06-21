package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.controller.dto.ContactDto;
import me.dio.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/contacts")
@Tag(name = "Contacts Controller", description = "RESTful API for managing contacts.")
public record ContactController(ContactService contactService) {

    @GetMapping
    @Operation(summary = "Get all contacts", description = "Retrieve a list of all registered contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<ContactDto>> findAll() {
        var contacts = contactService.findAll();
        var contactsDTO = contacts.stream().map(ContactDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(contactsDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a contact by ID", description = "Retrieve a specific contact based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<ContactDto> findById(@PathVariable Long id) {
        var contact = contactService.findById(id);
        return ResponseEntity.ok(new ContactDto(contact));
    }

    @PostMapping
    @Operation(summary = "Create a new contact", description = "Create a new contact and return the created contact's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "contact created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid contact data provided")
    })
    public ResponseEntity<ContactDto> create(@RequestBody ContactDto contactDto) {
        var contact = contactService.create(contactDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contact.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ContactDto(contact));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a contact", description = "Update the data of an existing contact based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "contact updated successfully"),
            @ApiResponse(responseCode = "404", description = "contact not found"),
            @ApiResponse(responseCode = "422", description = "Invalid contact data provided")
    })
    public ResponseEntity<ContactDto> update(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        var contact = contactService.update(id, contactDto.toModel());
        return ResponseEntity.ok(new ContactDto(contact));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact", description = "Delete an existing contact based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "contact deleted successfully"),
            @ApiResponse(responseCode = "404", description = "contact not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
