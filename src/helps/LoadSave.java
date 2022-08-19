package helps;

import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas(){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png"); //reads image

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    private static ArrayList<Integer> readFromFile(File file){
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //save 3d int array to file

    //load int array from file

    //create a new lvl with default values
    public static void CreateLevel(String name, int[] idarr){
        File newLevel = new File("src/res/" + name + ".txt");

        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        }
        else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        writeToFile(newLevel, idarr);

    }

    private static void writeToFile(File f, int[] idarr){

        try {
            PrintWriter pw = new PrintWriter(f);

            for (Integer i : idarr) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SaveLevel(String name, int[][] idArr){
        File lvlFile = new File("src/res/" + name + ".txt");

        if (lvlFile.exists()){
            writeToFile(lvlFile, Utilz.TwoDToOneDIntArr(idArr));
        }
        else {
            System.out.println("File: " + name + " does not exist");
            return;
        }

    }

    public static int[][] GetLevelData(String name){
        File lvlFile = new File("src/res/" + name + ".txt");

        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFromFile(lvlFile);
            return Utilz.ArrayListTo2DInt(list, 20, 20);
        }
        else {
            System.out.println("File: " + name + " does not exist");
            return null;
        }


    }
}
