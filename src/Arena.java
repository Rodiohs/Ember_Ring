import ui.GetPos;
import ui.RoundedButton;
import ui.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arena {
    public Arena(graphicInterface background, GetPos pos, String selected){
        switch (selected){
            case "Fire":
                background.setFilter(255, 30, 0, 150);
                break;
            case "Magic":
                background.setFilter(0, 139, 139, 150);
                break;
            case "Earth":
                background.setFilter(123, 75, 58, 200);
                break;
            case "Water":
                background.setFilter(0, 0, 170, 150);
                break;
            case "Air":
                background.setFilter(160, 168, 181, 150);
                break;
        }
        RoundedPanel panel = new RoundedPanel(30, 0, Color.decode("#101010"));
        panel.setBackground(new Color(30,30, 30, 200));
        int margin = (pos.Y(100)-pos.X(50))/2;
        panel.setBounds(pos.X(25), margin, pos.X(50), pos.X(50));
        background.add(panel);
    }
}
