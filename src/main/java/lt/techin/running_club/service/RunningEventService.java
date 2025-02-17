package lt.techin.running_club.service;

import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
