// student클래스에서 다음과 같이 정의된 두 개의 매서드 getTotal(), getAverage()를
//추가하시오


package java_backend4_8;

class 과제1 {
    public static void main(String[] args) {
        Student s = new Student();


        s.name = "홍길동";
        s.kor = 90;
        s.eng = 85;
        s.math = 100;

        System.out.println("이름 : " + s.name);
        System.out.println("국어 : " + s.kor);
        System.out.println("영어 : "  + s.eng);
        System.out.println("수학 : " + s.math);

        System.out.println("총점 : " + s.gettotal());
        System.out.println("평균 : " + s.getAverage());




    }
}

class Student {
    String name;
    int kor;
    int eng;
    int math;


    int gettotal() {
        return kor + eng + math;
    }

    double getAverage() {
        int total = gettotal();
        double avg = total / 3.0;
        avg = Math.round(avg * 100) / 100.0;
        return avg;
    }



}
