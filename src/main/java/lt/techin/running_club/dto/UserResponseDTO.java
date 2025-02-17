package lt.techin.running_club.dto;

import lt.techin.running_club.model.Role;

import java.util.List;

public record UserResponseDTO(long id,
                              String username,
                              List<Role> roles) {
}
