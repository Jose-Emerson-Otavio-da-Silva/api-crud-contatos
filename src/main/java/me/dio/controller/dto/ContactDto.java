package me.dio.controller.dto;

import me.dio.domain.model.Contact;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.Date;

public record ContactDto(
                Long id,
                String name,
                String phoneNumber,
                String email,
                AddressDto address,
                Date dateOfBirth,
                String company,
                String position,
                String notes,
                List<SocialNetworkDto> socialNetworks,
                List<GroupDTO> groups) {

        public ContactDto(Contact model) {
                this(
                                model.getId(),
                                model.getName(),
                                model.getPhoneNumber(),
                                model.getEmail(),
                                ofNullable(model.getAddress()).map(AddressDto::new).orElse(null),
                                model.getDateOfBirth(),
                                model.getCompany(),
                                model.getPosition(),
                                model.getNotes(),
                                ofNullable(model.getSocialNetworks()).orElse(emptyList()).stream()
                                                .map(SocialNetworkDto::new).collect(toList()),
                                ofNullable(model.getGroups()).orElse(emptyList()).stream().map(GroupDTO::new)
                                                .collect(toList()));
        }

        public Contact toModel() {
                Contact model = new Contact();
                model.setId(this.id);
                model.setName(this.name);
                model.setPhoneNumber(this.phoneNumber);
                model.setEmail(this.email);
                model.setAddress(ofNullable(this.address).map(AddressDto::toModel).orElse(null));
                model.setDateOfBirth(this.dateOfBirth);
                model.setCompany(this.company);
                model.setPosition(this.position);
                model.setNotes(this.notes);
                model.setSocialNetworks(ofNullable(this.socialNetworks).orElse(emptyList())
                                .stream().map(SocialNetworkDto::toModel).collect(toList()));
                model.setGroups(ofNullable(this.groups).orElse(emptyList())
                                .stream().map(GroupDTO::toModel).collect(toList()));
                return model;
        }

}
