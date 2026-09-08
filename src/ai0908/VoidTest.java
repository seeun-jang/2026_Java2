package ai0908;

public class VoidTest {

    public static void printLine(char c, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        char[] characters = {'★', '♥', '♣', '◆', '●', '▲', '♠', '♪'};
        int[] counts = {10, 20, 30, 40, 50, 60, 70, 80};

        for (int i = 0; i < characters.length; i++) {
            printLine(characters[i], counts[i]);
        }
    }
}