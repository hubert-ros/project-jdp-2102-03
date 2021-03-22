package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroup(long groupId) throws ResourceNotFoundException{
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        return optionalGroup.orElseThrow(() -> new ResourceNotFoundException("Group not existe in the database"));
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public Group updateGroup (Group group) {
        return groupRepository.save(group);
    }
}
