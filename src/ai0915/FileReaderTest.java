package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

    public static void main(String[] args) {

        try {
            // File을 읽어오기 위한 입력스트림 생성
            BufferedReader br = new BufferedReader(new FileReader("D:/FileIOTest/myData1.txt")); //절대경로

            String line;

            // 파일의 끝까지 한 줄씩 읽기
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // 입력스트림 닫기
            br.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
