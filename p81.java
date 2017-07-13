package p81;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p81
{
    private static Long answer = 0l;
    private static final int size = 80;
    private static final int [][] matrix = new int[size][size];
    
    public static void main(String[] args) throws FileNotFoundException
    {
        long startTime = System.currentTimeMillis();
        
        String line;
        Scanner scanner = new Scanner(new File("input.txt"));
        for (int i = 0; i < size; i++)
        {
            line = scanner.nextLine();
            String[] nums = line.split(",");
            for (int j = 0; j < size; j++)
            {
                matrix[i][j] = Integer.valueOf(nums[j]);
            }
        }
        
        for (int i = size - 2; i >= 0; i--) 
        {   
            matrix[size - 1][i] += matrix[size - 1][i + 1];
            matrix[i][size - 1] += matrix[i + 1][size - 1];
        }
        
        for (int i = size - 2; i >= 0; i--)
        {
            for (int j = size - 2; j >= 0; j--)
            {
                matrix[i][j] += Math.min(matrix[i + 1][j],matrix[i][j + 1]);
            }
        }
        
        answer = (long) matrix[0][0];
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
}