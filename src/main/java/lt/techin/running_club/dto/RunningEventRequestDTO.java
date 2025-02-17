package lt.techin.running_club.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record RunningEventRequestDTO(
        @NotNull
        @Size(min = 5, message = "username must have at least 6 characters.")
        String name,
        @NotNull
        @FutureOrPresent(message = "The date can't be in the past.")
        LocalDate calendarDate,
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9_\\-]+$", message = "No special symbols allowed. Only letters and numbers.")
        String location,
        @Min(value = 1, message = "There has to be at least 1 participant")
        int maxParticipants) {

}
