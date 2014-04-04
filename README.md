#Prerequisites

##Maven
The project is built with maven. You must have it installed on your machine.

##Slick-utils

The project uses slick-utils library. Since it doesn't exist in maven central repository, you should download it [here](http://slick.ninjacave.com/slick-util.jar), and run this maven command (in order to install it in your local repository) :

`mvn install:install-file -Dfile=slick-util.jar -DgroupId=slick -DartifactId=utils -Dversion=1.0.0 -Dpackaging=jar`

#Build the game
After checking out the project sources, go to the root of the project then run :

`mvn compile assembly:single`

#Run the game
After the build, go to `target/` folder under the root project then run :

`java -Djava.library.path="natives/" -jar tictactoe.jar`

#How to play
Player 1 has cross (X) symbol, and player 2 has circle(O) symbol. The first player is always Player 1.

Press arrow keys to move the cursor on the desired location. Then press space key to drop your symbol.

For the moment, when the game finished you should restart it to begin another game. 
It's possible when a player wins to press space key to close the game.

#Improvments
The game is not finished yet. But these features may appear later :

- set the number of cases that compose the board.
- set the number of symbols that is required to win a game.
- play against the computer. (dumb AI or not).
- play on network.
