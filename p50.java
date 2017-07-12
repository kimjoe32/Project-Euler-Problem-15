package p50;

import java.util.ArrayList;
import java.util.Collections;

public class p50
{
    private static final int limit = 1_000_000;
    private static final ArrayList<Integer> primes = new ArrayList<>();
    private static final ArrayList<Integer> consecutiveSums = new ArrayList<>();
    private static Integer answer = 0;
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        primeSieve();
        
        int sum = 0;
        for (Integer prime : primes)
        {
            sum += prime;
            consecutiveSums.add(sum);
        }
        
        int longestConsecutiveSum = 0;
        for (int i = 0; i < primes.size(); i++)
        {
            for (int j = i - (longestConsecutiveSum); j >= 0; j--)
            {
                sum = consecutiveSums.get(i) - consecutiveSums.get(j);
                if (sum >= 1_000_000) 
                {
                    break;
                }
                
                if (Collections.binarySearch(primes, sum) > 0)
                {
                    longestConsecutiveSum = i - j;
                    answer = sum;
                }
            }
        }
        
        System.out.println("answer = " + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
    
    private static void primeSieve()
    {
        boolean [] prime = new boolean[limit + 1];
        
        for(int i = 0; i < limit; i++)
        {
            prime[i] = true;
        }
        
        for(int base = 2; base*base <= limit; base++)
        {
            if(prime[base] == true)
            {
                for(int factor = base * base; factor <= limit; factor += base)
                {
                    prime[factor] = false;
                }
            }
        }
        
        prime[0] = false;
        prime[1] = false;
        
        for (int i = 0; i < prime.length; i++)
        {
            if (prime[i])
            {
                primes.add(i);
            }
        }
    }
}