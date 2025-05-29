package week_4;

public class Example2 {

    public static void main(String[] args) {
        int[] data = {3, 2, 9, 4, 7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최대값:" + max(data));
        System.out.println("최대값:" + max(null));
        System.out.println("최대값:" + max(new int[] {}));
    }

    public static int max(int[] data) {
        if (data == null || data.length == 0) {
            return -999999;
        } else {
            int answer = data[0];

            for (int i = 1; i < data.length; i++) {
                answer = Math.max(answer, data[i]);
            }

            return answer;
        }
    }
}
