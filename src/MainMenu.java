import ui.GetPos;
import ui.RoundedButton;
import ui.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public MainMenu(graphicInterface background){
        background.setFilter(0, 0, 0, 0);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        GetPos pos = new GetPos(screenWidth, screenHeight);
        background.setLayout(null);

        Image Logo = new ImageIcon("src/resources/ember.png").getImage();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen de fondo
                g.drawImage(Logo, 0, 0, pos.X(40), pos.X(40), this);
            }
        };
        panel.setOpaque(false);
        panel.setBounds(pos.X(30), pos.Y(10), pos.X(40), pos.X(40));
        background.add(panel);

        RoundedPanel frameForButtons = new RoundedPanel(30, 2, Color.decode("#181818"));
        frameForButtons.setBounds(pos.X(10), pos.Y(75), pos.X(80), pos.Y(20));
        frameForButtons.setBackground(Color.decode("#181818"));
        frameForButtons.setLayout(null);
        GetPos forButtons = new GetPos(frameForButtons.getWidth(), frameForButtons.getHeight());
        background.add(frameForButtons);

        RoundedButton btnStats = new RoundedButton("STATISTICS", 30, 50f, Color.decode("#A00000"));
        btnStats.setBounds(forButtons.X(2), forButtons.Y(10), forButtons.X(30), forButtons.Y(80));
        frameForButtons.add(btnStats);
        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new Stats(background, pos);
            }
        });

        RoundedButton btnPlay = new RoundedButton("PLAY", 30, 50f, Color.decode("#A00000"));
        btnPlay.setBounds(forButtons.X(35), forButtons.Y(10), forButtons.X(30), forButtons.Y(80));
        frameForButtons.add(btnPlay);
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new Presets(background, pos);
            }
        });

        RoundedButton btnQuit = new RoundedButton("QUIT", 30, 50f, Color.decode("#A00000"));
        btnQuit.setBounds(forButtons.X(68), forButtons.Y(10), forButtons.X(30), forButtons.Y(80));
        frameForButtons.add(btnQuit);
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminar la aplicación
            }
        });
    }
}
