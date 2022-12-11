package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day7 {

    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day7";
//    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day7-test";
    java.io.File file = new File(filePath);
    
    ArrayList<Action> inputCommands = new ArrayList<>();
    
//    ArrayList<String>  = new ArrayList<>();
    
    ArrayList<Directory> allDirectories = new ArrayList<>();

    Directory system;
    
    
    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        
        while (sc.hasNextLine()){
            Scanner line = new Scanner(sc.nextLine());
            ArrayList<String> lineArray = new ArrayList<>();
            
            while (line.hasNext()){
                lineArray.add(line.next());
            }
            inputCommands.add(new Action(lineArray));
//            System.out.println(inputCommands.get(inputCommands.size()-1));
        }
    }
    
    public void createSystem(){
        system = new Directory("/");
        system.parent = system;
//        system

        FileSystem currentItem = system;
        int step = 1;
        for (Action c: inputCommands) {
//            System.out.println("Step:"+step++);
//            System.out.println("Action:"+c);
            if (c.action == c.action.COMMAND){
                if (c.input.get(1).equals("cd")){
                    if (c.input.get(2).equals("/")) {
                        currentItem = system;
                    } else {
                        currentItem = currentItem.ChangeDirectory(c.input);
                    }
                }
                if (c.input.get(1).equals("ls")){
                    currentItem.DisplayChildren();
                }
            }
            if (c.action == c.action.FOLDER){
                currentItem = currentItem.CreateDirectory(c.input, currentItem);
//                System.out.println(currentItem.children);
            }
            if (c.action == c.action.FILE){
                currentItem = currentItem.CreateFile(c.input, currentItem);
//                System.out.println(currentItem.children);
            }
        }
    }
    
    public void GetAllDirectories(){
        allDirectories = system.GetDirectories(allDirectories);
    }
    
    public void CalculateChildrenSize(){
        system.FindChildrenSize();
    }
    
    public int FindDirToDelete(){
        int freespace = system.spaceConsumed;
        System.out.println("Space remaining: "+(70000000 - freespace));
        System.out.println("Need at least: "+(30000000-(70000000 - freespace)));

        ArrayList<Integer> sizes = new ArrayList<>();
        for (Directory d: allDirectories){
//            System.out.println(d.spaceConsumed);
            if (d.spaceConsumed > (30000000-(70000000 - freespace))){
                sizes.add(d.spaceConsumed);
            }
        }
//        System.out.println(sizes);
        return Collections.min(sizes);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        day7 d = new day7();
        d.loadData();
        
        d.createSystem();
        
//        System.out.println(d.system.toString());
        d.CalculateChildrenSize();
        
        d.GetAllDirectories();
        
        System.out.println(d.FindDirToDelete());
        

    }
}
class Action {
    enum action{
        COMMAND,
        FOLDER,
        FILE
    }
    
    action action;
    ArrayList<String> input;

    public Action(ArrayList<String> command) {
        input = command;
        if (command.get(0).equals("$")){
//            System.out.println("Action"+command);
            action = action.COMMAND;
        }
        else if (command.get(0).equals("dir")){
//            System.out.println("Dir"+command);
            action = action.FOLDER;
        } else {
            try {
                int size = Integer.parseInt(command.get(0));
//                FileSystem.out.println("File"+Action);
                action = action.FILE;
            } catch (Exception e){
                System.out.println("Item not found");
            }
        }
        
        
    }

    public FileSystemPair processCommand(FileSystemPair pair){
        if (action == action.COMMAND){
          if (input.get(1).equals("cd")){
              
          }
        } 
        if (action == action.FOLDER){
            System.out.println("folder");
        }
        if (action == action.FILE){
            
        }
        
        return pair;
    }

    @Override
    public String toString() {
        return "\nAction{" +
                "action=" + action +
                ", input=" + input +
                "}";
    }
}

class FileSystemPair{
    private final ArrayList<String> path;
    private final FileSystem system;

    public FileSystemPair(ArrayList<String> path, FileSystem system) {
        this.path = path;
        this.system = system;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public FileSystem getSystem() {
        return system;
    }
}