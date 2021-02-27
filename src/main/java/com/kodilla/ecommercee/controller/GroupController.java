package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping(value = "createGroup")
    public void createGroup(GroupDto groupDto) {
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(Long groupId) {
        return new GroupDto();
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto();
    }
}
