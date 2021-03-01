package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<GenericEntity> getGroups() {
        return  new ArrayList<>();
    }

    @PostMapping(value = "createGroup")
    public GenericEntity createGroup(GenericEntity genericEntity) {
        return new GenericEntity("1");
    }

    @GetMapping(value = "getGroup")
    public GenericEntity getGroup(@PathVariable long id) {
        return new GenericEntity("2");
    }

    @PutMapping(value = "updateGroup")
    public GenericEntity updateGroup(GenericEntity genericEntity) {
        return new GenericEntity("3");
    }
}
