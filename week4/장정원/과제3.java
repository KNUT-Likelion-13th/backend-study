package java_backend4_8;

class Exercise6_21 {
    // 1) abs 메서드 작성
    public static int abs(int value) {
        // 주어진 정수 value의 절대값을 반환한다.
        if (value < 0) {
            return -value;    // value가 음수라면 부호를 바꿔서 반환
        } else {
            return value;     // value가 0이상(양수)이면 그대로 반환
        }
    }

    public static void main(String[] args) {
        int value = 5;
        System.out.println(value + "의 절대값: " + abs(value));

        value = -10;
        System.out.println(value + "의 절대값: " + abs(value));
    }
}
