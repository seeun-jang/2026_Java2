package ai0922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LAB_AddLineNumber {

    public static void main(String[] args) {

        String fileName = "myData1.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                System.out.println(lineNumber + " : " + line);
                lineNumber++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다.");
        }
    }
}
