
import java.util.LinkedList;
import java.util.Queue;

class cordination {

    private int x;
    private int y;

    public cordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

public class mineSweeper {

    private int[][] gameBoard; // 0-8: number of mines around, 9: mine, 10: flag 
    private boolean[][] flags;
    private boolean[][] opens;
    private view view;

    public mineSweeper(String size, view view) {
        this.view = view;
        int boardSize = 0;
        switch (size) {
            case "easy" ->
                boardSize = 10;
            case "medium" ->
                boardSize = 15;
            case "hard" ->
                boardSize = 20;
        }
        gameBoard = new int[boardSize][boardSize];
        flags = new boolean[boardSize][boardSize];
        opens = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = 0;
            }
        }
        int mines = boardSize * boardSize / 10;
        for (int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * boardSize);
            int y = (int) (Math.random() * boardSize);
            if (gameBoard[x][y] == 9) {
                i--;
            } else {
                gameBoard[x][y] = 9;
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (x + j >= 0 && x + j < boardSize && y + k >= 0 && y + k < boardSize) {
                            if (gameBoard[x + j][y + k] != 9) {
                                gameBoard[x + j][y + k]++;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getCell(int x, int y) {
        return gameBoard[x][y];
    }

    public int getBoardSize() {
        return gameBoard.length;
    }

    public void flag(int x, int y) {
        flags[x][y] = !flags[x][y];
    }

    public void openCell(int x, int y) {
        opens[x][y] = true;
    }

    public void open(int x, int y) {
        Queue < cordination > queue = new LinkedList < > ();

        if (canOpenAround(x, y)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (isValid(x +i -1, y + j -1)){
                        if (!flags[x + i - 1][y + j - 1]) {
                            if (gameBoard[x + i - 1][y + j - 1] == 9) {
                                System.out.println("Game Over");
                            } else {
                                view.changeTheTextOfButton(x + i - 1, y + j - 1, gameBoard[x + i - 1][y + j - 1]);
                                opens[x + i - 1][y + j - 1] = true;
                                queue.add(new cordination(x + i - 1, y + j - 1));
                            }
                        }
                    }
                }
        }
        
        while (!queue.isEmpty()){
            open(queue.peek().getX(), queue.peek().getY());
            queue.remove();
        }
    }

    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < gameBoard.length && y >= 0 && y < gameBoard.length;
    }

    private boolean canOpenAround(int x, int y) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isValid (x + i - 1, y + j - 1) && flags[x + i - 1][y + j - 1]) {
                        counter++;
                    }
            }
            
        }
        return counter == gameBoard[x][y];
    }
}
