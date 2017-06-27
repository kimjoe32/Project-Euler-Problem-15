package javatests;

import java.awt.BorderLayout;

public class p27
{
    public static boolean isPrime(int num)
    {
        if (num < 0) return false;
        if ( num > 2 && num % 2 == 0 ) 
        {
            return false;
        }
        int top = (int)Math.sqrt(num);
        for(int i = 3; i < top; i+=2)
        {
            if(num % i == 0)
            {
                return false;
            }
        }
        return true; 
    }
    
    public static void main(String[] args)
    {
        Integer maxPrimes = 0;
        Integer productAB = -1;
        
        
        for (int a = -999; a < 1000; a++)
        {
            for (int b = -1000; b < 1001; b++)
            {
                Integer currentPrimes = 0;
                Integer n = 0;
                while (true)
                {
                    
                    if (isPrime(n*n + (a*n) + b))
                    {
                        currentPrimes ++;
                        n ++;
                        continue;
                    }
                    else break;
                }
                if (currentPrimes > maxPrimes)
                {
                    maxPrimes = currentPrimes;
                    productAB = a * b;
                }
//                if (currentPrimes > 0)
//                {
//                    System.out.println("a = " + a + " b = " + b + " currentPrimes = " + currentPrimes);
//                }
            }
        }
        
        System.out.println(productAB + "");
    }
}
