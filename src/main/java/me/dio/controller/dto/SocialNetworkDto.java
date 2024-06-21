package me.dio.controller.dto;

import me.dio.domain.model.SocialNetwork;

public record SocialNetworkDto(Long id, String nameItem, String description) {

    public SocialNetworkDto(SocialNetwork model) {
        this(model.getId(), model.getnameItem(), model.getDescription());
    }

    public SocialNetwork toModel() {
        SocialNetwork model = new SocialNetwork();
        model.setId(this.id);
        model.setnameItem(this.nameItem);
        model.setDescription(this.description);
        return model;
    }
}
