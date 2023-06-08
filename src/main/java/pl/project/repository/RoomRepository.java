package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelId(Long hotelId);

//    Room findByRoomNumber(int number);
    Room findByHotelIdAndNumber(Long hotelId, int number);

    List<Room> findByIsFree(boolean isFree);
    Room findByType(String type);
}
