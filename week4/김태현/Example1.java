package week_4;

public class Example1 {
    String name;
    int ban;
    int no;
    int kor;
    int eng;
    int math;

    public int getTotal() {
        return kor + eng + math;
    }

    public float getAverage() {
        float avg = getTotal() / 3.0f;
        return Math.round(avg * 10) / 10.0f;
    }
}
