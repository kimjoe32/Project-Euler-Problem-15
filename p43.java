package p43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p43
{
    private static final ArrayList<Integer> digits = new ArrayList<>();
    private static Long answer = 0l;
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10; i++)
        {
            digits.add(i);
        }
                 
        while (getPermuations())
        {
            
            if (    digits.get(5) % 5 != 0 ||
                    digits.get(3) % 2 != 0)
            {
                continue;
            }
            
            Long number = getNumber(digits);
            
            if (number < 1_000_000_000)
            {
                continue;
            }
            
            Long _345 = getNumber(digits.subList(2, 5));
            Long _567 = getNumber(digits.subList(4, 7));
            Long _678 = getNumber(digits.subList(5, 8));
            Long _789 = getNumber(digits.subList(6, 9));
            Long _8910 = getNumber(digits.subList(7, 10));
                
            if (    (_345 % 3 == 0)  && (_567 % 7 == 0)  && 
                    (_678 % 11 == 0) && (_789 % 13 == 0) && 
                    (_8910 % 17 == 0) )
            {
                answer += number;
            }
        }
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
    
    public static boolean getPermuations()
    {
        int size = digits.size();
        int k = size - 1;
        
        while (k > 0 && digits.get(k - 1) >= digits.get(k))
        {
            k--;
        }

        if (k <= 0) 
        {
            return false;
        }

        int l = size - 1;
        while (digits.get(l) <= digits.get(k-1))
        {
            l--;
        }

        Collections.swap(digits, l, k-1);

        int n = size - 1;
        while (k < n)
        {
            Collections.swap(digits, k, n);
            k++;
            n--;
        }
        return true;
    }
    
    public static Long getNumber(List<Integer> al)
    {
        StringBuilder strNum = new StringBuilder();

        for (int num : al) 
        {
             strNum.append(num);
        }
        
        return Long.parseLong(strNum.toString());
    }
}