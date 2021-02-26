package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
@CrossOrigin(origins = "*")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<Group> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(@RequestParam long groupId) {
        return new GroupDto(1,"this is only one group");
    }

    @PutMapping(value = "updateGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1, "The best updated group");
    }
}
