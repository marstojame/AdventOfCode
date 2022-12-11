package day7a;

import day7.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day7a {

//    String filePath = "C:\Users\james.marston\IdeaProjects\AdventOfCode\inputFolder\\day7";
    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day7-test";
    java.io.File file = new File(filePath);
    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            Scanner line = new Scanner(sc.nextLine());
            ArrayList<String> lineArray = new ArrayList<>();

            while (line.hasNext()){
                lineArray.add(line.next());
            }
//            inputCommands.add(new Action(lineArray));
//            System.out.println(inputCommands.get(inputCommands.size()-1));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        day7a d = new day7a();
        d.loadData();

//        d.createSystem();
//        System.out.println(d.inputCommands.toString());


    }
}
