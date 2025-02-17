package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.UserMapper;
import lt.techin.running_club.dto.UserRequestDTO;
import lt.techin.running_club.dto.UserResponseDTO;
import lt.techin.running_club.model.User;
import lt.techin.running_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<?> addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {

    User savedUser = userService.save(userRequestDTO);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri())
            .body(UserMapper.toUserResponseDTO(savedUser));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    if (!userService.existsUserById(id)) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

}