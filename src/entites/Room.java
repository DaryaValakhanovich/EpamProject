package entites;

public class Room extends BaseEntity{
    private RoomType type;
    private int NumberOfPlaces;

    public Room() {
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getNumberOfPlaces() {
        return NumberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        NumberOfPlaces = numberOfPlaces;
    }

    @Override
    public String toString() {
        return "Room{" + super.toString() +
                "type=" + type +
                ", NumberOfPlaces=" + NumberOfPlaces +
                '}';
    }
}
