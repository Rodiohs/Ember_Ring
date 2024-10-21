package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextFieldLine extends JTextField {
    public TextFieldLine(Font playerFont, GetPos pos, JPanel container, int x, int y, int width, int height, String preRule){
        JTextField textField = new JTextField(preRule);
        textField.setFont(playerFont);
        textField.setOpaque(false);
        textField.setForeground(Color.decode("#AAAAAA"));
        textField.setBorder(null);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBounds(pos.X(x), pos.Y(y), pos.X(width), pos.Y(height));
        container.add(textField);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(preRule)) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.decode("#AAAAAA"));
                    textField.setText(preRule); // Restaura el placeholder si está vacío
                }
            }
        });
        JPanel nameCharLine = new JPanel();
        nameCharLine.setBackground(Color.white);
        nameCharLine.setBounds(pos.X(x), pos.Y(y+height), pos.X(width), 5);
        container.add(nameCharLine);
    }
}
