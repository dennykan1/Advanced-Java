import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReVolt {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(read.readLine());
        String[][] matrix = new String[size][size];

        int commandCount = Integer.parseInt(read.readLine());
        int[] playerRowCol = new int[2];
        for (int i = 0; i < size; i++) {
            String[] data = read.readLine().split("");

            for (int j = 0; j < data.length; j++) {
                matrix[i][j] = data[j];
                if (data[j].equals("f")) {
                    playerRowCol[0] = i;
                    playerRowCol[1] = j;
                }
            }
        }

        boolean playerIsStepFinal = false;

        for (int i = 0; i < commandCount; i++) {
            String command = read.readLine();
            matrix[playerRowCol[0]][playerRowCol[1]] = "-";

            movePlayer(playerRowCol, command);

            playerRowCol[0] = validatePlayerIndexes(size, playerRowCol[0]);
            playerRowCol[1] = validatePlayerIndexes(size, playerRowCol[1]);

            String fieldElement = matrix[playerRowCol[0]][playerRowCol[1]];
            switch (fieldElement) {
                case "B":
                    movePlayer(playerRowCol, command);
                    break;
                case "T":
                    trapMove(playerRowCol, command);
                    break;
            }
            playerRowCol[0] = validatePlayerIndexes(size, playerRowCol[0]);
            playerRowCol[1] = validatePlayerIndexes(size, playerRowCol[1]);

            fieldElement = matrix[playerRowCol[0]][playerRowCol[1]];

            if (fieldElement.equals("F")) {
                matrix[playerRowCol[0]][playerRowCol[1]] = "f";
                playerIsStepFinal = true;
                break;
            }

        }

        if (playerIsStepFinal) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
            matrix[playerRowCol[0]][playerRowCol[1]] = "f";
        }
        printMatrix(matrix);

    }

    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void trapMove(int[] playerRowCol, String command) {
        switch (command) {
            case "up":
                playerRowCol[0]++;
                break;
            case "down":
                playerRowCol[0]--;
                break;
            case "left":
                playerRowCol[1]++;
                break;
            case "right":
                playerRowCol[1]--;
                break;
        }
    }

    private static void movePlayer(int[] playerRowCol, String command) {

        switch (command) {
            case "up":
                playerRowCol[0]--;
                break;
            case "down":
                playerRowCol[0]++;
                break;
            case "left":
                playerRowCol[1]--;
                break;
            case "right":
                playerRowCol[1]++;
                break;
        }
    }

    private static int validatePlayerIndexes(int size, int playerRow) {
        if (playerRow < 0) {
            return size - 1;
        }
        if (playerRow >= size) {
            return 0;
        }
        return playerRow;
    }
}
