package java_backend4_8;//잘 모르겠음..
import java.util.Arrays;

class Exercise6_20 {
    public static void main(String[] args) {
        int[] data = {3, 2, 9, 4, 7};

        // data 출력
        System.out.println("data 배열: " + Arrays.toString(data));

        // 1) 정상적인 배열에 대한 최대값
        System.out.println("최대값: " + max(data));

        // 2) null 배열에 대한 최대값
        System.out.println("최대값: " + max(null));

        // 3) 길이가 0인 배열에 대한 최대값
        System.out.println("최대값: " + max(new int[] {}));
    }

    // max 메서드 정의
    public static int max(int[] arr) {
        // 1) 예외 처리: 배열이 null 또는 길이가 0인 경우 -999999 반환
        if (arr == null || arr.length == 0) {
            return -999999;
        }

        // 2) 최대값 계산
        int maxValue = arr[0];       // 초기값을 배열의 첫 번째 요소로 설정
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 3) 최대값 반환
        return maxValue;
    }
}
