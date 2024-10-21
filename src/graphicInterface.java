import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphicInterface extends JPanel implements ActionListener {
    private Timer timer;
    private int num = 0;
    private int turn = 1;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int opacity = 0;
    private int width;
    private int height;

    public graphicInterface() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color filter = new Color(red, green, blue, opacity);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        String number = String.format("%03d", num); // Formato de 3 dígitos
        Image img = new ImageIcon("src/video/balatring_" + number + ".jpg").getImage();
        g2d.drawImage(img, 0, 0, width, height, this);
        g2d.setColor(filter);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        num = num + turn;
        if (num == 149) {
            turn = -1;
        } else if (num == 0) {
            turn = 1;
        }
        repaint();
    }

    public void setFilter(int red, int green, int blue, int opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public static graphicInterface createAndShowGUI() {
        graphicInterface movingBackground = new graphicInterface();
        movingBackground.setFocusable(true);
        movingBackground.requestFocusInWindow();
        return movingBackground;
    }
}
