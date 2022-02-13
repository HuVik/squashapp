package pti.sb_squash_mvc.model;

public class Match {

    private int id;
    private int hPlayerId;
    private int aPlayerId;
    private int hPlayerPoints;
    private int aPlayerPoints;
    private int locationId;
    private int date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gethPlayerId() {
        return hPlayerId;
    }

    public void sethPlayerId(int hPlayerId) {
        this.hPlayerId = hPlayerId;
    }

    public int getaPlayerId() {
        return aPlayerId;
    }

    public void setaPlayerId(int aPlayerId) {
        this.aPlayerId = aPlayerId;
    }

    public int gethPlayerPoints() {
        return hPlayerPoints;
    }

    public void sethPlayerPoints(int hPlayerPoints) {
        this.hPlayerPoints = hPlayerPoints;
    }

    public int getaPlayerPoints() {
        return aPlayerPoints;
    }

    public void setaPlayerPoints(int aPlayerPoints) {
        this.aPlayerPoints = aPlayerPoints;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
