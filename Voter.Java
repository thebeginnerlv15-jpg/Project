package model;
import java.io.Serializable;


public class Voter implements Serializable {
    private String id;
    private String name;
    private String password;
    private boolean hasVoted;


    public Voter(String id, String name, String password, boolean hasVoted) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hasVoted = hasVoted;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public boolean hasVoted() { return hasVoted; }
    public void setHasVoted(boolean hasVoted) { this.hasVoted = hasVoted; }


    @Override
    public String toString() {
        return id + "," + name + "," + password + "," + hasVoted;
    }


    public static Voter fromString(String line) {
        String[] p = line.split(",");
        if (p.length < 4) return null;
        return new Voter(p[0], p[1], p[2], Boolean.parseBoolean(p[3]));
    }
}
