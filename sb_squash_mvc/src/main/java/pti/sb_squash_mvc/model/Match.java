package pti.sb_squash_mvc.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="matches")
public class Match {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="homeplayerid")
    private User hPlayer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="awayplayerid")
    private User aPlayer;
	
	@Column(name="hplayerpoints")
    private int hPlayerPoints;
	
	@Column(name="aplayerpoints")
    private int aPlayerPoints;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="locationid")
    private Location location;
	
	@Column(name="date")
    private LocalDate date;
	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

   

	public User gethPlayer() {
		return hPlayer;
	}

	public void sethPlayer(User hPlayer) {
		this.hPlayer = hPlayer;
	}

	public User getaPlayer() {
		return aPlayer;
	}

	public void setaPlayer(User aPlayer) {
		this.aPlayer = aPlayer;
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

    

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate() {
		this.date = LocalDate.now();
	}

	
   
    
}
