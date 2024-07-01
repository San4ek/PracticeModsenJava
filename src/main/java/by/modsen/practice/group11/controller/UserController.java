package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.UserRequest;
import by.modsen.practice.group11.model.dto.response.UserResponse;
import by.modsen.practice.group11.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Admin panel controller")
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @PostMapping("/create")
    @Operation(summary = "Create user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userRequest));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable UUID id) {

        userService.deleteUser(id);
    }
}
