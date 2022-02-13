package pti.sb_squash_mvc.model;

public class Admin extends User{

    private int id;
    private String name;
    private String pwd;
    private String role;
    private int entered;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEntered() {
        return entered;
    }

    public void setEntered(int entered) {
        this.entered = entered;
    }

}
