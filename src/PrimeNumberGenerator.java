import java.math.BigInteger;
import static java.lang.Math.pow;

//***************************************************************************************************//
// Prime number generator that uses Java's BigInteger class to store the large numbers.              //
// Last edited 12-24-25                                                                              //
//***************************************************************************************************//
public class PrimeNumberGenerator
{
    //***********************************************************************************************//
    //                                      private member variables                                 //
    //***********************************************************************************************//
    private BigInteger Number;                  //Number to check if prime or composite
    private double Certainty = 0.0;             //Certainty that Number is prime
    private int Precision = 10;                 //Amount of bases to check during test, initialized to 10



    //***********************************************************************************************//
    //                                             constructors                                      //
    //***********************************************************************************************//
    PrimeNumberGenerator()
    {
        this.Number = BigInteger.ZERO;              //if no arguments, init to 0
    }
    PrimeNumberGenerator(BigInteger Number)
    {
        this.Number = Number;
    }



    //***********************************************************************************************//
    //                                      accessors and mutators                                   //
    //***********************************************************************************************//
    public void SetNumber(BigInteger Number)
    {
        this.Number = Number;
        Certainty = 0.0;                            //reset Certainty because we don't know if Number is prime if we reset it
    }
    public BigInteger GetNumber()
    {
        return Number;
    }
    public double GetCertainty()
    {
        return Certainty;
    }
    public void SetPrecision(int Precision)
    {
        this.Precision = Precision;
    }
    public int GetPrecision()
    {
        return Precision;
    }



    //***********************************************************************************************//
    //                                      public member functions                                  //
    //***********************************************************************************************//
    //generate and return a prime number using the Miller-Rabin test
    //takes in number of digits the prime should be in base ten
    //overwrites Number and Certainty member variables
    public BigInteger GeneratePrime(int NumDigits)
    {
        int Iteration = 0;                                                  //iteration counter to track how long it took to generate this prime
        int Expected = (int)Math.ceil(Math.log(10) * NumDigits / 2.0);      //expected number of iterations for a given number of digits, from prime number theorem

        //generate random number and loop until it's most likely prime
        do
        {
            Number = RNG.nDigitNumber1379(NumDigits);
            System.out.println("Current iteration: " + ++Iteration + " Expected: " + Expected);
        }while(!IsPrime());

        return Number;
    }

    //Checks if Number is prime
    public boolean IsPrime()
    {
        return MillerRabinTest(Precision);
    }


    //***********************************************************************************************//
    //                                      private member functions                                 //
    //***********************************************************************************************//
    //Miller-Rabin test
    //repeatedly apply Miller's test to have a high certainty a number is prime
    //takes in how many different bases to check
    private boolean MillerRabinTest(int Check)
    {
        BigInteger RandNum;
        int Bound = BigIntFunctions.ToInteger(Number.subtract(BigInteger.ONE));

        for (int i = 0; i < Check; i++)
        {
            //generate a random number between 1 and Number - 1 (or integer size limit if Number is too big)
            //this number is the base of modular exponentiation
            //could have duplicates, however the chances of a duplicate number being checked are very low
            RandNum = RNG.RandomPosInt(Bound);
            if (!MillerTest(RandNum, Number))
            {
                Certainty = 0.0;
                return false;
            }
        }

        Certainty = 1.0 - pow(0.25, Check);         //odds of identifying a composite number are at least 75% for each number
        return true;                                //if reached, Number passes the test
    }

    //Miller's test for primality
    //takes in a base for modular exponentiation and a number to check if prime
    static private boolean MillerTest(BigInteger Base, BigInteger PrimeCand)
    {
        BigInteger PMinus1 = PrimeCand.subtract(BigInteger.ONE);    //1 fewer than the prime candidate, compared to during loop
        BigInteger Exp = PMinus1;                                   //Starts the same as PMinus1 but allowed to change during the loop
        BigInteger Test;                                            //Base^(Exp) % PrimeCand
        boolean OneFlag = true;                                     //keeps track if the last number was a 1

        //Fermat's Test
        //Make sure Base^(Exp) % PrimeCand == 1
        if (!(BigIntFunctions.ModExp(Base, Exp, PrimeCand).equals(BigInteger.ONE)))   //if false, then PrimeCand is composite
            return false;

        //Miller's Test
        do
        {
            Test = BigIntFunctions.ModExp(Base, Exp, PrimeCand);                    //calculate Base^(Exp) % PrimeCand
            if(OneFlag && !Test.equals(BigInteger.ONE) && !Test.equals(PMinus1))    //if the previous iteration was 1 and the current doesn't equal 1 or PMinus1, then PrimeCand is composite
                return false;
            OneFlag = Test.equals(BigInteger.ONE);                                  //reset OneFlag
            Exp = Exp.divide(BigInteger.valueOf(2));                                //divide Exp by 2
        }while(BigIntFunctions.IsEven(Exp));                                        //loop until Exp becomes odd

        return true;            //if reached, Miller's test passes for this base
    }
}
