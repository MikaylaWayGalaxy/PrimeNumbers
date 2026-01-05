import java.util.Scanner;

//***************************************************************************************************//
// Created by Mikayla Fulmer                                                                         //
// Last edited 12-24-25                                                                              //
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

        System.out.print("Enter how many digits you want the prime to be: ");
        NumDigits = Keyboard.nextInt();
        if (NumDigits > 1)
        {
            System.out.println(Primes.GeneratePrime(NumDigits));
            System.out.printf("is prime with %.12f%% certainty.", Primes.GetCertainty() * 100.0);
        }
        else
            System.out.println("Input out of range.");
    }
}
