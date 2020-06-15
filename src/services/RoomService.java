package services;

import entites.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    public List<Room> findEmptyRooms(LocalDate localDateFrom, LocalDate localDateTo, int RoomTypeId, int numberOfPlaces);
}
