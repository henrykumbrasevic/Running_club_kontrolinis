package lt.techin.running_club.service;

import lt.techin.running_club.dto.RunningEventResponseDTO;
import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventService {

  private RunningEventRepository runningEventRepository;

  @Autowired
  public RunningEventService(RunningEventRepository runningEventRepository) {
    this.runningEventRepository = runningEventRepository;
  }

  public RunningEvent savedRunningEvent(RunningEvent runningEvent){
    return runningEventRepository.save(runningEvent);
  }

  public boolean existsById(long eventId) {
    return runningEventRepository.existsById(eventId);
  }

  public void deleteById(long eventId) {
    runningEventRepository.deleteById(eventId);
  }

  public List<RunningEvent> findAllRunningEvents() {
    return runningEventRepository.findAll();
  }

  public Optional<RunningEvent> findById(long eventId) {
    return runningEventRepository.findById(eventId);
  }
}
