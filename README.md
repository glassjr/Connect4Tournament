#Connect 4 Tournament API
__________________________________________
###Created by: Joseph Rock and Jacob Glass

###In order to make a playable AI, you must extend the Player class

There are two main packages in the source code:

######ConnectKSource
######DeveloperTools

In ConnectKSource we find the main modules that comprise the actual Connect 4 game components.

+    Chip : An enumerated state object (possible values are RED, BLACK, and EMPTY).
+    Cell : A wrapper class for the Chip. Provides pretty printing.
+    Location : A basic struct containing a final x and y integer component.
+    Board : A mutable class that handles moves and encapsulates a 2D array of Cell objects.
+    Game : A class that handles a single game of Connect 4. Contains all game logic.
+    Player : An abstract base class that provides two methods (nextMove and acceptEndGame).

In DeveloperTools we find some basic classes to help develop a Player subclass.

+    HumanPlayer : A Player object that allows a human to play by taking moves from std.in and printing moves to std.out.
+    RandomAI : A Player object that makes random moves safely.
+    ErrorAI : A Player object that always immediately throws an exception (tries dividing by zero).
+    TestRunner: A class that demonstrates how to construct a connect 4 game with two Players.

Note that there are also two classes Tournament and TournamentPlayer in the main src folder.
You can ignore these two classes, as they will not be useful for your Player subclass development.
Feel free to look at the source, however. (Tournament is the messiest class in this entire project.)

==========================================

#####To understand how to create your own Player class, you must read the source code for this project.
However, here's a quick look at TestRunner, so you can just get a feel for how the game works.

If you look at the humanGame method in TestRunner, you'll see some code along the lines of:

	Game game = new Game(new HumanPlayer("Black"),new RandomAI(), 6,7,4,false);
	Player winner = game.playGame();

We construct a game object, and then we return the winning Player object by calling "game.playGame()".

Just to explain the parameters in the Game constructor:
+    The first two arguments are Player objects. The first one will go first in the game.
+    The next three arguments (e.g. "6,7,4") are the number of rows, columns, and k-in-a-row required for a win.
+    As you can see, a Connect4 game has 6 rows, 7 columns, and requires 4 in a row.
+    The last argument is a toggle for debug mode. It is by default always false.
+    Debug mode allows you to print the board after every move and pause until you press enter.
+    This is useful for checking if your AI is behaving properly.

==========================================

#####Final notes on subclassing Player:

You MUST implement the "public int nextMove(Board board, Chip color)" method.
+    nextMove takes Board and Chip objects and returns the column the AI wants to place a Chip in.
+    Columns have zero-based indices. (For Connect4, columns are numbered [0-6].)
+    The AI is free to mutate the passed objects as much as it wants, as they are copies from the Game.

You may (but don't have to) implement the "public void acceptEndGame(Board board, Chip myColor, Chip winningColor)" method.
+    This is provided for AIs that will learn over the course of the Tournament.
+    After every game in a tournament, this method will be called on each AI.
+    It is passed the final board state, the AI's Chip color for that game, and the Chip color of the winner.
+    If the game is a draw, the winning Chip is null.

Quick notes on illegal behavior
+    If your nextMove returns a column not on the board (such as -1), you lose the game.
+    If your nextMove returns a column which is already full, you lose the game.
+    If your nextMove throws an exception, you lose the game.
+    If you implement acceptEndGame and it throws an exception, you are currently not penalized.
