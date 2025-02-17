package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.RunningEventMapper;
import lt.techin.running_club.dto.RunningEventRequestDTO;
import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.service.RunningEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService) {
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events")
  public ResponseEntity<?> addEvent (@RequestBody @Valid RunningEventRequestDTO runningEventRequestDTO){

    RunningEvent savedRunningEvent = runningEventService.savedRunningEvent(RunningEventMapper.toRunningEvent(runningEventRequestDTO));

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRunningEvent.getId()).toUri()).body(RunningEventMapper.toRunningEventResponseDTO(savedRunningEvent));
  }

  @DeleteMapping("/api/events/{eventId}")
  public ResponseEntity<Void> deleteRunningEvent(@PathVariable long eventId) {
    if (!runningEventService.existsById(eventId)) {
      return ResponseEntity.notFound().build();
    }
    runningEventService.deleteById(eventId);
    return ResponseEntity.noContent().build();
  }

}
