package helps;

import java.util.ArrayList;

public class Utilz {
    public static int[][] ArrayListTo2DInt(ArrayList<Integer> list, int ySize, int xSize){
        int[][] newArr = new int[ySize][xSize];

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[i].length; j++) {
                int index = i*ySize + j;
                newArr[i][j] = list.get(index);
            }
        }

        return newArr;

    }

    public static int[] TwoDToOneDIntArr(int[][] arr){
        int[] oneAr = new int[arr.length * arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int index = i*arr.length + j;
                oneAr[index] = arr[i][j];
            }
        }
        return oneAr;
    }
}
