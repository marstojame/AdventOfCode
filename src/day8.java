import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day8 {

    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day8";
//    String filePath = "C:\\Users\\james.marston\\IdeaProjects\\AdventOfCode\\inputFolder\\day8-test";
    java.io.File file = new File(filePath);
    
    ArrayList<ArrayList<Integer>> trees = new ArrayList<>();
    
    ArrayList<Point> foundItems = new ArrayList<>();
    
    int totalRows;
    int totalCols;

    public void LoadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            char[] lineArr = sc.nextLine().toCharArray();
            ArrayList<Integer> line = new ArrayList<>();
            for (char c: lineArr) {
                line.add(Integer.parseInt(String.valueOf(c)));
            }
            
            trees.add(line);
        }
        totalCols=trees.size();
        totalRows=trees.get(0).size();
    }
    
    public int ScanEdges(){
        
        int items = 0;
        //Add edges to found points
        for (int y = 0; y < totalCols; y++) {
            for (int x = 0; x < totalRows; x++) {
                // If the item is on an edge
                if ((y ==0 || x ==0) || (x ==trees.size()-1 || y ==trees.size()-1)){
                    foundItems.add(new Point(x, y));
                    items++;
                } else {
                    Point p = new Point(x, y);
                    if (CheckLeft(p)||CheckRight(p)||CheckUp(p)||CheckDown(p)){
                        foundItems.add(new Point(x, y));
                        items++;
//                        System.out.println(new Point(x, y)+" Visible");
                    }
//                    if (CheckDown(p)){
//                        foundItems.add(new Point(x, y));
//                        items++;
//                        System.out.println(new Point(x, y)+" Visible, POS:"+FindPoint(new Point(x,y)));
//                    }
                }
            }
            
        }
        
        return items;
    }
    
    public int FindPoint(Point p){
        return trees.get(p.y).get(p.x);
    }
    
    public boolean CheckLeft(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = p.x; x > 0; x--) {
            stringBuilder.append(new Point(x-1,p.y));

            if (treeHeight <= FindPoint(new Point(x-1,p.y))){
                return false;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return visible;
    }

    public boolean CheckRight(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int x = p.x; x < totalCols-1; x++) {
            stringBuilder.append(new Point(x,p.y));
//            System.out.println("x"+x);
            if (treeHeight <= FindPoint(new Point(x+1,p.y))){
                return false;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return visible;
    }

    public boolean CheckUp(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int y = p.y; y > 0; y--) {
            stringBuilder.append(new Point(p.x,y-1));
//            System.out.println("x"+x);
            if (treeHeight <= FindPoint(new Point(p.x,y-1))){
                return false;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return visible;
    }

    public boolean CheckDown(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int y = p.y; y < totalCols-1; y++) {
            stringBuilder.append(new Point(p.x,y));
//            System.out.println("x"+x);
            if (treeHeight <= FindPoint(new Point(p.x, y+1))){
                return false;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return visible;
    }
    
    
    
    public int FindVisibleTrees(){
        int totalVisible = 0;
        totalVisible+=ScanEdges();
        
        return totalVisible;
    }
    
    public int CalculateScoreLeft(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        int score = 0;
        for (int x = p.x; x > 0; x--) {
//            stringBuilder.append(new Point(x-1,p.y));
            score++;
            if (treeHeight <= FindPoint(new Point(x-1,p.y))){
                return score;
            }
            
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return score;
    }

    public int CalculateScoreRight(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        int score = 0;
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int x = p.x; x < totalCols-1; x++) {
            stringBuilder.append(new Point(x,p.y));
//            System.out.println("x"+x);
            score++;
            if (treeHeight <= FindPoint(new Point(x+1,p.y))){
                return score;
            }
            
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return score;
    }

    public int CalculateScoreUp(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        int score = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int y = p.y; y > 0; y--) {
//            stringBuilder.append(new Point(p.x,y-1));
//            System.out.println("x"+x);
            score++;
            if (treeHeight <= FindPoint(new Point(p.x,y-1))){
                return score;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return score;
    }

    public int CalculateScoreDown(Point p){
        int treeHeight = FindPoint(p);
        boolean visible = true;
        int score = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(p.x+"a"+(totalCols+1));
        for (int y = p.y; y < totalCols-1; y++) {
//            stringBuilder.append(new Point(p.x,y));
//            System.out.println("x"+x);
            score++;
            if (treeHeight <= FindPoint(new Point(p.x, y+1))){
                return score;
            }
        }
//        System.out.println("Items checked:"+stringBuilder.toString());
        return score;
    }
    
    
    
    public int FindHighestScenicScore(){
        int highestScore = 0;
        for (int y = 0; y < totalCols; y++) {
            for (int x = 0; x < totalRows; x++) {
                // If the item is on an edge
                if ((y ==0 || x ==0) || (x ==trees.size()-1 || y ==trees.size()-1)){
                    foundItems.add(new Point(x, y));

                } else {
                    Point p = new Point(x, y);
                    int score = 1;
                    score*=CalculateScoreLeft(p);
                    score*=CalculateScoreRight(p);
                    score*=CalculateScoreUp(p);
                    score*=CalculateScoreDown(p);
                    
                    
                    if (score>highestScore){
                        highestScore = score;
                    }
                }
            }

        }
        
        
        return highestScore;
    }

    public static void main(String[] args) throws FileNotFoundException {
        day8 d = new day8();
        d.LoadData();

//        for (ArrayList<Integer> arr: d.trees) {
//            System.out.println(arr.toString());
//        }
        
        
        
        System.out.println("Total Visible Trees: "+d.FindVisibleTrees());        
//        System.out.println(d.foundItems.toString());
        
        System.out.println("Highest Scenic Score: "+d.FindHighestScenicScore());
        
        

    }
    
}
