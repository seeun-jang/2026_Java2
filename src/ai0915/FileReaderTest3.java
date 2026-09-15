package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest3 {

    // 파일의 내용 전체를 거꾸로 출력.

    public static void main(String[] args) {

        try {
            StringBuilder sb = new StringBuilder();

            // 파일 읽기
            BufferedReader br =
                    new BufferedReader(new FileReader("myData1.txt"));

            String line = "";

            // 파일 끝까지 한 줄씩 읽기
            while (true) {
                line = br.readLine();

                if (line == null) {
                    break;
                }

                sb.append(line).append("\n");
            }

            // 파일 닫기
            br.close();


            sb.reverse();

            System.out.println(sb);


            // 전체 내용 출력
            System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}