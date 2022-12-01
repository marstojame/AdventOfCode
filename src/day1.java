import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day1 {
    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day1";
    File file = new File(filePath);

    ArrayList<Integer> calories = new ArrayList<>();

    public day1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        int index = 0;
        int maxpos = 0;
        int subtotal = 0;

        while (sc.hasNextLine()) {
            String s = sc.nextLine();


            if (isInt(s)){
                int i = Integer.parseInt(s);
                subtotal+=i;
            } else {
                calories.add(subtotal);
                subtotal = 0;
                index++;

            }

        }
        sc.close();

        calories.sort(Collections.reverseOrder());
        System.out.println("1:"+calories.get(0));
        System.out.println("2:"+calories.get(1));
        System.out.println("3:"+calories.get(2));

        System.out.println((calories.get(0)+calories.get(1)+calories.get(2)));

    }

    private boolean isInt(String s) {
        try {
            int intValue = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        new day1();
    }
}
