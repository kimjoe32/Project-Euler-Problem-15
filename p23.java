package p23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class p23
{
    public static void main(String[] args)
    {   
        Map<Integer, HashSet<Integer>> AbNumAndDivs = new HashMap<>();
        for (Integer i = 2; i <= 28123; i++)
        {
            HashSet<Integer> divsOfi = new HashSet<>();
            divsOfi.add(1);
            for (Integer j = i/2; j> 0 ; j--)
            {
                if (i % j == 0)
                {
                    divsOfi.add(j);
                }
            }
            AbNumAndDivs.put(i, divsOfi);
        }
        
        Iterator it = AbNumAndDivs.entrySet().iterator();
        ArrayList<Integer> abundantNums= new ArrayList<>();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            Iterator iths = ((HashSet<Integer>) pair.getValue()).iterator();
            Integer sum = 0;
            while (iths.hasNext())
            {
                sum += (Integer)iths.next();
            }
            
            if (sum > (Integer)pair.getKey())
            {
                abundantNums.add((Integer)pair.getKey());
            }
        }
        
        BitSet allNums = new BitSet(28123);
        for (int i = 0; i<abundantNums.size(); i++)
        {
            for (int j = i; j < abundantNums.size(); j++)
            {
                int sum = abundantNums.get(i) + abundantNums.get(j);
                if (sum <= 28123)
                {
                    allNums.set(sum);
                }
            }
        }
        
        Integer totalSum = 0;
        allNums.flip(0,allNums.size());
        for (int i =0; i <= 28123; i++)
        {
            if (allNums.get(i))
            {
                totalSum += i;
            }
        }
        
        System.out.println("" + totalSum);
    }
}