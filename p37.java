package p37;


public class p37
{
    private static final int limit = 1_000_000;
    private static final boolean [] primes = new boolean[limit + 1];
    private static int sumOfAnswers = 0;
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        primeSieve();
        
        for (int i = 11; i < limit; i +=2)
        {
            if (primes[i])
            {
                String s = String.valueOf(i);
                boolean isTruncatablePrime = true;
                for (int size = 1; size < s.length(); size++)
                {
                    Integer forwardTrunc = Integer.valueOf(s.substring(size, s.length() ));
                    Integer backTrunc = Integer.valueOf(s.substring(0, s.length() - (size)));
                    if (! primes[forwardTrunc] || ! primes[backTrunc])
                    {
                        isTruncatablePrime = false;
                        break;
                    }
                }
                if (isTruncatablePrime)
                {
                    System.out.println("" + i);
                    sumOfAnswers += i;
                }
            }
        }
        System.out.println("answer = " + sumOfAnswers);
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
        primes[1] = false;
    }
}