package ai0922;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LAB_FileCopy {

    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("myData1.txt");
            FileWriter fw = new FileWriter("newFile.txt");

            int data;

            while ((data = fr.read()) != -1) {
                fw.write(data);
            }

            fr.close();
            fw.close();

            System.out.println("--- myData1.txt가 newFile.txt로 복사되었음 ---");

        } catch (IOException e) {
            System.out.println("파일 복사 중 오류가 발생했습니다.");
        }
    }
}