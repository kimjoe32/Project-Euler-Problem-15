package p24;

import static java.lang.Math.sqrt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class p12
{
    public static void main(String[] args)
    {   
        int n =0;
        int currentNum= 0;
        int divisors = 0;
        while(true)
        {
            n++;
            currentNum += n;
            
            divisors = primeFactors(currentNum);
            System.out.println("" + currentNum + ": " + divisors);
            if (divisors >= 500)
            {
                System.out.println("" + n);
                break;
            }
        }
    }
    public static Integer primeFactors(int n)
    {
        HashMap<Integer, Integer> map = new  HashMap<>();
        while (n%2 == 0)
        {
            if (!map.containsKey(2))
            {
                map.put(2, 1);
            }
            else 
            {
                Integer t = map.get(2)+1;
                map.put(2, t);
            }
            
            n = n/2;
        }

        for (int i = 3; i <= sqrt(n); i = i+2)
        {
            while (n%i == 0)
            {
                if (!map.containsKey(i))
                {
                    map.put(i, 1);
                }
                else 
                {
                    Integer t = map.get(i)+1;
                    map.put(i, t);
                }
                
                n = n/i;
            }
        }

        if (n > 2)
        {
            if (!map.containsKey(n))
            {
                map.put(n, 1);
            }
            else 
            {
                Integer t = map.get(n)+1;
                map.put(n, t);
            }
        }
        
        Integer divisors = 1;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            divisors = divisors * ((Integer)pair.getValue() + (Integer)1);
        }
        
        return divisors;
    }
}