package dao;

import entites.RoomType;

import java.sql.*;

public class RoomTypeDao extends BaseDao<RoomType>{
    private static final String TABLE_NAME = "room_types";
    private static RoomTypeDao INSTANCE = null;

    private RoomTypeDao() {}

    public static RoomTypeDao getInstance() {
        if (INSTANCE == null) {
            synchronized (RoomTypeDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomTypeDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String getSelectQueryById() {
        return "SELECT * FROM room_types " +
                "WHERE room_types.id = ?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO room_types (room_title, cost_for_one_night) " +
                "VALUES (?, ?)";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, RoomType roomType) throws SQLException {
        statement.setString(1, roomType.getTitle());
        statement.setInt(2, roomType.getCostForOneNight());
    }

    @Override
    public RoomType createFromResultSet(ResultSet resultSet) throws SQLException {
        RoomType roomType = new RoomType();
        roomType.setId(resultSet.getLong(TABLE_NAME + ".id"));
        roomType.setTitle( resultSet.getString(TABLE_NAME + ".room_title"));
        roomType.setCostForOneNight( resultSet.getInt(TABLE_NAME + ".cost_for_one_night"));
        return roomType;
    }

}
