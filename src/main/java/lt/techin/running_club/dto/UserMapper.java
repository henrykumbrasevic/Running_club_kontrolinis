package lt.techin.running_club.dto;


import lt.techin.running_club.model.User;

import java.util.List;

public class UserMapper {


    public static List<UserResponseDTO> toUserDTOList(List<User> users) {
        List<UserResponseDTO> result = users.stream().map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles())).toList();

        return result;
    }

    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(userRequestDTO.password());
        user.setRoles(userRequestDTO.roles());
        return user;
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles());
    }

    public static void updateUserFromDTO( User user, UserRequestDTO userRequestDTO) {
        user.setPassword(userRequestDTO.password());
        user.setUsername(userRequestDTO.username());
        user.setRoles(userRequestDTO.roles());
    }
}
