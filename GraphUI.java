import java.awt.*;
import javax.swing.*;

public class GraphUI extends JPanel {

    private void drawNode(Graphics2D g, int x, int y, String name) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 60, 60);

        g.setColor(Color.BLACK);
        g.drawOval(x, y, 60, 60);
        g.drawString(name, x + 5, y + 35);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // ===== Connections (Edges) =====

        // Hanamanth - Rahul
        g2.drawLine(130, 130, 330, 130);

        // Hanamanth - Priya
        g2.drawLine(130, 130, 130, 280);

        // Rahul - Kiran
        g2.drawLine(330, 130, 330, 280);

        // Priya - Kiran
        g2.drawLine(130, 280, 330, 280);

        // Kiran - Ram
        g2.drawLine(330, 280, 530, 280);

        // ===== Users (Vertices) =====

        drawNode(g2, 100, 100, "Hanamanth");
        drawNode(g2, 300, 100, "Rahul");
        drawNode(g2, 100, 250, "Priya");
        drawNode(g2, 300, 250, "Kiran");
        drawNode(g2, 500, 250, "Ram");
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Social Network Analysis Graph");

        GraphUI panel = new GraphUI();

        frame.add(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}