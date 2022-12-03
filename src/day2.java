import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class day2 {
    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day2";
    File file = new File(filePath);

    ArrayList<Enum<move>> opponent = new ArrayList<>();
    ArrayList<Enum<move>> me = new ArrayList<>();

    int score = 0;

    enum move {
        PAPER,
        SCISSORS,
        ROCK
    }

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String them = sc.next();
            opponent.add(convertToEnum(them));

            String self = sc.next();
            me.add(convertToEnum2(convertToEnum(them),self));

        }
        sc.close();
    }

    public void compareArrays() {
        for (int i = 0; i < opponent.size(); i++) {
            Enum<move> o = opponent.get(i);
            Enum<move> m = me.get(i);

            score+=findWinner(o,m);

            if (move.ROCK.equals(m)) {
                score += 1;
            }
            if (move.PAPER.equals(m)) {
                score += 2;
            }
            if (move.SCISSORS.equals(m)) {
                score += 3;
            }
        }
    }

    // 0 = draw
    // 1 = win
    // 2 = lose
    public int findWinner(Enum<move> o, Enum<move> m){
        // Draw
        if (m.equals(o)){
            return 3;
        }
        // Win
        else if (m.equals(move.ROCK) && o.equals(move.SCISSORS) || m.equals(move.PAPER) && o.equals(move.ROCK) || m.equals(move.SCISSORS) && o.equals(move.PAPER)){
            return 6;
        }
        // Lose
        if (o.equals(move.ROCK) && m.equals(move.SCISSORS) || o.equals(move.PAPER) && m.equals(move.ROCK) || o.equals(move.SCISSORS) && m.equals(move.PAPER)){
            return 0;
        }

        return 50;
    }

    public move convertToEnum(String s){
        if (Objects.equals(s, "A") || Objects.equals(s, "X")){
            return move.ROCK;
        } else if (Objects.equals(s, "B") || Objects.equals(s, "Y")){
            return move.PAPER;
        } else {
            return move.SCISSORS;
        }
    }

    public Enum<move> convertToEnum2(Enum<move> them, String self){
        // Win
        if (self.equals("Z")){
            if (them.equals(move.ROCK)){
                return move.PAPER;
            } else
            if (them.equals(move.PAPER)){
                return move.SCISSORS;
            } else {
                return move.ROCK;
            }
        }
        // Lose
        else if (self.equals("X")){
            if (them.equals(move.ROCK)){
                return move.SCISSORS;
            } else
            if (them.equals(move.PAPER)){
                return move.ROCK;
            } else {
                return move.PAPER;
            }
        } //Draw
        else {
            return them;
        }
    }

// Rock:

    public static void main(String[] args) throws FileNotFoundException {
        day2 program = new day2();
        program.loadData();
        program.compareArrays();
        System.out.println(program.score);
    }
}
