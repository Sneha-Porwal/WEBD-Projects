import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class AnimatedNameApplet extends JPanel {

    private int x = 0; // Starting position of the text
    private String name = "Sneha Porwal"; // Replace with your name
    private int fontSize = 30; // Initial font size
    private int direction = 1; // Direction of font size change: 1 for increasing, -1 for decreasing

    // Constructor to set up a timer for animation
    public AnimatedNameApplet() {
        // Set up a timer to update the position every 20 milliseconds
        Timer timer = new Timer(20, e -> {
            // Move the name horizontally
            x += 6;

            // Reset position if the name moves off-screen
            if (x > getWidth()) {
                x = -getFontMetrics(getFont()).stringWidth(name); // Reset to the left side of the panel
            }

            // Adjust font size to simulate Z-axis movement (approaching and moving away)
            if (fontSize >= 60) {
                direction = -1; // Start shrinking the text
            } else if (fontSize <= 30) {
                direction = 1; // Start enlarging the text
            }

            fontSize += direction; // Change the font size

            // Repaint the panel to create the animation effect
            repaint();
        });
        timer.start(); // Start the timer
    }

    // Paint method for drawing the name
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the font with the dynamic font size
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.setColor(Color.blue);

        // Draw the name at the current x position (moving left to right)
        g.drawString(name, x, 100); // Fixed y position
    }

    // Create the window frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Z-Axis Animated Name");
        AnimatedNameApplet panel = new AnimatedNameApplet();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Window size
        frame.add(panel);
        frame.setVisible(true);
    }
}
