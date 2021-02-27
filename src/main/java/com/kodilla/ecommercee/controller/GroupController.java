package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<GenericEntity> getGroups() {
        return Collections.singletonList(new GenericEntity("taborety, mydło, powidło"));
    }

    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericEntity createGroup(@RequestBody GenericEntity genericEntity) {
        return new GenericEntity("created group:  lavender soap");
    }

    @GetMapping(value = "getGroup")
    public GenericEntity getGroup(Long groupId) {
        return new GenericEntity("get one group");
    }

    @PutMapping(value = "updateGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericEntity updateGroup(@RequestBody GenericEntity genericEntity) {
        return new GenericEntity("updated group: liquid soap");
    }
}
