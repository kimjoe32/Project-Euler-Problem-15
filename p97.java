package p97;

public class p97
{
    private static Long answer = 1l;
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 7830457; i++)
        {
            answer = (answer << 2) % 100000000000l;
        }
        
        answer *= 28433;
        answer %= 10000000000l;
        System.out.println("answer = " + (answer + 1));
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
}