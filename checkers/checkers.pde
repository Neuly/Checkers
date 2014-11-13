int frame_size = 30;
int players[][];
int SIZE_FIELD = 50;
int WIDTH = 460;
int HEIGHT = 460;
/* number of players on each side */
int NUM_PLAYER = 8;

/** constructor */
void setup() {
  size(WIDTH, HEIGHT);
  background(0);
  players = new int[8][8];
  int numPlayers = 1;
  // fills the board on each side with players
  // stops if numPlayers is as big as NUM_PLAYER
    
  for (int y = 0; y < 3; y++) {
    for (int x = 0; x < 3; x++) {
     if (x % 2 == 0 && 
        y % 2 == 0) {
        players[x][y] = 1;
        numPlayers++;
        print("first ");
      } else {
        if (x % 2 == 1 && 
          y % 2 == 1) {
          print("second ");
          players[x][y] = 1;
          numPlayers++;
        }
        else 
          players[x][y] = 0;
      }
      println(x + " " + y);
      if (numPlayers >= NUM_PLAYER)
        break;
    }
  } 
  /*
  numPlayers = 1;
  for (int y = 7; y > 4; y--) {
    for (int x = 7; x > 0; x--) {
      if (x % 2 == 1 && 
        y % 2 == 1) {
        players[x][y] = -1;
        numPlayers++;
      } else {
        if (x % 2 == 0 && 
          y % 2 == 0) {
          players[x][y] = -1;
          numPlayers++;
        }
        else 
          players[x][y] = 0;
      }
      
      if (numPlayers >= NUM_PLAYER)
        break;
    }
  }*/
}

/** draws everything */
void draw() {
  draw_board_and_frame();
  draw_players();
  
} 

/** draws the players - they are currently in the int matrix 
 *  there is no check, if there are enough payers on the field
 */
void draw_players() {
  float r = (SIZE_FIELD/2);
  float x = frame_size;
  float y = frame_size;
  println();
  // print the checkers on the checker board
  for (int i = 0; i < 8 ; y+=SIZE_FIELD, i++) {
    // Begin loop for rows
    for (int j = 0; j < 8 ; x+=SIZE_FIELD, j++) {
        stroke(255);
//        print(players[j][i] + " ");
        if ( players[i][j] != 0 ) {
          
          if (players[i][j] == 1)   
            fill(122, 0, 0);
          else if (players[i][j] == -1) 
            fill(0, 122, 0);
          
          ellipse(x+r, y+r, r, r);
        
        }
      }
  //    println();
    }
  }


/** draws the frame of the chessboard and the board itself - without the players */
void draw_board_and_frame() {
  int _width = WIDTH-frame_size;
  int _height = HEIGHT-frame_size;

  // print the checkers board
  // Begin loop for columns
  for (int x = frame_size; x < _width; x+=SIZE_FIELD) {
    // Begin loop for rows
    for (int y = frame_size; y < _height; y+=SIZE_FIELD) {
      stroke(255);
      if ((x + y) % 20 == 0) {  
        fill(0);
      } else {  
        fill(255);
      } 
      // For every column and row, a rectangle is drawn at an (x,y) location scaled and sized by videoScale.
      rect(x, y, SIZE_FIELD, SIZE_FIELD);
    }
  }
}

