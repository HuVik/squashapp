package pti.sb_squash_mvc.model;

public class User {

    private int id;
    private String name;
    private String pwd;
    private Roles role;
    private boolean entered;

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

	
}
