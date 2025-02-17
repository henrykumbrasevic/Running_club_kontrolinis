package lt.techin.running_club.service;

import lt.techin.running_club.dto.UserRequestDTO;
import lt.techin.running_club.model.Role;
import lt.techin.running_club.model.User;
import lt.techin.running_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> findUserById(long id) {
    return userRepository.findById(id);
  }

  public boolean existsUserById(long id) {
    return userRepository.existsById(id);
  }

  public void deleteUserById(long id) {
    userRepository.deleteById(id);
  }

}
