import org.json.JSONArray;
import org.json.JSONObject;
import ui.GameFont;
import ui.GetPos;
import ui.RoundedButton;
import ui.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Stats {
    public Stats(graphicInterface background, GetPos pos){
        background.setFilter(0, 0, 255, 70);
        RoundedPanel titles = new RoundedPanel(30, 0, Color.decode("#181818"));
        titles.setBackground(Color.decode("#81818"));
        titles.setBounds(pos.X(10), pos.Y(10), pos.X(80), pos.Y(10));
        titles.setLayout(null);
        GetPos forTitles = new GetPos(titles.getWidth(), titles.getHeight());
        background.add(titles);

        Font font = new GameFont(30f).getFont();

        JLabel name = new JLabel("Name");
        name.setFont(font);
        name.setBounds(forTitles.X(3), 0, forTitles.X(14), forTitles.Y(100));
        name.setForeground(Color.white);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(name);

        JLabel played = new JLabel("Matches played");
        played.setFont(font);
        played.setBounds(forTitles.X(19), 0, forTitles.X(14), forTitles.Y(100));
        played.setForeground(Color.white);
        played.setHorizontalAlignment(SwingConstants.CENTER);
        played.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(played);

        JLabel won = new JLabel("Matches won");
        won.setFont(font);
        won.setBounds(forTitles.X(35), 0, forTitles.X(14), forTitles.Y(100));
        won.setForeground(Color.white);
        won.setHorizontalAlignment(SwingConstants.CENTER);
        won.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(won);

        JLabel lost = new JLabel("Matches lost");
        lost.setFont(font);
        lost.setBounds(forTitles.X(51), 0, forTitles.X(14), forTitles.Y(100));
        lost.setForeground(Color.white);
        lost.setHorizontalAlignment(SwingConstants.CENTER);
        lost.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(lost);

        JLabel charD = new JLabel("Character deaths");
        charD.setFont(font);
        charD.setBounds(forTitles.X(67), 0, forTitles.X(14), forTitles.Y(100));
        charD.setForeground(Color.white);
        charD.setHorizontalAlignment(SwingConstants.CENTER);
        charD.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(charD);

        JLabel towerD = new JLabel("Tower deaths");
        towerD.setFont(font);
        towerD.setBounds(forTitles.X(83), 0, forTitles.X(14), forTitles.Y(100));
        towerD.setForeground(Color.white);
        towerD.setHorizontalAlignment(SwingConstants.CENTER);
        towerD.setVerticalAlignment(SwingConstants.CENTER);
        titles.add(towerD);

        String filePath = "src/statistics.json"; // Actualiza la ruta según sea necesario
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            String content = contentBuilder.toString();
            JSONObject jsonObject = new JSONObject(content);
            JSONArray usersArray = jsonObject.getJSONArray("users");
            putUsers(usersArray, forTitles, pos, background, font);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error leyendo el archivo: " + ex.getMessage());
        }

        RoundedButton btnStats = new RoundedButton("BACK", 30, 50f, Color.decode("#A00000"));
        btnStats.setBounds(pos.X(10), pos.Y(80), pos.X(80), pos.Y(10));
        background.add(btnStats);
        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new MainMenu(background);
            }
        });
    }

    private void putUsers(JSONArray usersArray, GetPos forTitles, GetPos pos, graphicInterface background, Font font){
        int spaceY = pos.Y(20);
        int spaceX = pos.X(10);
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            JLabel name = new JLabel(user.getString("name"));
            name.setFont(font);
            name.setBounds(spaceX+forTitles.X(3), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            name.setForeground(Color.white);
            name.setHorizontalAlignment(SwingConstants.CENTER);
            name.setVerticalAlignment(SwingConstants.CENTER);
            background.add(name);

            JLabel played = new JLabel(Integer.toString(user.getInt("matchesPlayed")));
            played.setFont(font);
            played.setBounds(spaceX+forTitles.X(19), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            played.setForeground(Color.white);
            played.setHorizontalAlignment(SwingConstants.CENTER);
            played.setVerticalAlignment(SwingConstants.CENTER);
            background.add(played);

            JLabel won = new JLabel(Integer.toString(user.getInt("matchesWon")));
            won.setFont(font);
            won.setBounds(spaceX+forTitles.X(35), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            won.setForeground(Color.white);
            won.setHorizontalAlignment(SwingConstants.CENTER);
            won.setVerticalAlignment(SwingConstants.CENTER);
            background.add(won);

            JLabel lost = new JLabel(Integer.toString(user.getInt("matchesLost")));
            lost.setFont(font);
            lost.setBounds(spaceX+forTitles.X(51), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            lost.setForeground(Color.white);
            lost.setHorizontalAlignment(SwingConstants.CENTER);
            lost.setVerticalAlignment(SwingConstants.CENTER);
            background.add(lost);

            JLabel charD = new JLabel(Integer.toString(user.getInt("totalCharacterDeaths")));
            charD.setFont(font);
            charD.setBounds(spaceX+forTitles.X(67), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            charD.setForeground(Color.white);
            charD.setHorizontalAlignment(SwingConstants.CENTER);
            charD.setVerticalAlignment(SwingConstants.CENTER);
            background.add(charD);

            JLabel towerD = new JLabel(Integer.toString(user.getInt("totalTowerDeaths")));
            towerD.setFont(font);
            towerD.setBounds(spaceX+forTitles.X(83), spaceY+forTitles.Y(100*i), forTitles.X(14), forTitles.Y(100));
            towerD.setForeground(Color.white);
            towerD.setHorizontalAlignment(SwingConstants.CENTER);
            towerD.setVerticalAlignment(SwingConstants.CENTER);
            background.add(towerD);
        }
    }
}
