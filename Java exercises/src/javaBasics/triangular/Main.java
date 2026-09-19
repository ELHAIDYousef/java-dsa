package javaBasics.triangular;

public class Main {
    public static void main(String[] args) {

    }

    static int triangular(int n) {
        int sum = 0;
        for (int i = 1; i <= n ; i++) {
            sum += i;
        }
        return sum;
    }
}
