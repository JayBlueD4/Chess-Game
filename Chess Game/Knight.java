import java.util.Scanner;

public class Knight extends ChessPiece {
    Scanner input = new Scanner(System.in);
    
    public Knight(char symbol, String currentPosition, int player) {
        super(2, symbol, currentPosition, player);
    }
    
    @Override
    public void move(Player player, Player opponent, int currentRound, ChessPiece[][] board) {
        System.out.print("Please enter the coordinates of the position you wish to move to (e.g. A3):\u001B[1m ");
        String desiredCords = input.next().toUpperCase();
        System.out.print("\u001B[0m");
        int[] currentIndices = convertToIndices(currentPosition);
        int[] desiredIndices = convertToIndices(desiredCords);
        
        if (validifyPosition(desiredCords)) {
            if (!checkForPiece(board, desiredIndices) && verifyMovement(desiredCords)) {
                
                //Update instance vars
                String initialPreviousPosition = previousPosition;
                int initialLastTurn = lastTurnMoved;
                
                lastTurnMoved = currentRound;
                previousPosition = currentPosition;
                currentPosition = desiredCords;
                
                //Update board    
                ChessPiece temp = board[desiredIndices[0]][desiredIndices[1]];
                board[desiredIndices[0]][desiredIndices[1]] = this;
                board[currentIndices[0]][currentIndices[1]] = new ChessPiece(0, ' ', previousPosition, 0);
                
                //Undo if move results in check 
                if (player.determineCheck(opponent, board)) {
                    lastTurnMoved = initialLastTurn;
                    currentPosition = previousPosition;
                    previousPosition = initialPreviousPosition;
                    board[desiredIndices[0]][desiredIndices[1]] = new ChessPiece(0, ' ', desiredCords, 0);
                    board[currentIndices[0]][currentIndices[1]] = this;
                    
                    System.out.println("\n\u001B[1m\u001B[31mError: move renders king in check.\u001B[0m");
                    System.out.print("Please either move to a different position (1), select another piece (2), or \u001B[1m\u001B[31msurrender\u001B[0m (3): ");
                    int choice = input.nextInt();
                    while (choice < 1 || choice > 3) {
                        System.out.println("\u001B[1m\u001B[31mError: invalid option. Please try again.\u001B[0m");
                        System.out.print("Move to a different position (1), select another piece (2), or \u001B[1m\u001B[31msurrender\u001B[0m (3): ");
                        choice = input.nextInt();
                    }
                    switch (choice) {
                        case 1: System.out.println();
                                this.move(player, opponent, currentRound, board);
                                break;
                        case 2: System.out.println();
                                ChessPiece currentPiece = Main.choosePiece(player);
                                System.out.println();
                                currentPiece.move(player, opponent, currentRound, board);
                                break;
                        case 3: System.out.println();
                                player.surrender(opponent, board);
                    }
                    return;
                }
                
                System.out.println("\u001B[1m\u001B[32mKnight moved from " + previousPosition + " to " + currentPosition + "\u001B[0m");
                
                Main.printBoard(board);
                return;
            }
            
            if (capture(player, opponent, board, desiredCords) && !checkIfKing(desiredIndices, board)) {
                ChessPiece capturedPiece = board[desiredIndices[0]][desiredIndices[1]];
                int capturedPieceIndex = Main.findPieceByPosition(capturedPiece.getCurrentPosition(), opponent.getPiecesInPlay());
                
                //Update instance vars
                String initialPreviousPosition = previousPosition;
                int initialLastTurn = lastTurnMoved;
                
                lastTurnMoved = currentRound;
                previousPosition = currentPosition;
                currentPosition = desiredCords;
                
                //Update board
                board[desiredIndices[0]][desiredIndices[1]] = this;
                board[currentIndices[0]][currentIndices[1]] = new ChessPiece(0, ' ', previousPosition, 0);
                
                //Adding & Removing piece accordingly
                player.getCapturedPieces().add(capturedPiece);
                opponent.getPiecesInPlay().remove(Main.findPieceByPosition(desiredCords, opponent.getPiecesInPlay()));
                
                //Undo if capture results in check
                if (player.determineCheck(opponent, board)) {
                    lastTurnMoved = initialLastTurn;
                    currentPosition = previousPosition;
                    previousPosition = initialPreviousPosition;
                    
                    board[desiredIndices[0]][desiredIndices[1]] = capturedPiece;
                    board[currentIndices[0]][currentIndices[1]] = this;
                    player.getCapturedPieces().remove(player.getCapturedPieces().size() - 1);
                    opponent.getPiecesInPlay().add(capturedPieceIndex, capturedPiece);
                    
                    System.out.println("\n\u001B[1m\u001B[31mError: move renders king in check.\u001B[0m");
                    System.out.print("Please either move to a different position (1), select another piece (2), or \u001B[1m\u001B[31msurrender\u001B[0m (3): ");
                    int choice = input.nextInt();
                    while (choice < 1 || choice > 3) {
                        System.out.println("\u001B[1m\u001B[31mError: invalid option. Please try again.\u001B[0m");
                        System.out.print("Move to a different position (1), select another piece (2), or \u001B[1m\u001B[31msurrender\u001B[0m (3): ");
                        choice = input.nextInt();
                    }
                    switch (choice) {
                        case 1: System.out.println();
                                this.move(player, opponent, currentRound, board);
                                break;
                        case 2: System.out.println();
                                ChessPiece currentPiece = Main.choosePiece(player);
                                System.out.println();
                                currentPiece.move(player, opponent, currentRound, board);
                                break;
                        case 3: System.out.println();
                                player.surrender(opponent, board);
                    }
                    return;
                }
                
                System.out.print("\u001B[1m\u001B[32mYou have captured the opponent's ");
                switch (capturedPiece.getType()) {
                    case 1: System.out.print("pawn");
                            break;
                    case 2: System.out.print("knight");
                            break;
                    case 3: System.out.print("bishop");
                            break;
                    case 4: System.out.print("rook");
                            break;
                    case 5: System.out.print("queen");
                }
                System.out.println(" at " + desiredCords + "!\u001B[0m");
                
                //Display results of move
                Main.printBoard(board);
                return;
            }
        }
        System.out.println("\n\u001B[1m\u001B[31mError: invalid move. \u001B[0m");
        System.out.print("Please either move to a different position (1) or select another piece (2): ");
        int choice = input.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("\u001B[1m\u001B[31mError: invalid option. Please try again.\u001B[0m");
            System.out.print("Would you like to move to a different position (1) or select another piece (2): ");
            choice = input.nextInt();
        }
        switch (choice) {
            case 1: System.out.println();
                    this.move(player, opponent, currentRound, board);
                    break;
            case 2: System.out.println();
                    ChessPiece currentPiece = Main.choosePiece(player);
                    System.out.println();
                    currentPiece.move(player, opponent, currentRound, board);
        }
    }
    
    @Override
    public boolean capture(Player player, Player opponent, ChessPiece[][] board, String desiredCords) {
        int[] desiredIndices = convertToIndices(desiredCords);
        
        if (validifyPosition(desiredCords) && checkIfPlayersPiece(opponent, desiredIndices, board) && verifyMovement(desiredCords)) {
            return true;
        }
        return false;
    }
    
    public boolean verifyMovement(String desiredCords) {
        if (((desiredCords.charAt(0) - currentPosition.charAt(0) == -1 || desiredCords.charAt(0) - currentPosition.charAt(0) == 1)
          && (desiredCords.charAt(1) - currentPosition.charAt(1) == -2 || desiredCords.charAt(1) - currentPosition.charAt(1) == 2))
          || ((desiredCords.charAt(0) - currentPosition.charAt(0) == -2 || desiredCords.charAt(0) - currentPosition.charAt(0) == 2)
          && (desiredCords.charAt(1) - currentPosition.charAt(1) == -1 || desiredCords.charAt(1) - currentPosition.charAt(1) == 1))) {
            return true;    
        }
        return false;
    }
}