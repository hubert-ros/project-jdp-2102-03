package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
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

    @PostMapping(value = "createGroup")
    public GenericEntity createGroup(GenericEntity genericEntity) {
        return new GenericEntity("created group:  lavender soap");
    }

    @GetMapping(value = "getGroup")
    public GenericEntity getGroup(@RequestParam long id) {
        return new GenericEntity("get one group");
    }

    @PutMapping(value = "updateGroup")
    public GenericEntity updateGroup(GenericEntity genericEntity) {
        return new GenericEntity("updated group: liquid soap");
    }
}
