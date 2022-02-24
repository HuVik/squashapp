package pti.sb_squash_mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="pwd")
    private String pwd;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
    private Roles role;
	
	@Column(name="entered")
    private boolean entered;
	
	
	@Column(name="pieceofentry")
	private int pieceOfEntry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public boolean isEntered() {
		return entered;
	}

	public void setEntered(boolean entered) {
		this.entered = entered;
	}

	public int getPieceOfEntry() {
		return pieceOfEntry;
	}

	public void setPieceOfEntry(int pieceOfEntry) {
		this.pieceOfEntry = pieceOfEntry;
	}
	
	

	
}
