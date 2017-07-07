package p32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class p32
{
    public static ArrayList<Integer> digits;
    public static ArrayList<Long> permutations;
    public static HashSet<Long> products;
    public static void main(String[] args)
    {
        digits = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); 
        permutations = new ArrayList<>();
        products = new HashSet<>();
        getPermuations();
        
        Long multiplicand;
        Long multiplier;
        Long product;
        
        do 
        {
            for (int j = 1; j < digits.size()/2; j++)
            {
                for (int k = j + 1; k < digits.size()/2+2; k++)
                {
                    multiplicand = getNumber(digits.subList(0, j));
                    multiplier = getNumber(digits.subList(j, k));
                    product = getNumber(digits.subList(k, digits.size()));
                    if (multiplicand * multiplier == product)
                    {
                        System.out.println(multiplicand + " * " + multiplier + " = " + product);
                        products.add(product);
                    }
                }
            }
        } while(getPermuations());
        Long sum = 0l;
        for (Long l : products)
        {
            sum += l;
        }
        System.out.println("total products = " + products.size() + "\tsum of products = " + sum);
    }
    
    public static boolean getPermuations()
    {
        int k = digits.size() - 1;
        while (k > 0 && digits.get(k - 1) >= digits.get(k))
        {
            k--;
        }

        if (k <= 0) 
        {
            return false;
        }

        int l = digits.size() - 1;
        while (digits.get(l) <= digits.get(k-1))
        {
            l--;
        }

        Collections.swap(digits, l, k-1);

        int n = digits.size() - 1;
        while (k < n)
        {
            Collections.swap(digits, k, n);
            k++;
            n--;
        }
        return true;
    }
    
    public static Long getNumber(List<Integer> al)
    {
        StringBuilder strNum = new StringBuilder();

        for (int num : al) 
        {
             strNum.append(num);
        }
        
        return Long.parseLong(strNum.toString());
    }
}