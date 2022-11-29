package ibk.ibag.challenge.controller;


import ibk.ibag.challenge.model.UserDTO;
import ibk.ibag.challenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{code}")
    @ApiOperation(notes = "Retrieve one user by code", value = "Get user by code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response ok if the operation was successful"),
            @ApiResponse(code = 404, message = "Response not found if the resource could not be found")
    })
    public ResponseEntity<UserDTO> getUserByCode(@PathVariable  String code) {
        Optional<UserDTO> optUser = userService.getUserByCode(code);
        try {
            UserDTO user = optUser.orElseThrow(NoSuchElementException::new);
            Link withSelfRel = linkTo(methodOn(UserController.class).getUserByCode(user.getCode())).withSelfRel();
            user.add(withSelfRel);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("Creating a new User...");
        userService.saveUser(userDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand(userDTO.getCode())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public UserDTO updateUser(@Valid @RequestBody UserDTO user) {
        System.out.println("Updating a user...");
        UserDTO userUpdated = userService.updateUser(user);
        return userUpdated;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable String code) {
        System.out.println("Deleting user " + code + "...");
        return ResponseEntity.ok(null);
    }

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@RequestParam(required = false) String name) {
        List<UserDTO> filtered = userService.listUsersByName(name);
        for (UserDTO user : filtered) {
            Link withSelfRel = linkTo(methodOn(UserController.class).getUserByCode(user.getCode())).withSelfRel();
            user.add(withSelfRel);
        }

        Link link = linkTo(methodOn(UserController.class).listAllUsers(name)).withSelfRel();
        CollectionModel<UserDTO> result = CollectionModel.of(filtered, link);

        return ResponseEntity.ok(result);
    }
}
