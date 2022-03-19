package pti.sb_squash_mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name ="address")
    private String address;
	
	@Column(name="rentperhour")
    private double rentPerHour;

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

    public double getRentPerHour() {
        return rentPerHour;
    }

    public void setRentPerHour(double rentPerHour) {
        this.rentPerHour = rentPerHour;
    }

	@Override
	public String toString() {
		return address + "| bérleti díj: " + rentPerHour + " Ft";
	}
    
    
}
