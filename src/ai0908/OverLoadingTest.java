package ai0908;

public class OverLoadingTest {

    // 정수 2개 계산
    public static int calc(int n1, int n2, char operator) {
        int result = 0;

        switch (operator) {
            case '+':
                result = n1 + n2;
                break;

            case '-':
                result = n1 - n2;
                break;

            case '*':
                result = n1 * n2;
                break;

            case '/':
                result = n1 / n2;
                break;
        }

        return result;
    }


    // 정수 3개 계산
    public static int calc(int n1, int n2, int n3, char operator) {
        int result = 0;

        switch (operator) {
            case '+':
                result = n1 + n2 + n3;
                break;

            case '-':
                result = n1 - n2 - n3;
                break;

            case '*':
                result = n1 * n2 * n3;
                break;

            case '/':
                result = n1 / n2 / n3;
                break;
        }

        return result;
    }


    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 5;
        int n3 = 7;
        char op = '+';

        // 정수 2개 계산
        System.out.printf(
                "%d %c %d = %d\n",
                n1, op, n2, calc(n1, n2, op)
        );

        // 정수 3개 계산
        System.out.printf(
                "%d %c %d %c %d = %d\n",
                n1, op, n2, op, n3, calc(n1, n2, n3, op)
        );
    }
}
