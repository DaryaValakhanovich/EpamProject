package entites;

import java.time.LocalDate;

public class Reservation extends BaseEntity{
    private Room room;
    private LocalDate dayOfArrival;
    private LocalDate dayOfDeparture;
    private long userId;

    public Reservation() {
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDayOfArrival() {
        return dayOfArrival;
    }

    public void setDayOfArrival(LocalDate dayOfArrival) {
        this.dayOfArrival = dayOfArrival;
    }

    public LocalDate getDayOfDeparture() {
        return dayOfDeparture;
    }

    public void setDayOfDeparture(LocalDate dayOfDeparture) {
        this.dayOfDeparture = dayOfDeparture;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "room=" + room +
                ", dayOfArrival=" + dayOfArrival +
                ", dayOfDeparture=" + dayOfDeparture +
                '}';
    }
}
