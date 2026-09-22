package ai0922;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LAB_EncryptMessage {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("secretMessage.txt");

            while (true) {

                System.out.print("문자열 입력 (종료: exit) : ");
                String inStr = scanner.nextLine();

                if (inStr.equalsIgnoreCase("exit")) {
                    break;
                }

                String secure = "";

                for (int i = 0; i < inStr.length(); i++) {
                    int num = (int) inStr.charAt(i);
                    num += 100;
                    secure += (char) num;
                }

                fw.write(secure + "\n");
            }

            fw.close();

            System.out.println("암호화된 문자열이 secretMessage.txt에 저장되었습니다.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }
}

