package service;

import model.Candidate;
import model.Voter;
import util.FileUtil;
import java.util.*;

public class ElectionService {
    private static final String CAND_FILE = "candidates.txt";
    private Map<String, Candidate> candidates = new LinkedHashMap<>();

    public ElectionService() {
        loadCandidates();
    }

    private void loadCandidates() {
        for (String l : FileUtil.readAllLines(CAND_FILE)) {
            Candidate c = Candidate.fromString(l);
            if (c != null) candidates.put(c.getId(), c);
        }
    }

    private void saveCandidates() {
        List<String> out = new ArrayList<>();
        for (Candidate c : candidates.values()) out.add(c.toString());
        FileUtil.writeAllLines(CAND_FILE, out);
    }

    public String addCandidate(String name, String party) {
        String id = "C" + (candidates.size() + 1);
        Candidate c = new Candidate(id, name, party, 0);
        candidates.put(id, c);
        saveCandidates();
        return id;
    }

    public void removeCandidate(String id) {
        candidates.remove(id);
        saveCandidates();
    }

    public void castVote(Voter v, String candidateId, AuthService auth) {
        Candidate c = candidates.get(candidateId);
        if (c == null) {
            System.out.println("Candidate not found.");
            return;
        }
        c.incrementVotes();
        saveCandidates();
        v.setHasVoted(true);
        auth.updateVoter(v);
        System.out.println("Vote cast for: " + c.getName());
    }

    public void showCandidates() {
        System.out.println("\nCandidates:");
        for (Candidate c : candidates.values())
            System.out.println(c.getId() + ". " + c.getName() + " (" + c.getParty() + ")");
    }

    public void showResults() {
        System.out.println("\n--- Election Results ---");
        List<Candidate> list = new ArrayList<>(candidates.values());
        list.sort((a,b) -> Integer.compare(b.getVotes(), a.getVotes()));
        for (Candidate c : list)
            System.out.println(c.getName() + " - " + c.getVotes() + " votes");
    }
}
