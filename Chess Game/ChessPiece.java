public class ChessPiece {
    
    int type;
    char symbol;
    int lastTurnMoved;
    String previousPosition;
    String currentPosition;
    int player;
    
    public ChessPiece(int type, char symbol, String currentPosition, int player) {
        this.type = type;
        this.symbol = symbol;
        this.previousPosition = currentPosition;
        this.currentPosition = currentPosition;
        this.player = player;
    }
    
    public char getSymbol() { 
        return symbol;
    }
    
    public String getPreviousPosition() {
        return previousPosition;
    }
    
    public String getCurrentPosition() { 
        return currentPosition;
    }
    
    public int getType() {
        return type;
    }
    
    public int getPlayer() {
        return player;
    }
    
    public int getLastTurnMoved() {
        return lastTurnMoved;
    }
    
    public void move(Player player, Player opponent, int currentRound, ChessPiece[][] board) { //dummy method
    }
    
    //capture dummy method
    public boolean capture(Player player, Player opponent, ChessPiece[][] board, String desiredCords) {
        return false;
    }
    
    public static boolean validifyPosition(String position) {
        if (position.length() != 2 || position.charAt(0) < 'A' || position.charAt(0) > 'Z' || position.charAt(1) < '1' || position.charAt(1) > '8') {
            return false;
        }
        return true;
    }
    
    public static int[] convertToIndices(String position) {
        int[] indices = new int[2];
        indices[0] = '8' - position.charAt(1);
        indices[1] = position.charAt(0) - 'A';
        return indices;
    }
    
    public static boolean checkForPiece(ChessPiece[][] board, int[] indices) {
        if (board[indices[0]][indices[1]].getType() > 0) {
            return true;
        }
        return false;
    }
    
    public static boolean checkIfPlayersPiece(Player player, int[] indices, ChessPiece[][] board) {
        if (board[indices[0]][indices[1]].getPlayer() == player.getNumber()) {
            return true;
        }
        return false;
    }
    
    public boolean piecesBetween(String desiredPosition, ChessPiece[][] board) {
        int[] currentIndices = convertToIndices(currentPosition);
        int[] desiredIndices = convertToIndices(desiredPosition);
        
        if (desiredIndices[0] - currentIndices[0] != 0 && desiredIndices[1] - currentIndices[1] != 0) {
            if (desiredIndices[0] - currentIndices[0] < 0 && desiredIndices[1] - currentIndices[1] < 0) {
                for (int row = desiredIndices[0] + 1, col = desiredIndices[1] + 1; row < currentIndices[0] && col < currentIndices[1]; row++, col++) {
                    if (board[row][col].getType() > 0)
                        return true;
                }
            }
            else if (desiredIndices[0] - currentIndices[0] < 0 && desiredIndices[1] - currentIndices[1] > 0) {
                for (int row = desiredIndices[0] + 1, col = desiredIndices[1] - 1; row < currentIndices[0] && col > currentIndices[1]; row++, col--) {
                    if (board[row][col].getType() > 0)
                        return true;
                }
            }
            else if (desiredIndices[0] - currentIndices[0] > 0 && desiredIndices[1] - currentIndices[1] < 0) {
                for (int row = desiredIndices[0] - 1, col = desiredIndices[1] + 1; row > currentIndices[0] && col < currentIndices[1]; row--, col++) {
                    if (board[row][col].getType() > 0)
                        return true;
                }
            }
            else {
                for (int row = desiredIndices[0] - 1, col = desiredIndices[1] - 1; row > currentIndices[0] && col > currentIndices[1]; row--, col--) {
                    if (board[row][col].getType() > 0)
                        return true;
                }
            }
        }
        else if (desiredIndices[0] - currentIndices[0] != 0) {
            if (desiredIndices[0] < currentIndices[0]) {
                for (int row = desiredIndices[0] + 1; row < currentIndices[0]; row++) {
                    if (board[row][desiredIndices[1]].getType() > 0) 
                        return true;
                }
            }
            else {
                for (int row = desiredIndices[0] - 1; row > currentIndices[0]; row--) {
                    if (board[row][desiredIndices[1]].getType() > 0) 
                        return true;
                }
            }
        }
        else if (desiredIndices[1] - currentIndices[1] != 0) {
            if (desiredIndices[1] < currentIndices[1]) {
                for (int col = desiredIndices[1] + 1; col < currentIndices[1]; col++) {
                    if (board[desiredIndices[0]][col].getType() > 0)
                        return true;
                }
            }
            else {
                for (int col = desiredIndices[1] - 1; col > currentIndices[1]; col--) {
                    if (board[desiredIndices[0]][col].getType() > 0)
                        return true;
                }
            }
        }
        return false;
    }
    
    public static boolean checkIfKing(int[] desiredIndices, ChessPiece[][] board) {
        if (board[desiredIndices[0]][desiredIndices[1]].getType() == 6) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return symbol + " (Type " + type + ") chess piece belonging to Player " + player + ", last moved from " + previousPosition + " to " + currentPosition + " in round " + lastTurnMoved;
    }
}