import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day6 {

    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day6";
//    String filePath = "C:\\Users\\James Work\\IdeaProjects\\AdventOfCode\\inputFolder\\day6-test";
    File file = new File(filePath);

    char[] inputData;

    public void loadData() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            inputData = sc.nextLine().toCharArray();

        }
        sc.close();
    }

    public int findStartOfPacket(){
        int bufferLength = 14;
        for (int i = bufferLength; i < inputData.length; i++) {
            char[] buffer = Arrays.copyOfRange(inputData, i-bufferLength, i);
            if (!findDuplicate(buffer)){
                // if there is no duplicate, return the index
                return i;
            }
        }

        return 0;
    }

    /**
     * Checks for a duplicate
     * @param buffer bufferStream
     * @return True if there is a duplicate
     */
    private boolean findDuplicate(char[] buffer){
        boolean duplicate = false;
        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < buffer.length; j++) {
                if (i != j && buffer[i]==buffer[j]) {
//                    FileSystem.out.println(buffer);
                    duplicate = true;
                    break;
                }
            }
        }

        return duplicate;
    }

    public static void main(String[] args) throws FileNotFoundException {
        day6 d = new day6();
        d.loadData();

        System.out.println(d.findStartOfPacket());

    }
}
