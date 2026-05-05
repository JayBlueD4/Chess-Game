import java.util.Scanner;

public class Pawn extends ChessPiece {
    Scanner input = new Scanner(System.in);
    
    public Pawn(char symbol, String currentPosition, int player) {
        super(1, symbol, currentPosition, player);
    }
    
    @Override
    public void move(Player player, Player opponent, int currentRound, ChessPiece[][] board) {
        System.out.print("Please enter the coordinates of the position you wish to move to (e.g. A3):\u001B[1m ");
        String desiredCords = input.next().toUpperCase();
        System.out.print("\u001B[0m");
        int[] currentIndices = convertToIndices(currentPosition);
        int[] desiredIndices = convertToIndices(desiredCords);
        
        if (validifyPosition(desiredCords)) {
            switch (player.getNumber()) {
                case 1: if (desiredCords.charAt(1) - currentPosition.charAt(1) == 1 && desiredCords.charAt(0) - currentPosition.charAt(0) == 0 
                           && !checkForPiece(board, desiredIndices)) {
                            
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
                            
                            System.out.println("\u001B[1m\u001B[32mPawn moved from " + previousPosition + " to " + currentPosition + "\u001B[0m");
                            
                            //Check for promotion
                            if (currentPosition.charAt(1) == '8') {
                                this.promotion(player, board);
                            }
                            
                            Main.printBoard(board);
                            return;
                        }
                        if (desiredCords.charAt(1) - currentPosition.charAt(1) == 2 && desiredCords.charAt(0) - currentPosition.charAt(0) == 0
                           && !piecesBetween(desiredCords, board) 
                           && !checkForPiece(board, desiredIndices) && this.lastTurnMoved == 0) {
                            
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
                            
                            System.out.println("\u001B[1m\u001B[32mPawn moved from " + previousPosition + " to " + currentPosition + "\u001B[0m");
                            
                            Main.printBoard(board);
                            return;
                        } break;
                        
                case 2: if (desiredCords.charAt(1) - currentPosition.charAt(1) == -1 && desiredCords.charAt(0) - currentPosition.charAt(0) == 0
                           && !checkForPiece(board, desiredIndices)) {
                            
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
                            
                            System.out.println("\u001B[1m\u001B[32mPawn moved from " + previousPosition + " to " + currentPosition + "\u001B[0m");
                            
                            //Check for promotion
                            if (currentPosition.charAt(1) == '1') {
                                this.promotion(player, board);
                            }
                            
                            Main.printBoard(board);
                            return;
                        }
                        if (desiredCords.charAt(1) - currentPosition.charAt(1) == -2 && desiredCords.charAt(0) - currentPosition.charAt(0) == 0
                           && !piecesBetween(desiredCords, board) 
                           && !checkForPiece(board, desiredIndices) && this.lastTurnMoved == 0) {
                            
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
                            
                            System.out.println("\u001B[1m\u001B[32mPawn moved from " + previousPosition + " to " + currentPosition + "\u001B[0m");
                            
                            Main.printBoard(board);
                            return;
                        } 
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
                
                //Check for promotion
                switch (player.getNumber()) {
                    case 1: if (currentPosition.charAt(1) == '8') {
                                this.promotion(player, board);
                            }
                            break;
                    case 2: if (currentPosition.charAt(1) == '1') {
                                this.promotion(player, board);
                            }
                }
                
                //Display results of move
                Main.printBoard(board);
                return;
            }
            
            if (enPassant(player, board, desiredCords, currentRound)) {
                String enPassantCords = "";
                switch (player.getNumber()) {
                    case 1: enPassantCords = "" + desiredCords.charAt(0) + (char)(desiredCords.charAt(1) - 1);
                            break;
                    case 2: enPassantCords = "" + desiredCords.charAt(0) + (char)(desiredCords.charAt(1) + 1);
                }
                int[] enPassantIndices = convertToIndices(enPassantCords);
                ChessPiece enPassantPiece = board[enPassantIndices[0]][enPassantIndices[1]];
                int enPassantPieceIndex = Main.findPieceByPosition(enPassantCords, opponent.getPiecesInPlay());
                
                //Update instance vars
                String initialPreviousPosition = previousPosition;
                int initialLastTurn = lastTurnMoved;
                
                lastTurnMoved = currentRound;
                previousPosition = currentPosition;
                currentPosition = desiredCords;
                
                //Update board
                board[desiredIndices[0]][desiredIndices[1]] = this;
                board[currentIndices[0]][currentIndices[1]] = new ChessPiece(0, ' ', previousPosition, 0);
                
                //Adding & removing piece accordingly
                player.getCapturedPieces().add(enPassantPiece);
                opponent.getPiecesInPlay().remove(Main.findPieceByPosition(enPassantCords, opponent.getPiecesInPlay()));
                board[enPassantIndices[0]][enPassantIndices[1]] = new ChessPiece(0, ' ', enPassantCords, 0);
                
                if (player.determineCheck(opponent, board)) {
                    lastTurnMoved = initialLastTurn;
                    currentPosition = previousPosition;
                    previousPosition = initialPreviousPosition;
                    
                    board[enPassantIndices[0]][enPassantIndices[1]] = enPassantPiece;
                    board[desiredIndices[0]][desiredIndices[1]] = new ChessPiece(0, ' ', desiredCords, 0);
                    board[currentIndices[0]][currentIndices[1]] = this;
                    player.getCapturedPieces().remove(player.getCapturedPieces().size() - 1);
                    opponent.getPiecesInPlay().add(enPassantPieceIndex, enPassantPiece);
                    
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
                
                System.out.println("\u001B[1m\u001B[32mYou have captured the opponent's pawn at " + enPassantCords + " using En Passant\u001B[0m");
                
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
        
        if (ChessPiece.validifyPosition(desiredCords) && checkIfPlayersPiece(opponent, desiredIndices, board)) {
            switch (player.getNumber()) {
                case 1: if (desiredCords.charAt(1) - currentPosition.charAt(1) == 1 
                          && (desiredCords.charAt(0) - currentPosition.charAt(0) == 1 || desiredCords.charAt(0) - currentPosition.charAt(0) == -1) 
                          && checkForPiece(board, desiredIndices)) {
                              return true;  
                        } break;
                    
                case 2: if (desiredCords.charAt(1) - currentPosition.charAt(1) == -1 
                          && (desiredCords.charAt(0) - currentPosition.charAt(0) == 1 || desiredCords.charAt(0) - currentPosition.charAt(0) == -1) 
                          && checkForPiece(board, desiredIndices)) {
                              return true;  
                        }
            }
        }
        return false;
    }
    
    public boolean enPassant(Player player, ChessPiece[][] board, String desiredCords, int currentRound) {
        int[] desiredIndices = convertToIndices(desiredCords);
        
        if (validifyPosition(desiredCords)) {
            switch (player.getNumber()) {
                case 1: if (validifyPosition("" + desiredCords.charAt(0) + (char)(desiredCords.charAt(1) - 1))) {
                            ChessPiece enPassantPiece = board[desiredIndices[0] + 1][desiredIndices[1]];
                            if (!checkForPiece(board, desiredIndices) && desiredCords.charAt(1) - currentPosition.charAt(1) == 1
                              && (desiredCords.charAt(0) - currentPosition.charAt(0) == -1 || desiredCords.charAt(0) - currentPosition.charAt(0) == 1)
                              && enPassantPiece.getType() == 1 && enPassantPiece.getPlayer() == 2
                              && enPassantPiece.getCurrentPosition().charAt(1) - enPassantPiece.getPreviousPosition().charAt(1) == -2 
                              && enPassantPiece.getLastTurnMoved() == currentRound - 1) {
                                return true;
                            }
                        } break;
                case 2: if (validifyPosition("" + desiredCords.charAt(0) + (char)(desiredCords.charAt(1) + 1))) {
                            ChessPiece enPassantPiece = board[desiredIndices[0] - 1][desiredIndices[1]];
                            if (!checkForPiece(board, desiredIndices) && desiredCords.charAt(1) - currentPosition.charAt(1) == -1
                              && (desiredCords.charAt(0) - currentPosition.charAt(0) == -1 || desiredCords.charAt(0) - currentPosition.charAt(0) == 1)
                              && enPassantPiece.getType() == 1 && enPassantPiece.getPlayer() == 1
                              && enPassantPiece.getCurrentPosition().charAt(1) - enPassantPiece.getPreviousPosition().charAt(1) == 2 
                              && enPassantPiece.getLastTurnMoved() == currentRound) {
                                return true;
                            }
                        }
            }
        }
        return false;
    }
    
    public void promotion(Player player, ChessPiece[][] board) {
        int[] currentIndices = convertToIndices(currentPosition);
        
        System.out.print("\nWhich piece would you like to promote your pawn to (2 - knight, 3 - bishop, 4 - rook, 5 - queen): ");
        int promotionType = input.nextInt();
        while (promotionType < 2 || promotionType > 5) {
            System.out.println("\n\u001B[1m\u001B[31mError: invalid promotion type. Please try again.\u001B[0m");
            System.out.print("Select the piece to promote your pawn to (2 - knight, 3 - bishop, 4 - rook, 5 - queen): ");
            promotionType = input.nextInt();
        }
        switch (promotionType) {
            case 2: System.out.println("\u001B[1m\u001B[32mPawn promoted to knight.\u001B[0m");
                    board[currentIndices[0]][currentIndices[1]] = new Knight((this.player == 1) ? '\u265E' : '\u2658', currentPosition, this.player);
                    break;
            case 3: System.out.println("\u001B[1m\u001B[32mPawn promoted to bishop.\u001B[0m");
                    board[currentIndices[0]][currentIndices[1]] = new Bishop((this.player == 1) ? '\u265D' : '\u2657', currentPosition, this.player);
                    break;
            case 4: System.out.println("\u001B[1m\u001B[32mPawn promoted to rook.\u001B[0m");
                    board[currentIndices[0]][currentIndices[1]] = new Rook((this.player == 1) ? '\u265C' : '\u2656', currentPosition, this.player);
                    break;
            case 5: System.out.println("\u001B[1m\u001B[32mPawn promoted to queen.\u001B[0m");
                    board[currentIndices[0]][currentIndices[1]] = new Queen((this.player == 1) ? '\u265B' : '\u2655', currentPosition, this.player);
        }
        
        //Update list of player's pieces in play 
        ChessPiece promotedPiece = board[currentIndices[0]][currentIndices[1]];
        player.getPiecesInPlay().remove(Main.findPieceByPosition(currentPosition, player.getPiecesInPlay()));
        int insertionType = promotedPiece.getType();
        while (Main.findPieceIndexByType(insertionType, player.getPiecesInPlay()) == -1) {
            insertionType++;
        }
        player.getPiecesInPlay().add(Main.findPieceIndexByType(insertionType, player.getPiecesInPlay()), promotedPiece);
    }
}