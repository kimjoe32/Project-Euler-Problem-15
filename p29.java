package p29;

import java.math.BigInteger;
import java.util.HashSet;


//for some reason this brute force works fast enough
public class p29
{
    public static void main(String[] args)
    {
        HashSet<BigInteger> al = new HashSet<>();
        for (long i = 1; i <= 100; i++)
        {
            BigInteger result = BigInteger.valueOf(1);
            al.add(result);
            for (long j = 0; j < 99; j++)
            {
                result = result.multiply(BigInteger.valueOf(i));
                al.add(result);
            }
        }
        
        System.out.println("" + al.size());
    }
}