package p21;

import static java.lang.Math.sqrt;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class p21
{
    public static void main(String[] args)
    {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 1; i <= 10000; i++)
        {
            int sum = 1;
            for (int j = 2; j <= sqrt(i); j++)
            {
                if (i%j == 0)
                {
                    sum += j;
                    if (i/j != j) sum += i/j;
                }
            }
            mp.put(i, sum);
        }
        
        Integer sumOfNums = 0;
        for (Map.Entry pair : mp.entrySet()) 
        {
            if (pair.getKey().equals(pair.getValue())) continue;
            
            if (Objects.equals((Integer)pair.getKey(), mp.get(pair.getValue())))
            {
                System.out.println("(" + pair.getKey() + ", " + pair.getValue() + ")\t=\t(" + 
                                    mp.get(pair.getValue()) + ", " + mp.get(pair.getKey()) + ")");
                sumOfNums = sumOfNums+ (Integer)pair.getKey();
            }
        }
       
        System.out.println(""+sumOfNums);
    }
}