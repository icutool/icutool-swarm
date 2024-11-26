package cn.icutool;

public class FastTest {
    public static void main(String[] args) {
        int count = 47;
        int pageSize = 10;
        int result = (int) Math.ceil((double) count / pageSize);
        System.out.println(result);

    }
}
