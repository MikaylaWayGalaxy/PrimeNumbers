import java.util.Scanner;

//***************************************************************************************************//
// Created by Mikayla Fulmer                                                                         //
// Last edited 1-4-26                                                                                //
//                                                                                                   //
// Large prime number generator based on PurpleMind's Youtube video.                                 //
// https://www.youtube.com/watch?v=tBzaMfV94uA                                                       //
//***************************************************************************************************//
public class PrimeNumbers
{
    static public void main(String[] args)
    {
        PrimeNumberGenerator Primes = new PrimeNumberGenerator();
        Scanner Keyboard = new Scanner(System.in);
        int NumDigits;
        int Iteration = 0;                                                  //iteration counter to track how long it took to generate this prime

        System.out.print("Enter how many digits you want the prime to be: ");
        NumDigits = Keyboard.nextInt();
        if (NumDigits > 1)
        {
            //generate random number and loop until it's almost certainly prime
            do
            {
                Primes.SetNumber(RNG.nDigitNumber1379(NumDigits));
                System.out.println("Current iteration: " + ++Iteration);
            }while(!Primes.IsPrime());

            System.out.println(Primes.GetNumber());
            System.out.printf("is prime with %.12f%% certainty.", Primes.GetCertainty() * 100.0);
        }
        else
            System.out.println("Input out of range.");
    }
}
