package p30;

public class p30
{
    public static int power = 5;
    
    public static void main(String[] args)
    {
        long[] arr = new long[] {0, 1, (long)Math.pow(2, power),
            (long)Math.pow(3, power), (long)Math.pow(4, power), (long)Math.pow(5, power),
            (long)Math.pow(6, power), (long)Math.pow(7, power), (long)Math.pow(8, power),
            (long)Math.pow(9, power)};
        Long totalSum = 0l;
        for (Integer i = 2; i < 355000; i++)
        {
            String stringNumber = i.toString();
            
            Long sum = 0l;
            for (int is = 0; is < stringNumber.length(); is++)
            {
                char c = stringNumber.charAt(is);        
                sum += arr[Character.getNumericValue(c)];
            }
            
            if (sum.toString().length() > power+1) 
            {
                break;
            }
            
            if (sum.equals(i.longValue())) 
            {
                System.out.println(sum.toString());
                totalSum += sum;
            }
        }
        System.out.println("sum = " + totalSum.toString());
    }
}