package pl.project.mapper;

import org.springframework.stereotype.Component;
import pl.project.dto.ReservationDTO;
import pl.project.entity.Customer;
import pl.project.entity.Hotel;
import pl.project.entity.Reservation;
import pl.project.entity.Room;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

        public ReservationDTO mapToDTO(Reservation reservation) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setId(reservation.getId());
            reservationDTO.setCustomerId(reservation.getCustomer().getId());
            reservationDTO.setRoomId(reservation.getRoom().getId());
            reservationDTO.setHotelId(reservation.getHotel().getId());
            reservationDTO.setStartDate(reservation.getStartDate());
            reservationDTO.setEndDate(reservation.getEndDate());
            reservationDTO.setStatus(reservation.getStatus());
            return reservationDTO;
        }

        public List<ReservationDTO> mapToDto(Collection<Reservation> reservations) {
            return reservations.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        public Reservation mapFromDTO(ReservationDTO reservationDTO) {
            Reservation reservation = new Reservation();



            Hotel hotel = new Hotel();
            hotel.setId(reservationDTO.getHotelId());
            Room room = new Room();
            room.setId(reservationDTO.getRoomId());
            Customer customer = new Customer();
            customer.setId(reservationDTO.getCustomerId());
            reservation.setCustomer(customer);
            reservation.setRoom(room);
            reservation.setHotel(hotel);
            reservation.setStartDate(reservationDTO.getStartDate());
            reservation.setEndDate(reservationDTO.getEndDate());
            reservation.setStatus(reservationDTO.getStatus());
            return reservation;
        }

        public Reservation mapFromDTO(Reservation reservation, ReservationDTO reservationDTO) {

            Hotel hotel = new Hotel();
            hotel.setId(reservationDTO.getHotelId());
            Room room = new Room();
            room.setId(reservationDTO.getRoomId());
            Customer customer = new Customer();
            customer.setId(reservationDTO.getCustomerId());


            reservation.setCustomer(customer);
            reservation.setRoom(room);
            reservation.setHotel(hotel);
            reservation.setStartDate(reservationDTO.getStartDate());
            reservation.setEndDate(reservationDTO.getEndDate());
            reservation.setStatus(reservationDTO.getStatus());
            return reservation;
        }
    }
