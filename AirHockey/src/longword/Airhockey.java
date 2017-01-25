package longword;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author baayl
 */
public class Airhockey extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
   
    // making the hockey puck
    Rectangle hockeyPuck = new Rectangle(WIDTH / 2, HEIGHT / 2, 30, 30);
    int moveX = 10;
    int moveY = 10;
    int speed = 30;
    
    // making the players "paddle"
    Rectangle redPaddle = new Rectangle(300, HEIGHT / 2 - 25, 25, 25);
    Rectangle bluePaddle = new Rectangle(500, HEIGHT / 2 - 25, 25, 25);
    Rectangle blueGoal = new Rectangle(0, 200, 12, 200);
    Rectangle redGoal = new Rectangle(787, 200, 12, 200);
    
    int score1 = 0;
    int score2 = 0;
    
    boolean redPaddleUp = false;
    boolean redPaddleDown = false;
    boolean redPaddleLeft = false;
    boolean redPaddleRight = false;
    boolean bluePaddleUp = false;
    boolean bluePaddleDown = false;
    boolean bluePaddleLeft = false;
    boolean bluePaddleRight = false;
    
    
      BufferedImage background = loadImage("field.png");
     
      public BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (Exception e) {
            System.out.println("Error loading " + filename);
        }
        return img;
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        g.setColor(Color.GREEN);
        g.fillRect(redGoal.x, redGoal.y, redGoal.width, redGoal.height);
        g.fillRect(blueGoal.x, blueGoal.y, blueGoal.width, blueGoal.height);
        
        g.setColor(Color.BLACK);
        g.fillOval(hockeyPuck.x, hockeyPuck.y, hockeyPuck.width, hockeyPuck.height);

        g.setColor(Color.red);
        g.fillOval(redPaddle.x, redPaddle.y, redPaddle.width, redPaddle.height);

        g.setColor(Color.blue);
        g.fillOval(bluePaddle.x, bluePaddle.y, bluePaddle.width, bluePaddle.height);
    }

    // GAME DRAWING ENDS HERE
    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            // returns the ball if it goes out of bounds
            if (redPaddle.y < 32) {
                redPaddle.y = 32;
            }
            if (bluePaddle.y < 32) {
                bluePaddle.y = 32;
            }
            if (hockeyPuck.y < 32) {
                hockeyPuck.y = hockeyPuck.y + moveY * speed;
            }
            if (redPaddle.y > 545) {
                redPaddle.y = 545;
            }
            if (bluePaddle.y > 545) {
                bluePaddle.y = 545;
            }
            if (hockeyPuck.y > 545) {
                hockeyPuck.y = hockeyPuck.y - moveY * speed * speed;
            }
            if (redPaddle.x < 35) {
                redPaddle.x = 35;
            }
            if (bluePaddle.x < 35) {
                bluePaddle.x = 35;
            }
            if (hockeyPuck.x < 35) {
                hockeyPuck.x = hockeyPuck.x + moveX * speed;
            }
            if (redPaddle.x > 745) {
                redPaddle.x = 745;
            }
            if (bluePaddle.x > 745) {
                bluePaddle.x = 745;
            }
            if (hockeyPuck.x > 745) {
                hockeyPuck.x = hockeyPuck.x - moveX * speed;
            }
            
            
            // red and blue paddles movement with a speed of 7
            if (redPaddleUp) {
                redPaddle.y = redPaddle.y -  7;
            } else if (redPaddleDown) {
                redPaddle.y = redPaddle.y + 7;
            }
            if (redPaddleLeft) {
                redPaddle.x = redPaddle.x - 7;
            } else if (redPaddleRight) { 
                redPaddle.x = redPaddle.x + 7;
            }
            if (bluePaddleUp) {
                bluePaddle.y = bluePaddle.y - 7;
            } else if (bluePaddleDown) {
                bluePaddle.y = bluePaddle.y + 7;
            }
            if (bluePaddleLeft) {
                bluePaddle.x = bluePaddle.x - 7;
            } else if (bluePaddleRight) {
                bluePaddle.x = bluePaddle.x + 7;
            }
            
            // hockeyPucks movement when it hits the redPaddle
            if (hockeyPuck.intersects(redPaddle)) {

                if (hockeyPuck.y <= redPaddle.y - (redPaddle.height / 2)) {
                    hockeyPuck.y = hockeyPuck.y - moveY * moveY;
                } else if (hockeyPuck.y >= redPaddle.y + (redPaddle.height / 2)) {
                    hockeyPuck.y = hockeyPuck.y + moveY * moveY;
                }
                if (hockeyPuck.x < redPaddle.x) {
                    hockeyPuck.x = hockeyPuck.x - moveX * moveX;
                } else if (hockeyPuck.x > redPaddle.x) {
                    hockeyPuck.x = hockeyPuck.x + moveX * moveX;
                }
            }

            // hockeyPucks movement when it hits the bluePaddle
            if (hockeyPuck.intersects(bluePaddle)) {

                if (hockeyPuck.y <= bluePaddle.y - (bluePaddle.height / 2)) {
                    hockeyPuck.y = hockeyPuck.y - moveY * moveY;
                } else if (hockeyPuck.y >= bluePaddle.y + (bluePaddle.height / 2)) {
                    hockeyPuck.y = hockeyPuck.y + moveY * moveY;
                }
                if (hockeyPuck.x < bluePaddle.x) {
                    hockeyPuck.x = hockeyPuck.x - moveX * moveX;
                } else if (hockeyPuck.x > bluePaddle.x) {
                    hockeyPuck.x = hockeyPuck.x + moveX * moveX;
                }
            }

            // goal "rules" when hockey puck hits or goes insde the goal 
            if (hockeyPuck.intersects(redGoal)) {
                hockeyPuck.y = HEIGHT / 2;
                hockeyPuck.x = WIDTH / 2;
                score1++; <--
            }

            if (hockeyPuck.intersects(blueGoal)) {
                hockeyPuck.y = HEIGHT / 2;
                hockeyPuck.x = WIDTH / 2;
                score2++; <--
            }
            
            // if red or blue player scores 3 the player wins
            if (score1 == 3 || score2 == 3) {
                done = true;
            }

            

            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Airhockey game = new Airhockey();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // make the game listen for keys
        frame.addKeyListener((KeyListener) game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_W) {
            redPaddleUp = true;
        } else if (key == KeyEvent.VK_S) {
            redPaddleDown = true;
        }
        if (key == KeyEvent.VK_A) {
            redPaddleLeft = true;
        } else if (key == KeyEvent.VK_D) {
            redPaddleRight = true;
        }
        if (key == KeyEvent.VK_UP) {
            bluePaddleUp = true;
        } else if (key == KeyEvent.VK_DOWN) {
            bluePaddleDown = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            bluePaddleLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            bluePaddleRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_W) {
            redPaddleUp = false;
        } else if (key == KeyEvent.VK_S) {
            redPaddleDown = false;
        }
        if (key == KeyEvent.VK_A) {
            redPaddleLeft = false;
        } else if (key == KeyEvent.VK_D) {
            redPaddleRight = false;
        }
        if (key == KeyEvent.VK_UP) {
            bluePaddleUp = false;
        } else if (key == KeyEvent.VK_DOWN) {
            bluePaddleDown = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            bluePaddleLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            bluePaddleRight = false;
        }

    }
}
