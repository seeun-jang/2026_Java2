package ai0922;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
// 이미지 파일과 같은 이진 파일은 1바이트씩 읽어서 1바이트씩 출력해야한다.
public class ImageFileCopy {

    public static void main(String[] args) {

        try {
            FileInputStream fIn = new FileInputStream("fire.jpg");
            FileOutputStream fOut = new FileOutputStream("fireCopy.jpg");

            int data;

            while ((data = fIn.read()) != -1) {
                fOut.write((byte) data);
            }

            fIn.close();
            fOut.close();

            System.out.println("이미지 파일 복사 완료");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
