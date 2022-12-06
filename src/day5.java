import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day5 {

    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day5";
//    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day5-test";
    File file = new File(filePath);

    ArrayList<CrateStack> crates = new ArrayList<>();

    ArrayList<Action> actions = new ArrayList<>();

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<char[]> cratesInput = new ArrayList<>();
        boolean cratesLoaded = false;
        while (sc.hasNext()) {
            String s = sc.nextLine();

            // All crates loaded
            if (s.equals("") && !cratesLoaded) {
                crates = CrateStack.processCratesString(cratesInput);
                cratesLoaded = true;
            } else {
                if (!cratesLoaded){
                    cratesInput.add(s.toCharArray());
                } else {
                    actions.add(new Action(s));
                }
            }

        }
        sc.close();
    }

    public void processAction(){
        for (Action a: actions) {
            crates = a.processAction(crates);
        }
    }

    public void processAction2(){
        for (Action a: actions) {
            crates = a.processAction2(crates);
        }
    }

    public String getTopCrates(){
        StringBuilder stringBuilder = new StringBuilder();

//        for (CrateStack c: crates) {
//            stringBuilder.append(c.getTopCrate());
//        }
        for (CrateStack crate : crates) {
            stringBuilder.append(crate.getTopCrate());
        }

        return stringBuilder.toString();
    }



    public static void main(String[] args) throws FileNotFoundException {
        day5 d = new day5();
        d.loadData();

        // d.processAction(); //Day1
        d.processAction2();

        System.out.println("TopCrates: "+d.getTopCrates());

    }

    @Override
    public String toString() {
        return "crates=" + crates;
    }
}

class Action {
    int countToMove;
    int source;
    int destination;

    public Action(String input){
        Scanner sc = new Scanner(input);

        if (sc.hasNext()){
            sc.next();
            countToMove = sc.nextInt();
            sc.next();
            source = sc.nextInt()-1;
            sc.next();
            destination = sc.nextInt()-1;
        }

    }

    public ArrayList<CrateStack> processAction(ArrayList<CrateStack> crates){
        CrateStack sourceStack = crates.get(source);
        CrateStack destinationStack = crates.get(destination);

        for (int i = 0; i < countToMove; i++) {
            Character c = sourceStack.removeCrate();
            destinationStack.addCrate(c);
        }

        // Update crates array
        crates.set(source, sourceStack);
        crates.set(destination, destinationStack);

        return crates;
    }

    public ArrayList<CrateStack> processAction2(ArrayList<CrateStack> crates){
        CrateStack sourceStack = crates.get(source);
        CrateStack destinationStack = crates.get(destination);

        ArrayList<Character> stack = sourceStack.removeCrate(countToMove);
        destinationStack.addCrate(stack);

        crates.set(source, sourceStack);
        crates.set(destination, destinationStack);

        return crates;
    }

    @Override
    public String toString() {
        return "Action{" +
                "countToMove=" + countToMove +
                ", source=" + source +
                ", destination=" + destination +
                '}';
    }
}

class CrateStack {
    private final ArrayList<Character> crates;

    public CrateStack() {
        this.crates = new ArrayList<>();
    }

    public void addCrate(Character crate) {
        this.crates.add(crate);
    }

    public void addCrate(ArrayList<Character> newCrates){

        for (int i = newCrates.size()-1; i != -1; i--) {
            crates.add(newCrates.get(i));
        }
    }
    
    public Character removeCrate(){
        Character crateLetter = crates.get(crates.size()-1);
        crates.remove(crates.size()-1);
        return crateLetter;
    }

    public ArrayList<Character> removeCrate(int moveCount){
        ArrayList<Character> movedCrates = new ArrayList<>();

        for (int i = 0; i < moveCount; i++) {
            movedCrates.add(crates.get(crates.size()-1));
            crates.remove(crates.size()-1);
        }

        return movedCrates;
    }

    public Character getTopCrate(){
        return crates.get(crates.size()-1);
    }

    static public ArrayList<CrateStack> processCratesString(ArrayList<char[]> row) {
        ArrayList<CrateStack> crates = new ArrayList<>();
        // Find how many columns total there are
        char[] lastRow = row.get(row.size() - 1);
        int lastRowLength = lastRow.length-1;
        int totalColumns = Integer.parseInt(String.valueOf(lastRow[lastRowLength]));

        for (int c = 0; c < totalColumns; c++) {
            int textIndex = c*4+1;
            crates.add(new CrateStack());
            // Iterate the rows, -2 to consider row 0 and row for numbers
            for (int r = row.size()-2; r !=-1; r--) {
                if (row.get(r)[textIndex] != ' ') {
                    crates.get(c).addCrate(row.get(r)[textIndex]);
                }
            }
        }

        return crates;
    }
    
    @Override
    public String toString() {
        return "CrateStack{" +
                "crates=" + crates +
                '}';
    }
}