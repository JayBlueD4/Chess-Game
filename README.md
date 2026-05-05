# Chess-Game

A console-based chess game making use of hex codes and text formatting to create a chess board composed entirely of text. If chesspiece symbols do not display on a desktop application, the program is also available on OnlineGDB (https://onlinegdb.com/6xFejIWEw).

Allows for two players to play against each other. A player can choose to surrender if under check with no valid moves to make (i.e. checkmate). The final piece statistics for both players are displayed upon surrender.

Special moves like pawn promotion and en passant are enabled.

This program made heavy use of abstraction, ensuring each task and its respective subtasks were encapsulated into methods for easier use and readability. Classes and subclasses were also used to achieve abstraction and avoid repetitive code. There is a focus on the use of 2D arrays as well, with one storing the data for the chessboard.

Exception handling was especially important to prevent program termination in the face of invalid input.
