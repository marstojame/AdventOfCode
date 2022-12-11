package day7;

import java.util.ArrayList;

public class FileSystem {
    String name;
    ArrayList<FileSystem> children;
    
    FileSystem parent;

    public FileSystem(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    
    
    //Change directory (CD)
    public FileSystem ChangeDirectory(ArrayList<String> input){
        String dirName = input.get(2);
//        System.out.println("CD:"+dirName);
        if (dirName.equals("..")){
            return parent;
        }
        
        int index = findChild(dirName);
//        System.out.println(children);
        return children.get(index);
    }

    //Create directory (dir)
    public FileSystem CreateDirectory(ArrayList<String> input, FileSystem parent){
        children.add(new Directory(input.get(1)));
        children.get(children.size()-1).parent = this;
        
        return this;
    }
    
    //Create file (int)
    public FileSystem CreateFile(ArrayList<String> input, FileSystem parent){
        int size = Integer.parseInt(input.get(0));
        children.add(new File(input.get(1), size));
        children.get(children.size()-1).parent = this;

        return this;
    }

    //Display children (LS)
    public void DisplayChildren(){
        System.out.println(this);
    }
    
    
    private int findChild(String name){
        int i = 0;
        for (i = 0; i < children.size(); i++) {
            FileSystem child = children.get(i);
            if (child.name.equals(name)){
                return i;
            }
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder stringOutput = new StringBuilder();
        stringOutput.append("Name: ");
        stringOutput.append(name);
        stringOutput.append(", children{\n\t");
        for (FileSystem f:children) {
            stringOutput.append("\t").append(f).append(",");
        }
        stringOutput.append("}");

        return stringOutput.toString();
    }
}

/**
 * Directory containing a combination of nested files/folders
 */
class Directory extends FileSystem {
    
    int spaceConsumed = 0;

    public Directory(String name) {
        super(name);
    }
    
    public int FindChildrenSize(){
        int childrenSize = 0;
        for (FileSystem f: children) {
            if (f instanceof File){
                childrenSize+=((File) f).size;
            }
            if (f instanceof Directory){
                childrenSize += ((Directory) f).FindChildrenSize();
            }
        }
        if (childrenSize<100000){
//            System.out.println(childrenSize);
        }
        
        spaceConsumed = childrenSize;
        return childrenSize;
    }
    
    public ArrayList<Directory> GetDirectories(ArrayList<Directory> directories){
        directories.add(this);
        for (FileSystem f: children){
            if (f instanceof Directory){
                Directory d = (Directory) f;
                d.GetDirectories(directories);
            }            
        }
        return directories;
    }
    
//    public void addChild(FileSystem f){
//        items.add(f);
//    }


//    @Override
//    public String toString() {
//        return "Directory{" +
//                "name='" + name + '\'' +
//                ", children=" + children +
//                '}';
//    }
    
}

/**
 * A file with a name and size
 */
class File extends FileSystem {
    int size;


    public File(String name, int size) {
        super(name);
        this.size = size;
    }
    
    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "File{" +
                "size=" + size +
                ", name='" + name + "'\n" +
                '}';
    }
}