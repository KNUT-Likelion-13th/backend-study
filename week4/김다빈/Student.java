public class Student {
    public String name;
    public int ban;
    public int no;
    public int kor;
    public int eng;
    public int math;
    public int total;

    public int getTotal() {
        this.total = kor + eng + math;
        return total;
    }

    public String getAverage() {
        float avg = total / 3f;
        String rsult = String.format("%.2f", avg);
        return (rsult);
    }
}
