package me.dio.controller.dto;

import me.dio.domain.model.Address;

public record AddressDto(Long id, String street, Long cep, String number, String district, String city, String state,
        String country) {

    public AddressDto(Address model) {
        this(model.getId(), model.getStreet(), model.getZipCode(), model.getNumber(), model.getDistrict(),
                model.getCity(), model.getState(), model.getCountry());
    }

    public Address toModel() {
        Address model = new Address();
        model.setId(this.id);
        model.setStreet(this.street);
        model.setCep(this.cep);
        model.setNumber(this.number);
        model.setDistrict(this.district);
        model.setCity(this.city);
        model.setState(this.state);
        model.setCountry(this.country);
        return model;
    }
}
