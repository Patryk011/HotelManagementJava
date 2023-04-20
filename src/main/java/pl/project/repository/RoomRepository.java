package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
