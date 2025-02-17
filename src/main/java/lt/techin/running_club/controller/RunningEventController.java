package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.*;
import lt.techin.running_club.model.Registration;
import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.model.User;
import lt.techin.running_club.service.RegistrationService;
import lt.techin.running_club.service.RunningEventService;
import lt.techin.running_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final UserService userService;
  private final RegistrationService registrationService;
  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(UserService userService, RegistrationService registrationService, RunningEventService runningEventService) {
    this.userService = userService;
    this.registrationService = registrationService;
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events")
  public ResponseEntity<?> addEvent (@RequestBody @Valid RunningEventRequestDTO runningEventRequestDTO){

    RunningEvent savedRunningEvent = runningEventService.savedRunningEvent(RunningEventMapper.toRunningEvent(runningEventRequestDTO));

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRunningEvent.getId()).toUri()).body(RunningEventMapper.toRunningEventResponseDTO(savedRunningEvent));
  }

  @DeleteMapping("/events/{eventId}")
  public ResponseEntity<Void> deleteRunningEvent(@PathVariable long eventId) {
    if (!runningEventService.existsById(eventId)) {
      return ResponseEntity.notFound().build();
    }
    runningEventService.deleteById(eventId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/events")
  public ResponseEntity<List<RunningEventResponseDTO>> getRunningEvents() {
    return ResponseEntity.ok(RunningEventMapper.toRunningEventResponseDTOList(runningEventService.findAllRunningEvents()));
  }

  @PostMapping("/events/{eventId}/register")
  public ResponseEntity<?> registerForEvent(@PathVariable long eventId, @Valid @RequestBody RegistrationRequestDTO registrationRequestDTO, Authentication authentication) {
    Optional<RunningEvent> runningEvent = runningEventService.findById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Optional<User> user = userService.findById(registrationRequestDTO.user().getId());
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    Registration registration = new Registration();
    registration.setUser(user.get());
    registration.setRunningEvent(runningEvent.get());
    registration.setRegistrationDate(LocalDateTime.now());
    registrationService.save(registration);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(registration.getId())
            .toUri()).body(RegistrationMapper.toRegistrationResponseDTO(registration));
  }

  @GetMapping("/events/{eventId}/participants")
  public ResponseEntity<?> getParticipants(@PathVariable long eventId) {
    Optional<RunningEvent> runningEvent = runningEventService.findById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    List<User> participants = runningEvent.get().getRegistrations().stream()
            .map(Registration::getUser).toList();

    return ResponseEntity.ok(UserMapper.toParticipantsResponseDTOList(participants));
  }

}
