package p63;

public class p63
{
    private static Long answer = 0l;
    
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        
        for (long base = 1l; base < 10; base++)
        {
            answer += (long) (1 / ( 1- Math.log10(base)));
        }
        
        System.out.println("" + answer);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
    }
}