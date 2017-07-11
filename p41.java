package p41;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p41
{
    private static Long answer = 0l;
    private static final ArrayList<Integer> digits = new ArrayList<>();
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        for (int i = 1; i <= 10; i++)
        {
            digits.add(i);
        }
        
        while ( !digits.isEmpty())
        {
            Collections.sort(digits);
            digits.remove(digits.size() - 1);
            
            while (getPermuations())
            {
                Long currentNumber = getNumber(digits);
                if (currentNumber > answer && isPrime(currentNumber))
                {
                    answer = currentNumber;
                }
            }
        }
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
    
    public static boolean getPermuations()
    {
        int k = digits.size() - 1;
        while (k > 0 && digits.get(k - 1) >= digits.get(k))
        {
            k--;
        }

        if (k <= 0) 
        {
            return false;
        }

        int l = digits.size() - 1;
        while (digits.get(l) <= digits.get(k-1))
        {
            l--;
        }

        Collections.swap(digits, l, k-1);

        int n = digits.size() - 1;
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
    
    public static boolean isPrime(Long num)
    {
        if (num < 0) 
        {
            return false;
        }
        if ( num > 2 && num % 2 == 0 ) 
        {
            return false;
        }
        int top = (int) Math.sqrt(num);
        for(int i = 3; i < top; i += 2)
        {
            if(num % i == 0)
            {
                return false;
            }
        }
        return true; 
    }
}