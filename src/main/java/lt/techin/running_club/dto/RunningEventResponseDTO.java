package lt.techin.running_club.dto;

import java.time.LocalDate;

public record RunningEventResponseDTO(long id,
                                      String name,
                                      LocalDate calendarDate,
                                      String location,
                                      int maxParticipants) {


}
