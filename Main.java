//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the first Number: ");
        int a=sc.nextInt();
        System.out.print("Enter the second Number: ");
        int b=sc.nextInt();

        System.out.print("\n1.Add");
        System.out.print("\n2.Multiply");
        System.out.print("\n3.Divide ");
        System.out.print("\n4.Modulus ");
        System.out.print("\n5.Subtract ");
        System.out.print("\nChoose the action to be performed(1/2/3/4/5):");
        int choose=sc.nextInt();
        sc.close();
        switch(choose){
            case 1: System.out.println("a+b"+"="+(a+b));
            break;
            case 2: System.out.println("a*b"+"="+(a*b));
            break;
            case 3: System.out.println("a/b"+"="+(a/b));
            break;
            case 4: System.out.println("a%b"+"="+(a%b));
            break;
            case 5: System.out.println("a-b"+"="+(a-b));
            break;
            default: System.out.println("Invalid choice");
        }

    }
}