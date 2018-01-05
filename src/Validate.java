import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate
{
    public static Scanner sc=new Scanner(System.in);

    public static int validateInteger() //validates integer entries
    {
        while (true)
        {
            try
            {
                //You can either use sc.nextInt, but you must make sure the
                //empty line is consumed by using sc.nextLine() with sc.nextInt().
                //or you can just use paresInt which consumes the empty line

                //int choice=sc.nextInt();
                int choice=Integer.parseInt(sc.next()); // Quick edit from nextLine() to next()

                //int choice=Integer.parseInt(sc.nextLine());
                //sc.nextLine(); //You need this to consume the empty line proceeding the reading of a String
                return choice;
            }

            catch (InputMismatchException e)
            {
                sc.nextLine();
				/*
			   * The next() method must be called in the catch block to dispose of the
			   * users invalid input because the nextDouble() method leaves the input
			   * value in the Scanners input stream if an InputMismatchException is
			   * thrown. If you omit the statement that calls next, the while loop
			   * keeps reading it, throws an exception, and displays an error
			   * message in an infinite loop
			   */
                System.out.println(e.getMessage());
                System.out.print("That is not a valid integer, please try again:");
            }

            catch (NumberFormatException e)
            {
                sc.nextLine();
                //e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.print("That is not a valid integer, please try again \n");
            }

        }
    }

    public static double validateDouble() //validates double entries
    {
        while (true)
        {
            try
            {
                double myDouble ;
                //myDouble = sc.nextDouble();
                myDouble= Double.parseDouble(sc.nextLine());
                return myDouble;
            }

            catch (InputMismatchException e)
            {
                sc.nextLine();
				/*
			   * Same comment as above applies
			   */
                System.out.println(e.getMessage());
                System.out.print("That is not a valid double, please try again Mr:");
            }
        }
    }

    public static String validateString()
    {
        while (true)
        {
            try
            {

                String myString=sc.nextLine();

                return myString;
            }

            catch (InputMismatchException e)
            {
                sc.nextLine();
				/*
			   * Same comment as above applies
			   */
                System.out.println(e.getMessage());
                System.out.print("That is not a valid String, please try again:");
            }
        }
    }
}
