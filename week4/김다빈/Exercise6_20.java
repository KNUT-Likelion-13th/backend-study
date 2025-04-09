package likellion;

public class Exercise6_20 {
     public static void main(String[] args) {
        int[] data = {3, 2, 9, 4, 7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최대값:" + max(data));
        System.out.println("최대값:" + max(null));
        System.out.println("최대값:" + max(new int[]{}));
    }

    private static int max(int data[]) {
        int max = 0;

        if (data == null || data.equals("")) {
            return -999999;
        }
        if (data.length == 0) {
            return -999999;
        }
        for (int i : data) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
}
