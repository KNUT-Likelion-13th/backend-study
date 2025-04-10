package week4.김민우;

public class p1 {
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
    private static class Student {
        String name;
        int ban;
        int no;
        int kor;
        int eng;
        int math;

        int getTotal() {
            return kor + eng + math;
        }

        double getAverage() {
//            return Math.round(getTotal() * 10.0 / 3);         // -> 787.0
            return Math.round(getTotal() * 10.0 / 3) / 10.0;    // -> 78.7
        }
    }
}
