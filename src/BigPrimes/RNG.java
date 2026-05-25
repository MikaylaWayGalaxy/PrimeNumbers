package BigPrimes;

import java.math.BigInteger;
import java.util.Random;

//***************************************************************************************************//
// Random number generator that uses Java's BigInteger to generate large numbers.                    //
// Pure static class.                                                                                //
// Last edited 1-4-26                                                                                //
//***************************************************************************************************//
public class RNG
{
    //***********************************************************************************************//
    //                   private constructor, prevents class from being initialized                  //
    //***********************************************************************************************//
    private RNG() { /* do nothing */ }



    //***********************************************************************************************//
    //                                        static functions                                       //
    //***********************************************************************************************//
    //takes in an integer and generate a random number with that many digits in base ten
    //naive approach by adding at every step, a better method would probably be generating multiple numbers at once and adding them in blocks
    //to increase probability of being prime, last digit is 1, 3, 7, or 9
    //throws IllegalArgumentException
    static public BigInteger nDigitNumber1379(int n)
    {
        BigInteger Num = new BigInteger("0");
        BigInteger RandNum;
        BigInteger Ten = new BigInteger("10");
        Random Rand = new Random();

        //verify n is positive
        if (n <= 0)
            throw new IllegalArgumentException();

        //do ones position separately to ensure Num is not divisible by 2 or 5
        switch(Rand.nextInt(4))             //generate random number between 0-3
        {
        case (0):
            Num = Num.add(BigInteger.ONE);
            break;
        case (1):
            Num = Num.add(BigInteger.valueOf(3));
            break;
        case (2):
            Num = Num.add(BigInteger.valueOf(7));
            break;
        case (3):
            Num = Num.add(BigInteger.valueOf(9));
            break;
        }

        //ignore rest of function if n is 1
        if (n == 1)
            return Num;

        //do the rest in a loop until the n-1 position
        for(int i = 1; i < n - 1; i++)
        {
            Ten = Ten.pow(i);                                        //multiply by the ith power of ten to set that position in base ten
            RandNum = BigInteger.valueOf(Rand.nextInt(10));   //generate random number between 0-9
            RandNum = RandNum.multiply(Ten);                         //multiply that random number by 10^i
            Num = Num.add(RandNum);                                  //add to Num
            Ten = BigInteger.valueOf(10);                            //reset Ten
        }

        //do the final (largest) position separate to ensure it is non-zero
        Ten = Ten.pow(n - 1);
        RandNum = BigInteger.valueOf(Rand.nextInt(9) + 1);    //generate random number between 1-9
        RandNum = RandNum.multiply(Ten);
        Num = Num.add(RandNum);

        return Num;
    }

    //returns a BigInteger holding a random integer in the range 1 - Upper
    //throws IllegalArgumentException
    static public BigInteger RandomPosInt(int Upper)
    {
        Random Rand = new Random();
        if (Upper <= 0)
            throw new IllegalArgumentException();
        return (BigInteger.valueOf(Rand.nextInt(Upper))).add(BigInteger.ONE);
    }
}
