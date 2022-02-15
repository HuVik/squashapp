package pti.sb_squash_mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="matches")
public class Match {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="hplayerid")
    private int hPlayerId;
	
	@Column(name="aplayerid")
    private int aPlayerId;
	
	@Column(name="hplayerpoints")
    private int hPlayerPoints;
	
	@Column(name="aplayerpoints")
    private int aPlayerPoints;
	
	@Column(name="locationid")
    private int locationId;
	
	@Column(name="date")
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
