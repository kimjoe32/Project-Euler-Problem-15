package p42;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p42
{
    private static Long answer = 0l;
    private static final HashMap<Character, Integer> pairs = new HashMap<>();
    private static final ArrayList<Long> triangleNums = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        long startTime = System.currentTimeMillis();
        
        for (int i = 1; i <= 26; i++)
        {
            char c = (char) (64 + i);
            pairs.put(c, i);
        }
        
        triangleNums.add(1l);
        for (int i = 2; i <= 500; i++)
        {
            triangleNums.add(i + triangleNums.get(i-2));
        }
        
        String line;
        
        try (Scanner sc = new Scanner(new File("input.txt")))
        {
            line = sc.next();
        }
        Matcher m = Pattern.compile("\"[A-Z]*\",").matcher(line);
        
        while (m.find())
        {
            String word = m.group().replaceAll("[,\"]","");
            Long sum = 0l;
            
            for (int i = 0; i < word.length(); i++)
            {
                sum += pairs.get(word.charAt(i));
            }
            
            if (Collections.binarySearch(triangleNums, sum) >= 0)
            {
                answer++;
            }
        }
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
}