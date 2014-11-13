int frame_size = 30;
button players[][];
int SIZE_FIELD = 50;
int WIDTH = 460;
int HEIGHT = 460;
/* number of players on each side */
int NUM_PLAYER = 8;

/* if the button was pressed, it should be redrawn every time */
boolean a_button_pressed;
int pressed_i;
int pressed_j;
int PLAYER_ONE = 1;
int PLAYER_TWO = -1;

/** represents a single button */
class button {
  float x;
  float y;
  int player;
  boolean pressed;
  float old_x;
  float old_y;
  button (float _x, float _y, int _player, boolean _pressed) {
      x = _x;
      y = _y;
      old_x = x;
      old_y = y;
      player = _player;
      pressed = _pressed;
  }
  
  float getX(){
    return x;
  }
  float getY(){
    return y;
  }
  int getPlayer(){
    return player;
  }
  boolean getStatus() {
    return pressed;
  }
  
  void setPressed(boolean _pressed) {
    pressed = _pressed;
  }
  
  void setXY(int _x, int _y) {
    x = _x;
    y = _y;
  }
  
  void setBack() {
    x = old_x;
    y = old_y;
  }
  
  void setPlayer(int _p) {
     player = _p; 
  }
  
}

/** constructor */
void setup() {
  size(WIDTH, HEIGHT);
  background(0);
  
  a_button_pressed = false;
  players = new button[8][8];
  int numPlayers = 1;
  // fills the board on each side with players
  // stops if numPlayers is as big as NUM_PLAYER
  float r = (SIZE_FIELD/2);
  float x_pos = frame_size + 25;
  float y_pos = frame_size;         

  for (int i = 0; i < 8; i++) {
    y_pos = frame_size + 25;
    for (int j = 0; j < 8; j++) {
      players[i][j] = new button(x_pos,y_pos,0,false);
      y_pos+=SIZE_FIELD; 
      }
    x_pos+=SIZE_FIELD;
    }
      
  for (int y = 0; y < 3; y++) {
    for (int x = 0; x < 8; x++) {
     println(x + " " + y); 
     if (numPlayers > NUM_PLAYER)
        break;
     if ((x % 2 == 0 && 
          y % 2 == 0) || 
         (x % 2 == 1 && 
          y % 2 == 1)) {
        players[x][y].setPlayer(PLAYER_ONE);
        numPlayers++;
      }
    }
  } 
  
  numPlayers = 1;
  for (int y = 7; y > 4; y--) {
    for (int x = 0; x < 8; x++) {
      if (numPlayers > NUM_PLAYER)
        break;
      if ((x % 2 == 0 && 
           y % 2 == 0) || 
          (x % 2 == 1 && 
           y % 2 == 1)) {
        players[x][y].setPlayer(PLAYER_TWO);
        numPlayers++;
      } 
    }
  }
}

/** draws everything */
void draw() {
  
  draw_board_and_frame();
  draw_players();
  
  move();
} 

/** allows the movement of the buttons */
void move() {
  // update if a button was pressed    
   if (a_button_pressed) {
    // if mouse button stays pressed, update the position of the button
    if (mousePressed) {
      players[pressed_i][pressed_j].setXY(mouseX, mouseY);
      a_button_pressed = true;
    } 
     // else if the movement is valid, it should be set to this postion
     // if not vaid, move it back to the old position  
    else {
      players[pressed_i][pressed_j].setBack();   
      a_button_pressed = false;
      pressed_i = 0;
      pressed_j = 0;
    } 
  }
  else if (mousePressed) {
    float r = (SIZE_FIELD/2);
    println("pressed it " + mouseX + " " + mouseY); 
    // so now, we need to find the corresponding button
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (players[i][j].getPlayer() != 0) {
          if (overCircle(players[i][j].getX(), players[i][j].getY(), r)){
            players[i][j].setXY(mouseX, mouseY);
            players[i][j].setPlayer(0);
            players[i][j].setPressed(true);
            pressed_i = i;
            pressed_j = j;
            a_button_pressed = true;
            break;
            }
          }
        }
      }
    }
}
  


/** true if its over the circle */
boolean overCircle(float x, float y, float diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}

/** draws the players - they are currently in the int matrix 
 *  there is no check, if there are enough payers on the field
 */
void draw_players() {
  float r = (SIZE_FIELD/2);
  float x = frame_size + 25;
  float y = frame_size;         
  
  // print the checkers on the checker board
  for (int i = 0; i < 8 ; i++) {
    y = 5;
    
    // Begin loop for rows
    for (int j = 0; j < 8 ; j++) {
        stroke(255);
        y+=SIZE_FIELD;
        //print((players[i][j]).getPlayer() + " ");
        
        if ( (players[i][j]).getPlayer() != 0 || (players[i][j]).getStatus()) {
          if ((players[i][j]).getPlayer() == PLAYER_ONE)   
            fill(122, 0, 0);
          else if ((players[i][j]).getPlayer() == PLAYER_TWO) 
            fill(0, 122, 0);
            else if ((players[i][j]).getStatus())
              fill(0,122,122);
          ellipse((players[i][j]).getX(), (players[i][j]).getY(), r, r);
        }
      }
      //println();
      x+=SIZE_FIELD;
    } //println();
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

