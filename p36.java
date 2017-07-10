package p36;

import java.util.ArrayList;

public class p36
{
    private static Integer answer = 0;
    public static void main(String[] args)
    {
        ArrayList<Integer> palindromesDecimal = new ArrayList<>();
        for (Integer i = 0; i < 1000000; i++)
        {
            if (isPalindrome(i.toString()))
            {
                palindromesDecimal.add(i);
            }
        }
        
        for (Integer i: palindromesDecimal)
        {
            if (isPalindrome(Integer.toBinaryString(i)))
            {
                answer += i;
//                System.out.println(i.toString());
//                System.out.println(Integer.toBinaryString(i));
            }
        }
        
        System.out.println("answer = " + answer);
    }
    public static boolean isPalindrome(String s) 
    {
        int n = s.length();
        for (int i = 0; i < (n/2); ++i) 
        {
           if (s.charAt(i) != s.charAt(n - i - 1)) 
           {
               return false;
           }
        }
        return true;
    }
}