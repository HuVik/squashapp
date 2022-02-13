package pti.sb_squash_mvc.model;

public class Location {

    private int id;
    private String address;
    private int rentPerHour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRentPerHour() {
        return rentPerHour;
    }

    public void setRentPerHour(int rentPerHour) {
        this.rentPerHour = rentPerHour;
    }
}
