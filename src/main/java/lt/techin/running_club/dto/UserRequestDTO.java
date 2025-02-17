package lt.techin.running_club.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.running_club.model.Role;

import java.util.List;

public record UserRequestDTO(
        @NotNull
        @Pattern(regexp = "^[a-z0-9_\\-]+$", message = "username must consist only of lowercase letters.")
        @Size(min = 4, message = "username must have at least 4 characters.")
        String username,
        @NotNull
        @Size(min = 6, message = "password must have at least 6 characters.")
        String password,
        @NotNull
        List<Role> roles) {
}
