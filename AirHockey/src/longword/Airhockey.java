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
