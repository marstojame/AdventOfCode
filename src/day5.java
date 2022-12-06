import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day5 {

//    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day5";
        String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day5-test";
    File file = new File(filePath);

    ArrayList<CrateStack> crates = new ArrayList<>();

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<char[]> cratesInput = new ArrayList<>();
        boolean cratesLoaded = false;
        while (sc.hasNext()) {
            String s = sc.nextLine();

            // All crates loaded
            if (s.equals("")) {
                processCratesString(cratesInput);
                cratesLoaded = true;
                System.out.println(crates.toString());
            }
            if (!cratesLoaded){
                cratesInput.add(s.toCharArray());
            } else {
//                System.out.println(s);
            }




        }
        sc.close();
    }

    private void processCratesString(ArrayList<char[]> row) {
        // Find how many columns total there are
        char[] lastRow = row.get(row.size() - 1);
        int lastRowLength = lastRow.length-1;
        int totalColumns = Integer.parseInt(String.valueOf(lastRow[lastRowLength]));


        System.out.println("rows: "+(row.size()-1));
        System.out.println("columns: "+totalColumns);

//        int currentrow = row.size()-2;
//        System.out.println(row.get(currentrow));


        for (int c = 0; c < totalColumns; c++) {
            int textIndex = c*4+1;
            crates.add(new CrateStack());
            // Iterate the rows, -2 to consider row 0 and row for numbers
            for (int r = row.size()-2; r !=-1; r--) {
//                System.out.print(row.get(r)[textIndex]);
                if (row.get(r)[textIndex] != ' ') {
                    crates.get(c).addCrate(row.get(r)[textIndex]);
                }
            }
        }

        for (int i = 0; i < row.size(); i++) {
            System.out.println(row.get(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        day5 d = new day5();
        d.loadData();


//        System.out.println("badges score:"+d.processRucksacks());
//        System.out.println("badges score:"+d.findBadges());


    }

}

class CrateStack {
    private ArrayList<Character> crates;

    public CrateStack() {
        this.crates = new ArrayList<>();
    }

    public void addCrate(Character crate) {
        this.crates.add(crate);
    }
    
    public Character removeCrate(){
        Character crateLetter = crates.get(crates.size()-1);
        crates.remove(crates.size()-1);
        return crateLetter;
    }
    
    @Override
    public String toString() {
        return "CrateStack{" +
                "crates=" + crates +
                '}';
    }
}