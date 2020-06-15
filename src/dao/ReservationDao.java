package dao;

import entites.Reservation;

import java.sql.*;
import java.time.LocalDate;

public class ReservationDao extends BaseDao<Reservation>{
    private static final String RESERVATIONS_TABLE_NAME = "reservations";
    private static ReservationDao INSTANCE = null;

    private ReservationDao() {
    }

    public static ReservationDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ReservationDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReservationDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM reservations " +
                "WHERE id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM reservations;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO reservations (room_id, day_of_arrival, day_of_departure, user_id) " +
                "VALUES (?, ?, ?, ?)";
    }


    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Reservation object) throws SQLException {
        statement.setLong(1, object.getRoom().getId());
        statement.setString(2, object.getDayOfArrival().toString());
        statement.setString(3, object.getDayOfDeparture().toString());
        statement.setLong(4, object.getUserId());
    }

    @Override
    public Reservation createFromResultSet(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong(RESERVATIONS_TABLE_NAME + ".id"));
        reservation.setRoom(RoomDao.getInstance().findById(resultSet.getLong(RESERVATIONS_TABLE_NAME + ".room_id")).get());
        reservation.setDayOfArrival( LocalDate.parse(resultSet.getString(RESERVATIONS_TABLE_NAME + ".day_of_arrival")));
        reservation.setDayOfDeparture(LocalDate.parse(resultSet.getString(RESERVATIONS_TABLE_NAME + ".day_of_departure")));
        return reservation;
    }
}
