package entites;

public class RoomType extends BaseEntity{
    private String title;
    private int costForOneNight;

    public RoomType() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCostForOneNight() {
        return costForOneNight;
    }

    public void setCostForOneNight(int costForOneNight) {
        this.costForOneNight = costForOneNight;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "title='" + title + '\'' +
                ", costForOneNight=" + costForOneNight +
                '}';
    }
}
