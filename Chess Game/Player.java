import java.util.ArrayList;

public class Player {
    
    String name;
    int number;
    ArrayList<ChessPiece> piecesInPlay;
    ArrayList<ChessPiece> capturedPieces;
    int wins;
    int losses;
    
    public Player(String name, int number) {
        this.name = name;
        this.number = number;
        piecesInPlay = new ArrayList<ChessPiece>();
        capturedPieces = new ArrayList<ChessPiece>();
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public ArrayList<ChessPiece> getPiecesInPlay() {
        return piecesInPlay;
    }
    
    public ArrayList<ChessPiece> getCapturedPieces() {
        return capturedPieces;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void takeTurn(Player opponent, int currentRound, ChessPiece[][] board) {
		System.out.println("\u001B[1m\u001B[4m" + name + "'s turn:\u001B[0m");
		if (determineCheck(opponent, board)) {
		    System.out.println("\u001B[1m\u001B[31mWarning \u001B[4m" + name + "\u001B[0m\u001B[1m\u001B[31m: you are in check!\u001B[0m\n");
		}
		ChessPiece currentPiece = Main.choosePiece(this);
		System.out.println();
		currentPiece.move(this, opponent, currentRound, board);
    }
    
    public boolean determineCheck(Player opponent, ChessPiece[][] board) {
        ChessPiece playerKing = piecesInPlay.get(piecesInPlay.size() - 1);
        String kingPosition = playerKing.getCurrentPosition();
        for (int i = 0; i < opponent.getPiecesInPlay().size(); i++) {
            if (opponent.getPiecesInPlay().get(i).capture(opponent, this, board, kingPosition)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean possibleCheck(Player opponent, String position, ChessPiece[][] board) {
        String desiredCords = position;
        if (ChessPiece.validifyPosition(desiredCords)) {
            for (int i = 0; i < opponent.getPiecesInPlay().size(); i++) {
                if (opponent.getPiecesInPlay().get(i).capture(opponent, this, board, desiredCords)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void surrender(Player opponent, ChessPiece[][] board) {
        Main.printBoard(board);
        System.out.println("\u001B[1m\u001B[31m\u001B[4m" + name + "\u001B[0m\u001B[1m\u001B[31m surrenders!\u001B[0m");
        System.out.println("\u001B[1m\u001B[32mCongrats, \u001B[4m" + opponent.getName() + "\u001B[0m\u001B[1m\u001B[32m, you win!!\u001B[0m");
        
        System.out.println();
        System.out.print("\u001B[1m\u001B[4m" + name + "'s remaining pieces:\u001B[0m ");
        displayPiecesInPlay();
        System.out.print("\u001B[1m\u001B[4m" + name + "'s captured opponent pieces:\u001B[0m ");
        displayCapturedPieces();
        
        System.out.println();
        System.out.print("\u001B[1m\u001B[4m" + opponent.getName() + "'s remaining pieces:\u001B[0m ");
        opponent.displayPiecesInPlay();
        System.out.print("\u001B[1m\u001B[4m" + opponent.getName() + "'s captured opponent pieces:\u001B[0m ");
        opponent.displayCapturedPieces();
        
        System.exit(1);
    }
    
    public void displayPiecesInPlay() { 
        for (ChessPiece piece : piecesInPlay) {
            System.out.print(piece.symbol + " ");
        }
        System.out.println();
    }
    
    public void displayCapturedPieces() { 
        for (ChessPiece piece : capturedPieces) {
            System.out.print(piece.symbol + " ");
        }
        System.out.println();
    }
}