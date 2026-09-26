package class_problems;
import java.util.*;

class Question {
    String text, correctAnswer;
    public Question(String text, String correctAnswer) { this.text = text; this.correctAnswer = correctAnswer; }
}

class Examination {
    String title;
    List questions;
    public Examination(String title, List questions) { this.title = title; this.questions = questions; }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        List qs = Arrays.asList(new Question("Q1", "A"), new Question("Q2", "B"));
        Examination mathQuiz = new Examination("Math Quiz", qs);

        System.out.println("Examination 'Math Quiz' started by Student.");
        Map answers = new HashMap<>();
        answers.put(1, "A");
        System.out.println("Question 1 answered with 'A'");
        answers.put(2, "C");
        System.out.println("Question 2 answered with 'C'");

        int correct = 0;
        if (answers.get(1).equals(mathQuiz.questions.get(0).correctAnswer)) correct++;
        if (answers.get(2).equals(mathQuiz.questions.get(1).correctAnswer)) correct++;

        System.out.println("Examination 'Math Quiz' submitted successfully.");
        System.out.println("Result for 'Math Quiz' attempt: " + correct + "/" + mathQuiz.questions.size() + " correct.");
    }
}
