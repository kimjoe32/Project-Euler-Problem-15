package p35;

public class p35
{
    private static final int limit = 1_000_000;
    private static final boolean [] primes = new boolean[limit + 1];
    private static int answer = 1; 
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        primeSieve();
        
        for (int i = 3; i < limit; i +=2)
        {
            if (primes[i])
            {
                int[] rotations = rotate(String.valueOf(i));
                boolean isCyclicPrime = true;
                
                for (int permutation = 0; permutation < rotations.length; permutation++)
                {
                    if ( !primes[ rotations[permutation] ] )
                    {
                        isCyclicPrime = false;
                        break;
                    }
                }
                
                if (isCyclicPrime) 
                {
                    answer++;
                    System.out.println("" + i);
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
        for(int i = 0; i < limit; i++)
        {
            primes[i] = true;
        }
        
        for(int base = 2; base*base <= limit; base++)
        {
            if(primes[base] == true)
            {
                for(int factor = base * base; factor <= limit; factor += base)
                {
                    primes[factor] = false;
                }
            }
        }
    }
    
    private static int[] rotate(String primeStr)
    {
        int[] returnArray = new int[primeStr.length()];
        
        for (int i = 1; i < returnArray.length; i++)
        {
            int offset = i % primeStr.length();
            String s = primeStr.substring(offset) + primeStr.substring(0, offset);
            returnArray[i] = Integer.valueOf(s);
        }
        
        return returnArray;
    }
}