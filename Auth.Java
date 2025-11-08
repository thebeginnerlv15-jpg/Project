package service;


import model.Voter;
import util.FileUtil;
import java.util.*;


public class AuthService {
    private static final String VOTER_FILE = "voters.txt";


    public String generateVoterId() {
        List<String> lines = FileUtil.readAllLines(VOTER_FILE);
        int max = 1000;
        for (String l : lines) {
            Voter v = Voter.fromString(l);
            if (v == null) continue;
            try {
                int n = Integer.parseInt(v.getId().replaceAll("[^0-9]", ""));
                if (n > max) max = n;
            } catch (Exception ignored) {}
        }
        return "V" + (max + 1);
    }


    public boolean registerVoter(String name, String password) {
        String id = generateVoterId();
        Voter v = new Voter(id, name, password, false);
        FileUtil.appendLine(VOTER_FILE, v.toString());
        System.out.println("Registered voter ID: " + id);
        return true;
    }


    public Voter loginVoter(String id, String password) {
        for (String l : FileUtil.readAllLines(VOTER_FILE)) {
            Voter v = Voter.fromString(l);
            if (v != null && v.getId().equals(id) && v.getPassword().equals(password))
                return v;
        }
        return null;
    }


    public void updateVoter(Voter updated) {
        List<String> lines = FileUtil.readAllLines(VOTER_FILE);
        List<String> newLines = new ArrayList<>();
        for (String l : lines) {
            Voter v = Voter.fromString(l);
            if (v == null) continue;
            if (v.getId().equals(updated.getId())) newLines.add(updated.toString());
            else newLines.add(l);
        }
        FileUtil.writeAllLines(VOTER_FILE, newLines);
    }
}
