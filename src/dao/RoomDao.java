package dao;

import connection.ConnectionManager;
import entites.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends BaseDao<Room>{
    private static final String ROOM_TABLE_NAME = "rooms";
    private static RoomDao INSTANCE = null;

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        if (INSTANCE == null) {
            synchronized (RoomDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Room room) throws SQLException {
        statement.setInt(1, room.getNumberOfPlaces());
        statement.setLong(2, room.getType().getId());
    }

    @Override
    public Room createFromResultSet(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong(ROOM_TABLE_NAME + ".id"));
        room.setNumberOfPlaces(resultSet.getInt(ROOM_TABLE_NAME + ".number_of_places"));
        room.setType(RoomTypeDao.getInstance().findById(resultSet.getLong(ROOM_TABLE_NAME + ".room_type_id")).get());
        return room;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM rooms " +
                "WHERE rooms.id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM rooms ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO rooms (number_of_places, room_type_id) " +
                "VALUES (?, ?)";
    }

    public List<Room> findEmptyRooms(LocalDate dayOfArrival, LocalDate dayOfDeparture, long roomTypeId, int numberOfPlaces) {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from rooms r where "
                            + "r.room_type_id=? and r.number_of_places=? "
                            + "and r.id NOT IN (SELECT r1.id FROM reservations a join rooms r1 on r1.id = a.room_id "
                            + "where ? < a.day_of_departure and ? > a.day_of_arrival "
                            + "or a.day_of_arrival < ? and a.day_of_arrival > ?)")) {
                preparedStatement.setLong(1, roomTypeId);
                preparedStatement.setInt(2, numberOfPlaces);
                preparedStatement.setString(3, dayOfArrival.toString());
                preparedStatement.setString(4, dayOfArrival.toString());
                preparedStatement.setString(5, dayOfDeparture.toString());
                preparedStatement.setString(6, dayOfArrival.toString());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Room room = new Room();
                        room.setId(resultSet.getLong("id"));
                        room.setNumberOfPlaces(resultSet.getInt("number_of_places"));
                        room.setType(RoomTypeDao.getInstance().findById(resultSet.getLong("room_type_id")).get());
                        rooms.add(room);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}

