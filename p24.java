package p24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class p24
{
    public static List<Integer> digits;
    public static Boolean noMorePerms = false;
    
    public static void main(String[] args)
    {
        digits = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int totalPermutations = 0;
        for (int i = 0; i < 999999 && !noMorePerms; i++) 
        {
            nextPermuation();
        }
        printDigits();
    }
    
    public static void nextPermuation()
    {
        int k = digits.size() - 1;
        while (k > 0 && digits.get(k - 1) >= digits.get(k))
        {
            k--;
        }
        
        if (k <= 0) 
        {
            noMorePerms = true; 
            return;
        }
        
        int l = digits.size() - 1;
        while (digits.get(l) <= digits.get(k-1))
        {
            l--;
        }
        
        Collections.swap(digits, l, k-1);

        int n = digits.size() -1;
        while (k < n)
        {
            Collections.swap(digits, k, n);
            k++;
            n--;
        }
    }
    
    public static void printDigits()
    {
        System.out.println(digits.toString());
    }
}