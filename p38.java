package p38;

import java.util.ArrayList;
import java.util.Arrays;

public class p38
{
    private static int answer = 0;
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        for (int i = 192; i < 100000; i++)
        {
            String result = "";
            int j = 1;
            
            while(result.length() < 9)
            {
                int k = i * j;
                result = result + String.valueOf(k);
                j++;
            }
            
            //System.out.println(result);
            if (result.length() == 9)
            {
                j = Integer.valueOf(result);
                if (isPandigital(j) && j > answer)
                {
                    System.out.println("" + j);
                    answer = j;
                }
            }
        }
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
    
    private static boolean isPandigital(int n)
    {
        String s = String.valueOf(n);
        
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        s = new String(chars);
        
        return s.equals("123456789");
    }
}