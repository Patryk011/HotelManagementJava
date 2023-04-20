package pl.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
