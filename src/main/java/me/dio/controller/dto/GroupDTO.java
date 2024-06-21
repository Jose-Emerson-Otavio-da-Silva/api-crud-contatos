package me.dio.controller.dto;

import me.dio.domain.model.Group;

public record GroupDTO(Long id, String nameItem, String description) {

    public GroupDTO(Group model) {
        this(model.getId(), model.getnameItem(), model.getDescription());
    }

    public Group toModel() {
        Group model = new Group();
        model.setId(this.id);
        model.setnameItem(this.nameItem);
        model.setDescription(this.description);
        return model;
    }
}
