/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 500;

/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 6;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
			(WIDTH - (NBRICKS_PER_ROW-1)*BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 7;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 40;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Initial ball speed */
	private static final int BALL_SPEED = 5;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		BrickCount = NBRICK_ROWS*NBRICKS_PER_ROW;
		TurnsLeft = NTURNS;
		Score = 0;
	// Initialize turns remaining counter
		TurnsLeftLabel = new GLabel("Turns Left: " + TurnsLeft, 5, 20);
		add(TurnsLeftLabel);
	// Initialize score keeper
		myScore = new GLabel("Score: " + Score, WIDTH-90, 20);
		add(myScore);
		
	// Initialize bricks
		brickInit();
	// Initialize paddle
		Paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleInit();

	// Initialize ball and ball starting location
		initX = WIDTH/2-BALL_RADIUS;
		initY = HEIGHT/2-BALL_RADIUS;
		Ball = new GOval(initX, initY);
		
	// Only runs game when more than 0 turns and more than 0 bricks are left
		while (TurnsLeft > 0 && BrickCount > 0) {
			ballInit();
			waitForClick();
			ballBounce();
		}
	// If there are no more turns left
		if (TurnsLeft == 0) {
			GLabel gameOver = new GLabel("GAME OVER");
			add(gameOver, (WIDTH-gameOver.getWidth())/2, (HEIGHT-gameOver.getHeight())/2);
		}
	
	// If all bricks have been removed
		if (BrickCount == 0) {
			victory();
		}

	}

	public void brickInit() {
		int y = BRICK_Y_OFFSET; 
		for (int row = 0; row < NBRICK_ROWS; row++) {

			for (int col = 0; col < NBRICKS_PER_ROW; col++) {
				
				myRect = new GRect(BRICK_SEP/2+(BRICK_WIDTH+BRICK_SEP)*col, y,
						BRICK_WIDTH, BRICK_HEIGHT);
				
				// Sets brick fill color to vary by row.
				if (row % NBRICK_ROWS == 0) {
					myRect.setFillColor(Color.RED);
				} else if (row % NBRICK_ROWS == 1) {
					myRect.setFillColor(Color.RED	);
				} else if (row % NBRICK_ROWS == 2) {
					myRect.setFillColor(Color.ORANGE);
				} else if (row % NBRICK_ROWS == 3) {
					myRect.setFillColor(Color.ORANGE);
				} else if (row % NBRICK_ROWS == 4) {
					myRect.setFillColor(Color.YELLOW);
				} else if (row % NBRICK_ROWS == 5) {
					myRect.setFillColor(Color.YELLOW);
				} else if (row % NBRICK_ROWS == 6) {
					myRect.setFillColor(Color.GREEN);
				} else if (row % NBRICK_ROWS == 7) {
					myRect.setFillColor(Color.GREEN);
				} else if (row % NBRICK_ROWS == 8) {
					myRect.setFillColor(Color.CYAN);
				} else if (row % NBRICK_ROWS == 9) {
					myRect.setFillColor(Color.CYAN);
				} else if (row % NBRICK_ROWS == 10) {
					myRect.setFillColor(Color.BLUE);
				} else if (row % NBRICK_ROWS == 11) {
					myRect.setFillColor(Color.BLUE);
				} else if (row % NBRICK_ROWS == 12) {
					myRect.setFillColor(Color.GRAY);
				} else if (row % NBRICK_ROWS == 13) {
					myRect.setFillColor(Color.GRAY);
				}
				
				// Sets brick border color to vary by row.
				if (row % NBRICK_ROWS == 0) {
					myRect.setColor(Color.RED);
				} else if (row % NBRICK_ROWS == 1) {
					myRect.setColor(Color.RED	);
				} else if (row % NBRICK_ROWS == 2) {
					myRect.setColor(Color.ORANGE);
				} else if (row % NBRICK_ROWS == 3) {
					myRect.setColor(Color.ORANGE);
				} else if (row % NBRICK_ROWS == 4) {
					myRect.setColor(Color.YELLOW);
				} else if (row % NBRICK_ROWS == 5) {
					myRect.setColor(Color.YELLOW);
				} else if (row % NBRICK_ROWS == 6) {
					myRect.setColor(Color.GREEN);
				} else if (row % NBRICK_ROWS == 7) {
					myRect.setColor(Color.GREEN);
				} else if (row % NBRICK_ROWS == 8) {
					myRect.setColor(Color.CYAN);
				} else if (row % NBRICK_ROWS == 9) {
					myRect.setColor(Color.CYAN);
				} else if (row % NBRICK_ROWS == 10) {
					myRect.setColor(Color.BLUE);
				} else if (row % NBRICK_ROWS == 11) {
					myRect.setColor(Color.BLUE);
				} else if (row % NBRICK_ROWS == 12) {
					myRect.setColor(Color.GRAY);
				} else if (row % NBRICK_ROWS == 13) {
					myRect.setColor(Color.GRAY);
				}
				myRect.setFilled(true);
				add(myRect);
			}
			y = y + BRICK_HEIGHT + BRICK_SEP;
		}
	}
	
	public void paddleInit() {
		int i = (WIDTH-PADDLE_WIDTH)/2;
		int j = HEIGHT-PADDLE_HEIGHT-PADDLE_Y_OFFSET;
		Paddle.setLocation(i, j);
		Paddle.setFillColor(Color.BLACK);
		Paddle.setColor(Color.BLACK);
		Paddle.setFilled(true);
		add(Paddle);
	}
	
	public void ballInit() {
		Ball.setSize(BALL_RADIUS*2, BALL_RADIUS*2);
		Ball.setLocation(initX, initY);
		Ball.setFillColor(Color.LIGHT_GRAY);
		Ball.setColor(Color.DARK_GRAY);
		Ball.setFilled(true);
		add(Ball);
	}
	
	public void ballBounce() {
	
	// Randomizes initial x-velocity
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 1.0;
		paddleBounces = 0;
	
	// Runs while ball is above height of app window 
		while (Ball.getY() < HEIGHT) {
		// Ends if all bricks have been removed
			if (BrickCount == 0){
				break;
			}
			
		// Speeds up ball after continuous play
			if (paddleBounces == 5 || paddleBounces == 11 || paddleBounces == 17) {
				vx = 1.25*vx;
				vy = 1.25*vy;
				paddleBounces++;
			}
			
		// Moves ball
			Ball.move(vx, vy);
			pause(BALL_SPEED);
			
		// Bounces ball off application window
			if (Ball.getX()+2*BALL_RADIUS >= WIDTH || Ball.getX() <= 0) {
				vx = -vx;
			} else if(Ball.getY() <= 0) {
				vy = -vy;
			}
		// Checks for collision
			colliders();
		}	
		
		
	// If ball falls down past paddle
		if (Ball.getY() >= HEIGHT) {
			remove(TurnsLeftLabel);
			TurnsLeft--;
			TurnsLeftLabel.setLabel("Turns Left: " + TurnsLeft);
			add(TurnsLeftLabel);
		}
		
	}	
	
	private GObject getColidingObject(double x, double y) {
		if (getElementAt(x, y) != null){
			AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
			GObject collider = getElementAt(x, y);
			if (collider == myScore || collider == TurnsLeftLabel) {
				/* Don't do anything if colliding with one of the 2 GLabels */;
			} else if (collider == Paddle){
				bounceClip.play();
				paddleBounces++;
				if (y <= Paddle.getY()+PADDLE_HEIGHT && x >= Paddle.getX() && x <= Paddle.getX()+PADDLE_WIDTH) {
					vy = -vy;
				} else if (y > Paddle.getY()+PADDLE_HEIGHT && x >= Paddle.getX() && x <= Paddle.getX()+PADDLE_WIDTH){
					vx = -vx;
				}
			} else {
				bounceClip.play();
				vy = -vy;
				remove(collider);
				remove(myScore);
				
				if (collider.getColor() == Color.CYAN){
					Score+=10;
				} else if (collider.getColor() == Color.GREEN){
					Score+=25;
				} else if (collider.getColor() == Color.YELLOW){
					Score+=50;
				} else if (collider.getColor() == Color.ORANGE){
					Score+=75;
				} else if (collider.getColor() == Color.RED){
					Score+=100;
				}				
					BrickCount--;
				myScore = new GLabel("Score: " + Score, WIDTH-90, 20);
				add(myScore);
				if (BrickCount == 0) {
					GLabel win = new GLabel("YOU WIN!");
					add(win, (WIDTH-win.getWidth())/2, (HEIGHT-win.getHeight())/2);
				}
			}
			return collider;
		}  else {
			return null;
		}
	}
	
	private GObject colliders(){
		
		// Coordinates of interest
		double x1, y1, x2, y2, x3, y3, x4, y4;
		x1 = Ball.getX()+BALL_RADIUS;
		y1 = Ball.getY()-2;
		x2 = Ball.getX()+BALL_RADIUS;
		y2 = Ball.getY()+2*BALL_RADIUS+2;
		x3 = Ball.getX()+2*BALL_RADIUS+2;
		y3 = Ball.getY()+BALL_RADIUS;
		x4 = Ball.getX()-2;
		y4 = Ball.getY()+BALL_RADIUS;
		
		getColidingObject(x1, y1);
		getColidingObject(x2, y2);
		getColidingObject(x3, y3);
		getColidingObject(x4, y4);
		
		return null;
	}
	
	private void victory() {
		GLabel win = new GLabel("YOU WIN!");
		add(win, (WIDTH-win.getWidth())/2, (HEIGHT-win.getHeight())/2);	
	}
	
	public void mouseMoved(MouseEvent e){
		if (e.getX() > PADDLE_WIDTH/2 && e.getX() < WIDTH-PADDLE_WIDTH/2){
			Paddle.setLocation(e.getX()-PADDLE_WIDTH/2, HEIGHT-PADDLE_HEIGHT-PADDLE_Y_OFFSET);
		}
	}
	
	private GRect Paddle;
	private GOval Ball;
	private GLabel TurnsLeftLabel;
	private int TurnsLeft;
	private GRect myRect;
	private GLabel myScore;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private double initX, initY;
	private int BrickCount;
	private int paddleBounces;
	private int Score;
}
