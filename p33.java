package p33;

public class p33
{
    public static void main(String[] args)
    {
        Double productOfSolutions = 1d;
        for (Integer numerator = 11; numerator <100; numerator++)
        {
            for (Integer denominator = numerator; denominator < 100; denominator++)
            {
                if (numerator.equals(denominator) ||
                    (numerator % 10 == 0 && denominator % 10 == 0))
                {
                    continue;
                }
                
                String numeratorStr = numerator.toString();
                String denominatorStr = denominator.toString();
                int specialNumerator = -1, specialDenominator = -1;
                if (numeratorStr.charAt(0) == denominatorStr.charAt(0)) 
                {
                    specialNumerator = Character.getNumericValue(numeratorStr.charAt(1));
                    specialDenominator = Character.getNumericValue(denominatorStr.charAt(1));
                }
                else if (numeratorStr.charAt(0) == denominatorStr.charAt(1)) 
                {
                    specialNumerator = Character.getNumericValue(numeratorStr.charAt(1));
                    specialDenominator = Character.getNumericValue(denominatorStr.charAt(0));
                }
                else if (numeratorStr.charAt(1) == denominatorStr.charAt(0)) 
                {
                    specialNumerator = Character.getNumericValue(numeratorStr.charAt(0));
                    specialDenominator = Character.getNumericValue(denominatorStr.charAt(1));
                }
                else if (numeratorStr.charAt(1) == denominatorStr.charAt(1)) 
                {
                    specialNumerator = Character.getNumericValue(numeratorStr.charAt(0));
                    specialDenominator = Character.getNumericValue(denominatorStr.charAt(0));
                }
                
                if (specialDenominator == -1 || specialNumerator == -1) continue;
                
                Double resultSpecial = (double) specialNumerator / (double)specialDenominator;
                Double resultFraction = (double) numerator/(double)denominator;
                if (resultSpecial.equals(resultFraction))
                {
                    System.out.println(numerator + "/" + denominator + "  =  " + specialNumerator + "/" + specialDenominator);
                    productOfSolutions *= resultSpecial;
                }
            }
            
        }
        System.out.println(productOfSolutions.toString());
    }
}