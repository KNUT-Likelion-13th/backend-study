import java.util.Scanner;

public class q03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();

        System.out.println(((a % 4 == 0 && a % 100 != 0) || a % 400 == 0) ? "1" : "0");

        sc.close();
    }
    
}
