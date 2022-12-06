import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day4 {

    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day4";
//        String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day4-test";
    File file = new File(filePath);

    ArrayList<Pair> elves = new ArrayList<>();

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String s = sc.nextLine();

            String numberOnly= s.replaceAll("[^0-9]", " ");

            Scanner s1 = new Scanner(numberOnly);

            int elf1Min = s1.nextInt();
            int elf1Max = s1.nextInt();
            int elf2Min = s1.nextInt();
            int elf2Max = s1.nextInt();

            elves.add(new Pair(elf1Min, elf1Max, elf2Min, elf2Max));

        }
        sc.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        day4 d = new day4();
        d.loadData();

        int fullContained = 0;
        int anyContained = 0;

        for (Pair p: d.elves) {
            fullContained+=p.fullContained;
            anyContained+=p.anyContained;
            System.out.println(p);
        }

        System.out.println("Full Contained: "+fullContained);
        System.out.println("Any Contained: "+anyContained);


    }
}

class Pair {
    ArrayList<Integer> elf1;
    ArrayList<Integer> elf2;

    int fullContained;
    int anyContained;

    public Pair(int elf1Min, int elf1Max, int elf2Min, int elf2Max) {
        this.elf1 = processInput(elf1Min, elf1Max);
        this.elf2 = processInput(elf2Min, elf2Max);

        fullContained = (findMatching(elf1, elf2) || findMatching(elf2, elf1)) ? 1 : 0;
        anyContained = (findMatchingAny(elf1, elf2) || findMatchingAny(elf2, elf1)) ? 1 : 0;
    }

    private ArrayList<Integer> processInput(int min, int max){
        ArrayList<Integer> input = new ArrayList<>();
        
        for (int i = min; i < max+1; i++) input.add(i);
        
        return input;
    }

    private boolean findMatching(ArrayList<Integer> a, ArrayList<Integer> b){

        for (int x: b) {
            if (!a.contains(x)){
                return false;
            }
        }
        return true;
    }

    private boolean findMatchingAny(ArrayList<Integer> a, ArrayList<Integer> b){

        for (int x: b) {
            if (a.contains(x)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "elf1=" + elf1 +
                ", elf2=" + elf2 +
                ", fullContained=" + fullContained +
                ", anyContained=" + anyContained +
                '}';
    }
}