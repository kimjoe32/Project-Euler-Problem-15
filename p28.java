package p28;


public class p28
{
    private static final int matrixDimension = 1001;
    private static int length;
    private static long result = 1;
    public static void main(String[] args)
    {
        length = matrixDimension/2;
        for (int i = 1; i <= length; i++)
        {
            result += 4 * Math.pow((2*i +1), 2) - 12 * i;
            System.out.println("" + result);
        }
        System.out.println("" + result);
    }
}