package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByType(String type);
}
