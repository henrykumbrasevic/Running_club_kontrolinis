package lt.techin.running_club.dto;

import lt.techin.running_club.model.RunningEvent;

import java.util.List;

public class RunningEventMapper {

  public static RunningEvent toRunningEvent(RunningEventRequestDTO runningEventRequestDTO) {
    RunningEvent runningEvent = new RunningEvent(runningEventRequestDTO.name(), runningEventRequestDTO.calendarDate(), runningEventRequestDTO.location(), runningEventRequestDTO.maxParticipants());
    return runningEvent;

  }

  public static RunningEventResponseDTO toRunningEventResponseDTO(RunningEvent runningEvent) {
    return new RunningEventResponseDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants());
  }

  public static List<RunningEventResponseDTO> toRunningEventResponseDTOList(List<RunningEvent> runningEvents) {
    List<RunningEventResponseDTO> result = runningEvents.stream().map(event -> new RunningEventResponseDTO(event.getId(), event.getName(), event.getCalendarDate(), event.getLocation(), event.getMaxParticipants())).toList();

    return result;
  }
}
