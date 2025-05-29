public class DottedStar {
    public static void main(String[] args) {
        int j = 0;
        char comb = '*';

        for(int i = 1; i <= 100; ++i) {
            for(int var4 = 0; var4 < i; ++var4) {
                System.out.print('*');
            }

            System.out.println();
        }

    }
}
