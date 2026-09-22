package ai0922;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterTest1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("myData2.txt");

            while (true) {
                System.out.print("내용 입력 (종료: exit) : ");

                String line = scanner.nextLine();

                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                fw.write(line + "\n");
            }

            fw.close();

            System.out.println("myData2.txt에 내용 저장 완료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }
}
