import java.math.BigInteger;

//***************************************************************************************************//
// Pure static class that includes functions for BigInteger. Notably modular exponentiation which    //
// is used to check if a large number is prime.                                                      //
// Last edited 1-4-26                                                                                //
//***************************************************************************************************//
public class BigIntFunctions
{
    //***********************************************************************************************//
    //                   private constructor, prevents class from being initialized                  //
    //***********************************************************************************************//
    private BigIntFunctions() { /* do nothing */ }



    //***********************************************************************************************//
    //                                        static functions                                       //
    //***********************************************************************************************//
    //Modular Exponentiation
    //Base^Exp % Mod
    //iterative version, works for large inputs
    static public BigInteger ModExp(BigInteger Base, BigInteger Exp, BigInteger Mod)
    {
        BigInteger Product = BigInteger.ONE;            //product to return, initialize to 1

        //loop until Exp becomes 0, Exp reduces during each iteration
        while (!Exp.equals(BigInteger.ZERO))
        {
            if (IsEven(Exp))        //if exponent is even
            {
                //squaring a number and raising it to a half doesn't change the number
                //Base^Exp = (Base^2)^(Exp/2)
                //Base^Exp % Mod = (Base^2 % Mod)^(Exp/2) % Mod
                Base = Base.pow(2);
                Base = Base.mod(Mod);
                Exp = Exp.divide(BigInteger.TWO);       //cut Exp by half
            }
            else                    //if exponent is odd
            {
                //multiply by Base and reduce exponent by one
                //Base^Exp = Base * Base^(Exp-1)
                //Base^Exp % Mod = (Base % Mod) * Base^(Exp-1) % Mod
                Product = Product.multiply(Base);       //multiply product by current base
                Product = Product.mod(Mod);             //calculate mod here to keep product small during loop
                Exp = Exp.subtract(BigInteger.ONE);     //subtract 1 from exponent, guarantees next iteration is even
            }
        }

        return Product.mod(Mod);                        //return product after calculating mod
    }

    //Modular Exponentiation
    //Base^Exp % Modulus
    //old version that uses recursion
    //WARNING: may result in a stack overflow error if input is too large, use iterative version instead
    static public BigInteger ModExpOld(BigInteger Base, BigInteger Exp, BigInteger Modulus)
    {
        //if Exp == 0, return 1
        //Base^0 = 1
        //escape condition for recursion
        if (Exp.equals(BigInteger.ZERO))
            return BigInteger.ONE;

        //check if Exp is even or odd
        //both reduce Exp and call recursively

        //if Exp is even
        //squaring a number and raising it to a half doesn't change the number
        //Base^Exp = (Base^2)^(Exp/2)
        //Base^Exp % Modulus = (Base^2 % Modulus)^(Exp/2) % Modulus
        if (IsEven(Exp))
            return ModExpOld(Base.pow(2).remainder(Modulus), Exp.divide(BigInteger.valueOf(2)), Modulus);

        //if Exp is odd
        //multiply by Base and reduce exponent by one
        //Base^Exp = Base * Base^(Exp-1)
        //Base^Exp % Modulus = Base * Base^(Exp-1) % Modulus
        return (Base.multiply(ModExpOld(Base, Exp.subtract(BigInteger.valueOf(1)), Modulus))).remainder(Modulus);
    }

    //returns an integer containing the value of input if input is small enough
    //returns MAX_INTEGER if input is too large
    static public int ToInteger(BigInteger Num)
    {
        if (OutsideSizeLimit(Num))       //if Num > MAX_INTEGER
            return Integer.MAX_VALUE;
        return Num.intValue();
    }

    //IsEven function for BigInteger
    static public boolean IsEven(BigInteger Num)
    {
        return (!Num.testBit(0));     //checks only last bit to prevent using modulus which would require calculating potentially large divisions
    }

    //checks if input is within the signed integer size limit
    static public boolean InsideSizeLimit(BigInteger Num)
    {
        return (Num.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0);
    }
    //todo: update for negative numbers

    //checks if input is outside the signed integer size limit
    static public boolean OutsideSizeLimit(BigInteger Num)
    {
        return (!InsideSizeLimit(Num));
    }
}
