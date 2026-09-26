package assigment_problems;
import java.util.*;

interface ScoringRule {
    double calculateFinalScore(double idea, double execution, double presentation);
}

class InnovationTrack implements ScoringRule {
    public double calculateFinalScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}

class OpenTrack implements ScoringRule {
    public double calculateFinalScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

enum HackathonState { OPEN, JUDGING, PUBLISHED }

class Team {
    String name;
    List members;
    String track;
    String projectName;
    double finalScore;
    boolean scored = false;

    public Team(String name, List members, String track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        HackathonState state = HackathonState.OPEN;
        Map teams = new HashMap<>();
        Set assignedStudents = new HashSet<>();
        Map rules = Map.of(
                "Innovation", new InnovationTrack(),
                "Open", new OpenTrack()
        );

        // 1. Team 'ByteBusters' registers with 3 members
        List members1 = Arrays.asList("Asha", "Ravi", "Neha");
        if (members1.size() >= 2 && members1.size() <= 4) {
            teams.put("ByteBusters", new Team("ByteBusters", members1, "Innovation"));
            System.out.println("Team ByteBusters registered (3 members, Innovation track).");
        }

        // 2. Team 'SoloCoder' registers with 1 member
        List members2 = Arrays.asList("Kiran");
        if (members2.size() >= 2 && members2.size() <= 4) {
            teams.put("SoloCoder", new Team("SoloCoder", members2, "Open"));
        } else {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
        }

        // 3. ByteBusters submits project 'SmartAttend'
        Team busters = teams.get("ByteBusters");
        busters.projectName = "SmartAttend";
        System.out.println("Project 'SmartAttend' submitted by ByteBusters.");

        // 4. Judge scores 'SmartAttend'
        double idea = 8, execution = 7, presentation = 9;
        double score = rules.get(busters.track).calculateFinalScore(idea, execution, presentation);
        busters.finalScore = score;
        busters.scored = true;
        System.out.printf("Score recorded for 'SmartAttend'. Final score: %.2f.\n", score);

        // 5. Organiser publishes results
        state = HackathonState.PUBLISHED;
        System.out.println("Results published.");

        // 6. Judge attempts to change idea score after publish
        if (state == HackathonState.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
        }
    }
}
