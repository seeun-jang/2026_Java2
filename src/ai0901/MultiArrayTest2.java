package ai0901;

import java.util.Scanner;

public class MultiArrayTest2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] arr = new int[3][4];

        // 배열 값 입력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                System.out.print("arr[" + i + "][" + j + "] 값 입력 : ");
                arr[i][j] = sc.nextInt();

            }
        }

        System.out.println("<arr[0][0] ~ arr[2][3] 요소에 저장된 값을 출력>");

        // 배열 값 출력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                System.out.printf("%3d", arr[i][j]);

            }
            System.out.println();
        }

        sc.close();
    }
}
