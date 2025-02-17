package lt.techin.running_club.repository;

import lt.techin.running_club.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningEventRepository extends JpaRepository <RunningEvent, Long> {

}
