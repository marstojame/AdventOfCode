import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day3 {

    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day3";
//    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day3-test";
    File file = new File(filePath);

    ArrayList<Rucksack> rucksacks = new ArrayList<>();

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String s = sc.nextLine();

            int length = s.length()/2;
            String a = s.substring(0,length);
            String b = s.substring(length);


            rucksacks.add(new Rucksack(s,a,b));

        }
        sc.close();
    }

    public int processRucksacks(){
        int totalCount = 0;
        for (Rucksack r: rucksacks) {
            r.findDuplicates();
            totalCount += r.processDuplicates();
        }
        return totalCount;
    }

    public int findBadges() {
        int count = 0;
        int index = 0;
        int x = 0;
        while (index!=rucksacks.size()){
            Rucksack r1 = rucksacks.get(index);
            Rucksack r2 = rucksacks.get(index+1);
            Rucksack r3 = rucksacks.get(index+2);

            count += r1.findBadges(r2.fullBag, r3.fullBag);
            index+=3;
            x++;

        }
        return count;
    }


    public static void main(String[] args) throws FileNotFoundException {
        day3 d = new day3();
        d.loadData();


        System.out.println("badges score:"+d.processRucksacks());
        System.out.println("badges score:"+d.findBadges());


    }
}

class Rucksack {
    String fullBag;
    char[] bag1;
    char[] bag2;
    char[] duplicates;
    int duplicateValue = 0;

    public Rucksack(String fullBag, String bag1, String bag2) {
        this.fullBag = fullBag;
        this.bag1 = bag1.toCharArray();
        this.bag2 = bag2.toCharArray();
    }


    public void findDuplicates(){
        StringBuilder dup = new StringBuilder();
        for (char c : bag1) {
            if (Arrays.toString(bag2).indexOf(c) != -1 && dup.indexOf(String.valueOf(c)) == -1) {
                dup.append(c);
            }
        }
        duplicates = dup.toString().toCharArray();
    }

    public int findBadges(String sack1, String sack2){
        for (char c : fullBag.toCharArray()) {
            if (sack1.indexOf(c) != -1 && sack2.indexOf(c) != -1) {
                return convertToInt(c);
            }
        }

        System.out.println("Didn't find");
        return 0;
    }

    public int processDuplicates(){
        for (char c: duplicates) {
            duplicateValue+=convertToInt(c);
        }
        return duplicateValue;
    }

    public int convertToInt(char c){
        int val = 0;
        if (Character.isUpperCase(c)){
            val = c - '0' + 10;
        } else {
            val = c - '0' - 48;
        }
        return val;
    }


    @Override
    public String toString() {
        return "Rucksack{" +
                "bag1='" + Arrays.toString(bag1) + '\'' +
                ", bag2='" + Arrays.toString(bag2) + '\'' +
                ", duplicates='" + Arrays.toString(duplicates) + '\'' +
                '}';
    }
}