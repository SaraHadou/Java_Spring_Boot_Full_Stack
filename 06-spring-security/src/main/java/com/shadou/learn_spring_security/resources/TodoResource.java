package com.shadou.learn_spring_security.resources;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<Todo> TODO_LIST =
            List.of(new Todo("user1", "Learn AWS"),
                    new Todo("user2", "Learn Docker"));

    @GetMapping("/todos")
    public List<Todo> retrieveAllData() {
        return TODO_LIST;
    }

    @GetMapping("/users/{username}/todos")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
//    @PostAuthorize("returnObject.username == 'user1'")
    @RolesAllowed({"ADMIN", "USER"})
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Todo> retrieveTodosForSpecificUser(@PathVariable String username) {
        return TODO_LIST.stream()
                .filter(todo -> todo.username().equals(username))
                .toList();
    }

    @PostMapping("/users/{username}/todos")
    public void createTodosForSpecificUser(@PathVariable String username,
                                           @RequestBody Todo todo) {
        logger.info("Create {} for {}", todo, username);
    }

}

record Todo (String username, String description) {}