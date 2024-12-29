public class mineSweeper {
    private int [][] gameBoard; // 0-8: number of mines around, 9: mine, 10: flag 

    public mineSweeper(String size) {
        int boardSize = 0;
        switch (size) {
            case "small" -> boardSize = 10;
            case "medium" -> boardSize = 15;
            case "large" -> boardSize = 20;
        }
        gameBoard = new int[boardSize][boardSize];
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
    public int getGameBoard(int x, int y) {
        return gameBoard[x][y];
    }


    
}
