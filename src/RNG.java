import java.math.BigInteger;
import java.util.Random;

public class RNG
{
    //takes in an integer and generate a random number with that many digits in base ten
    //because the numbers can be larger than the integer size limit, returns a BigInteger
    //naive approach by adding at every step
    //a better method would probably be generating multiple numbers at once and adding them in blocks
    static public BigInteger nDigitOddNumber(int n)
    {
        BigInteger Num = new BigInteger("0");
        BigInteger RandNum;
        BigInteger Ten = new BigInteger("10");
        Random Rand = new Random();

        //verify n is positive
        if (n <= 0)
            return Num;

        //todo: check if n is too big?

        //Do ones position separately to ensure Num is odd
        RandNum = new BigInteger(Integer.toString(Rand.nextInt(5)));
        RandNum = RandNum.multiply(BigInteger.valueOf(2));
        RandNum = RandNum.add(BigInteger.valueOf(1));
        Num = Num.add(RandNum);

        //Do the rest in a loop until the n-1 position
        for(int i = 1; i < n - 1; i++)
        {
            Ten = Ten.pow(i);                                        //multiply by the ith power of ten to set that position in base ten
            RandNum = BigInteger.valueOf(Rand.nextInt(10));   //generate random number between 0-9
            RandNum = RandNum.multiply(Ten);                         //multiply that random number by 10^i
            Num = Num.add(RandNum);                                  //add to Num
            Ten = BigInteger.valueOf(10);                            //reset Ten
        }

        //todo: see if I want to modify code to get around this
        if (n == 1)
            return Num;

        //do the final (largest) position separate to ensure it is non-zero
        Ten = Ten.pow(n - 1);
        RandNum = BigInteger.valueOf(Rand.nextInt(9) + 1);    //generate random number between 1-9
        RandNum = RandNum.multiply(Ten);
        Num = Num.add(RandNum);

        return Num;
    }
    //todo: final digit should be nonzero

    //returns a BigInteger holding a random integer in the range 1 - Upper
    static public BigInteger RandomPosInt(int Upper)
    {
        Random Rand = new Random();
        return (BigInteger.valueOf(Rand.nextInt(Upper))).add(BigInteger.ONE);
    }
    //todo: verify Upper is positive
}
