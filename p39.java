package p39;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class p39
{
    private static int answer = 0;
    private static final HashSet<ArrayList<Integer>> triples = new HashSet<>();
    private static final Map<Integer, Integer> sumOfTriples = new HashMap<>();
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        //a = m^2 - n^2m, b = 2mn, c = m^2 + n^2
        //m > n > 0
        //m and n are coprime
        for (int m = 1; m < 1000; m++)
        {
            for (int n = 1; n < m; n++)
            {
                if (GCD(m, n) != 1 ||
                        ((m % 2) == 1 && (n % 2) == 1))
                {
                    break;
                }
                int a = ((m * m) - (n * n));
                int b = (2 * m * n);
                int c = ((m * m) + (n * n));
                
                for (int k = 1; k < 1000; k++)
                {
                    int ak = a * k;
                    int bk = b * k;
                    int ck = c * k;
                    if ( ak + bk + ck > 1000 )
                    {
                        break;
                    }
                    
                    addToSet(ak, bk, ck);
                }
            }
        }
        
        sumTriples();
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
    
    private static void addToSet(int a, int b, int c)
    {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(a);
        al.add(b);
        al.add(c);
        Collections.sort(al);
        triples.add(al);
    }
    
    private static int GCD(int a, int b) 
    {
        if (b == 0) 
        {
            return a;
        }
        return GCD(b, a % b);
    }
    
    private static void sumTriples()
    {
        for (ArrayList<Integer> al: triples)
        {
            int sum = 0;
            for (Integer i: al)
            {
                sum += i;
            }
            
            int count = sumOfTriples.containsKey(sum) ? sumOfTriples.get(sum) : 0;
            sumOfTriples.put(sum, count + 1);
        }
        int max = -1;
        for (Map.Entry<Integer, Integer> e: sumOfTriples.entrySet())
        {
            if (e.getValue().compareTo(max) >0)
            {
                answer = e.getKey();
                max = e.getValue();
            }
        }
    }
}