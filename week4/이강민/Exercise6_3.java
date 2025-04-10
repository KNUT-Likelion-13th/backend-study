public class Exercise6_3 {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "홍길동";
        s.ban = 1;
        s.no = 1;
        s.kor = 100;
        s.eng = 60;
        s.math = 76;

        System.out.println("이름:" + s.name);
        System.out.println("총점:" + s.getTotal());
        System.out.println("평균:" + s.getAverage());
    }

    static class Student {
        String name;
        int ban, no, kor, eng, math;

        public int getTotal() {
            return kor + eng + math;
        }

        public float getAverage() {
            float avg = (kor + eng + math) / 3f;
            return Math.round(avg * 100) / 100f;
        }

    }
}