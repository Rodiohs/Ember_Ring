import ui.GameFont;
import ui.GetPos;
import ui.RoundedButton;

import Character.Character;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

public class Presets {
    private static List<Character> player1Characters = new ArrayList<Character>();
    private static List<Character> player2Characters = new ArrayList<Character>();

    public Presets(graphicInterface background, GetPos pos) {
        background.setFilter(255, 0, 0, 100);
        Font font = new GameFont(90f).getFont();
        Font fieldFont = new GameFont(35f).getFont();
        JLabel title = new JLabel("GAME PRESETS");
        title.setForeground(Color.white);
        title.setFont(font);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(pos.X(30), pos.Y(10), pos.X(40), pos.Y(10));
        background.add(title);

        JTextField maxPlayers = new JTextField("Max characters per player");
        maxPlayers.setFont(fieldFont);
        maxPlayers.setOpaque(false);
        maxPlayers.setForeground(Color.decode("#AAAAAA"));
        maxPlayers.setBorder(null);
        maxPlayers.setHorizontalAlignment(SwingConstants.CENTER);
        maxPlayers.setBounds(pos.X(35), pos.Y(30), pos.X(30), pos.Y(5));
        background.add(maxPlayers);
        maxPlayers.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (maxPlayers.getText().equals("Max characters per player")) {
                    maxPlayers.setText("");
                    maxPlayers.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (maxPlayers.getText().isEmpty()) {
                    maxPlayers.setForeground(Color.decode("#AAAAAA"));
                    maxPlayers.setText("Max characters per player"); // Restaura el placeholder si está vacío
                }
            }
        });

        JPanel lineMaxPlayers = new JPanel();
        lineMaxPlayers.setBackground(Color.white);
        lineMaxPlayers.setBounds(pos.X(35), pos.Y(35), pos.X(30), 5);
        background.add(lineMaxPlayers);

        JTextField player1 = new JTextField("Player 1's name");
        player1.setFont(fieldFont);
        player1.setOpaque(false);
        player1.setForeground(Color.decode("#AAAAAA"));
        player1.setBorder(null);
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player1.setBounds(pos.X(35), pos.Y(45), pos.X(30), pos.Y(5));
        background.add(player1);
        player1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (player1.getText().equals("Player 1's name")) {
                    player1.setText("");
                    player1.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (player1.getText().isEmpty()) {
                    player1.setForeground(Color.decode("#AAAAAA"));
                    player1.setText("Player 1's name"); // Restaura el placeholder si está vacío
                }
            }
        });

        JPanel linePlayer1 = new JPanel();
        linePlayer1.setBackground(Color.white);
        linePlayer1.setBounds(pos.X(35), pos.Y(50), pos.X(30), 5);
        background.add(linePlayer1);

        JTextField player2 = new JTextField("Player 2's name");
        player2.setFont(fieldFont);
        player2.setOpaque(false);
        player2.setForeground(Color.decode("#AAAAAA"));
        player2.setBorder(null);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setBounds(pos.X(35), pos.Y(60), pos.X(30), pos.Y(5));
        background.add(player2);
        player2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (player2.getText().equals("Player 2's name")) {
                    player2.setText("");
                    player2.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (player2.getText().isEmpty()) {
                    player2.setForeground(Color.decode("#AAAAAA"));
                    player2.setText("Player 2's name"); // Restaura el placeholder si está vacío
                }
            }
        });

        JPanel linePlayer2 = new JPanel();
        linePlayer2.setBackground(Color.white);
        linePlayer2.setBounds(pos.X(35), pos.Y(65), pos.X(30), 5);
        background.add(linePlayer2);


        RoundedButton back = new RoundedButton("BACK", 30, 50f, Color.decode("#181818"));
        back.setBounds(pos.X(29), pos.Y(80), pos.X(20), pos.Y(10));
        background.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new MainMenu(background);
            }
        });

        RoundedButton next = new RoundedButton("NEXT", 30, 50f, Color.decode("#181818"));
        next.setBounds(pos.X(51), pos.Y(80), pos.X(20), pos.Y(10));
        background.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxPlayersValue = Integer.parseInt(maxPlayers.getText());
                    if (maxPlayersValue < 3) {
                        JOptionPane.showMessageDialog(null, "The number of characters must be at least 3.", "Config Error", JOptionPane.ERROR_MESSAGE);
                    } else if (player1.getText().equals("Player 1's name") || player2.getText().equals("Player 2's name")) {
                        JOptionPane.showMessageDialog(null, "You must enter a name for the players.", "Name Error", JOptionPane.WARNING_MESSAGE);
                    } else if (player1.getText().equals(player2.getText())) {
                        JOptionPane.showMessageDialog(null, "Player names can't be the same.", "Name Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        background.removeAll();  // Eliminar todos los componentes del panel
                        background.revalidate(); // Actualizar el diseño del panel
                        background.repaint();

                        Player Player1 = new Player(0, player1.getText(), player1Characters);
                        Player Player2 = new Player(1, player2.getText(), player2Characters);
                        new CharSelection(background, pos, null, maxPlayersValue, Player1, Player2);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the max characters.", "Format Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
