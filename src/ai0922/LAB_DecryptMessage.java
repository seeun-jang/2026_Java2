package ai0922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LAB_DecryptMessage {

    public static void main(String[] args) {

        try {
            BufferedReader br =
                    new BufferedReader(new FileReader("secretMessage.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String original = "";

                for (int i = 0; i < line.length(); i++) {

                    int num = (int) line.charAt(i);

                    num -= 100;

                    original += (char) num;
                }

                System.out.println(original);
            }

            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
