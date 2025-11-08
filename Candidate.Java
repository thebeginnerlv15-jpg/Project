package model;

import java.io.Serializable;


public class Candidate implements Serializable {
    private String id;
    private String name;
    private String party;
    private int votes;


    public Candidate(String id, String name, String party, int votes) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.votes = votes;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getParty() { return party; }
    public int getVotes() { return votes; }
    public void incrementVotes() { votes++; }


    @Override
    public String toString() {
        return id + "," + name + "," + party + "," + votes;
    }


    public static Candidate fromString(String line) {
        String[] p = line.split(",");
        if (p.length < 4) return null;
        return new Candidate(p[0], p[1], p[2], Integer.parseInt(p[3]));
    }
}
