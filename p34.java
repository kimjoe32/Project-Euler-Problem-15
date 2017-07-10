package p34;

public class p34
{
    static byte[] digits = {0, 0, 0, 0, 0, 0, 0, 1, 0};
    static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    static long answer = 0l;
    
    public static void main(String[] args)
    {
        for (long i = 10l; i < 100_000_000; i++)
        {
            long sumOfFacts = 0l;
            int digitsIndex = 0;
           
            while (digits[digitsIndex] == 0) 
            {
                digitsIndex++;
            }
            for (; digitsIndex < digits.length; digitsIndex++)
            {
                sumOfFacts += factorials[ digits[digitsIndex] ];
            }
            
            int k = digits.length-1;
            digits[k]++;
            while (digits[k] > 9)
            {
                digits[k] = 0;
                digits[k-1]++;
                k--;
            }
            
            if(i == sumOfFacts)
            {
                System.out.println("" + i);
                answer += i;
            }
        }
        System.out.println("answer =  " + answer);
    }
}