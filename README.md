This application represent the backend of TicTacToe game which follow these rules:

-player with X symbole always goes first.

-Players cannot play on a played position.

-Players alternate placing X’s and O’s on the board until either:

-One player has three in a row, horizontally, vertically or diagonally

-All nine squares are filled.

-If a player is able to draw three X’s or three O’s in a row, that player wins.

-If all nine squares are filled and neither player has three in a row, the game is a draw.

This application is been created with springboot framework:

Java version 11

Maven version 3.8.1

The goal of this app, is to allow 2 players to play tictactoe game till one of them win or the game is a draw.



This api contains 3 end points:


localhost:8083/api/player/new ---> to create new player

localhost:8083/api/game/new ---> to create new game

localhost:8083/api/player/play ---> to play



To build the project:

go to the main folder of the project : "TicTacToe-main" in cmd : mvn clean install

to run the application execute :

replace pathofthefolder by yours

java -jar pathofthefolder\TicTacToe-main\target\tictactoe-0.0.1-SNAPSHOT.jar com.game.tictactoe

Rest end point:

//creation of players

curl -H "Content-Type: application/json" -X POST -d "{\"userName\":\"p1\",\"symbol\":\"\"}" http://localhost:8083/api/player/new

curl -H "Content-Type: application/json" -X POST -d "{\"userName\":\"p2\",\"symbol\":\"\"}" http://localhost:8083/api/player/new

//creation of game

curl -H "Content-Type: application/json" -X POST -d "{\"player1\":1,\"player2\":2,\"board\":null,\"turn\":null,\"gameOver\":false,\"chancesLeft\":null}" http://localhost:8083/api/game/new

//senario to run

curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/1 -d "{\"gameId\":3,\"i\":0,\"j\":0}"

curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/2 -d "{\"gameId\":3,\"i\":0,\"j\":1}"


curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/1 -d "{\"gameId\":3,\"i\":1,\"j\":0}"

curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/2 -d "{\"gameId\":3,\"i\":0,\"j\":2}"

curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/1 -d "{\"gameId\":3,\"i\":2,\"j\":0}"

curl -H "Content-Type: application/json" -X PUT http://localhost:8083/api/player/play/2 -d "{\"gameId\":3,\"i\":1,\"j\":2}"


run tests :

go the main folder of the project : newAccountBank , open cmd, write : mvn test
