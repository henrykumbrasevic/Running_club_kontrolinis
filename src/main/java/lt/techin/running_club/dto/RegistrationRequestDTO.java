package lt.techin.running_club.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.running_club.model.User;

public record RegistrationRequestDTO(
        @NotNull
        User user) {
}
