package class_problems;

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] options = {"Rock", "Paper", "Scissors"};

        int wins = 0, losses = 0, draws = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Round " + i + " - Enter your move (Rock, Paper, Scissors): ");
            String player = sc.next();
            String computer = options[rand.nextInt(3)];

            System.out.println("Computer chose: " + computer);

            if (player.equalsIgnoreCase(computer)) {
                System.out.println("Result: Draw\n");
                draws++;
            } else if ((player.equalsIgnoreCase("Rock") && computer.equals("Scissors")) ||
                    (player.equalsIgnoreCase("Paper") && computer.equals("Rock")) ||
                    (player.equalsIgnoreCase("Scissors") && computer.equals("Paper"))) {
                System.out.println("Result: Player Wins\n");
                wins++;
            } else {
                System.out.println("Result: Computer Wins\n");
                losses++;
            }
        }

        double winPercent = (wins * 100.0) / 5;
        System.out.println("--- Summary ---");
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws + " | Win %: " + winPercent + "%");

        sc.close();
    }
}