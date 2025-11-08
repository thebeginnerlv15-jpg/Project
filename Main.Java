package ui;

import model.Voter;
import service.AuthService;
import service.ElectionService;
import java.util.*;

public class MainApp {
    private static final String ADMIN_PASS = "admin123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();
        ElectionService election = new ElectionService();

        while (true) {
            System.out.println("\n=== Online Voting System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Register Voter");
            System.out.println("3. Login & Vote");
            System.out.println("4. View Results");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Admin password: ");
                    if (!sc.nextLine().equals(ADMIN_PASS)) {
                        System.out.println("Incorrect password.");
                        break;
                    }
                    adminMenu(sc, election);
                    break;
                case "2":
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Password: ");
                    String pw = sc.nextLine();
                    auth.registerVoter(name, pw);
                    break;
                case "3":
                    System.out.print("Voter ID: ");
                    String vid = sc.nextLine();
                    System.out.print("Password: ");
                    String vpw = sc.nextLine();
                    Voter v = auth.loginVoter(vid, vpw);
                    if (v == null) {
                        System.out.println("Invalid credentials.");
                        break;
                    }
                    if (v.hasVoted()) {
                        System.out.println("You have already voted.");
                        break;
                    }
                    election.showCandidates();
                    System.out.print("Enter candidate ID: ");
                    String cid = sc.nextLine();
                    election.castVote(v, cid, auth);
                    break;
                case "4":
                    election.showResults();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu(Scanner sc, ElectionService election) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Candidate");
            System.out.println("2. Remove Candidate");
            System.out.println("3. Show Candidates");
            System.out.println("4. Show Results");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Party: ");
                    String party = sc.nextLine();
                    String id = election.addCandidate(name, party);
                    System.out.println("Added candidate ID: " + id);
                    break;
                case "2":
                    System.out.print("Candidate ID to remove: ");
                    election.removeCandidate(sc.nextLine());
                    break;
                case "3":
                    election.showCandidates();
                    break;
                case "4":
                    election.showResults();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
