package p31;

public class p31
{
    private static final int[]  coins = {1, 2, 5, 10, 20, 50, 100, 200};
    private static final int    TARGET = 200;
    private static int [][]     data;
    
    public static void main(String[] args)
    {
        data = new int[TARGET+1][8];
        for (int i =0; i < TARGET+1; i++)
        {
            data[i][0] = 1;
        }
        
        for (int r = 0; r <= TARGET; r++)
        {
            for (int c = 1; c < coins.length; c++)
            {
                if (coins[c] <= r)
                {
                    data[r][c] = data[r][c-1] + data[r-coins[c]][c];
                }
                else
                {
                    data[r][c] = data[r][c-1];
                }
            }
        }
        System.out.println("total combinations = " + data[TARGET][coins.length - 1] + " ");
    }    
}