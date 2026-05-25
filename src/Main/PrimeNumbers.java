package Main;

import java.util.Scanner;
import java.util.Random;
import BigPrimes.*;

//***************************************************************************************************//
// Created by Mikayla Fulmer                                                                         //
// Last edited 5-24-26                                                                               //
//                                                                                                   //
// Large prime number generator based on PurpleMind's Youtube video.                                 //
// https://www.youtube.com/watch?v=tBzaMfV94uA                                                       //
//***************************************************************************************************//
public class PrimeNumbers
{
    static public void main(String[] args)
    {
        PrimeNumberGenerator Primes = new PrimeNumberGenerator();   //generates large prime numbers
        Scanner Keyboard = new Scanner(System.in);                  //gets keyboard input
        int NumDigits;                                              //number of digits of prime number
        int Iteration = 0;                                          //tracks how many numbers it took before finding a prime

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
        else if (NumDigits == 1)
        {
            switch(new Random().nextInt(4))
            {
            case (0):
                System.out.println("2 is prime with 100% certainty.");
                break;
            case (1):
                System.out.println("3 is prime with 100% certainty.");
                break;
            case (2):
                System.out.println("5 is prime with 100% certainty.");
                break;
            case (3):
                System.out.println("7 is prime with 100% certainty.");
                break;
            }
        }
        else
            System.out.println("Input out of range.");
    }
}
