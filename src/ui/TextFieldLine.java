package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextFieldLine extends JTextField {
    private String preRule;

    public TextFieldLine(Font playerFont, GetPos pos, JPanel container, int x, int y, int width, int height, String preRule) {
        super(preRule); // Usar `super` para establecer el texto predeterminado
        this.preRule = preRule;
        this.setFont(playerFont);
        this.setOpaque(false);
        this.setForeground(Color.decode("#AAAAAA"));
        this.setBorder(null);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBounds(pos.X(x), pos.Y(y), pos.X(width), pos.Y(height));
        container.add(this); // Añadir esta instancia al contenedor
        addFocusListeners(); // Configurar listeners de enfoque

        // Añadir la línea decorativa debajo del campo de texto
        JPanel nameCharLine = new JPanel();
        nameCharLine.setBackground(Color.white);
        nameCharLine.setBounds(pos.X(x), pos.Y(y + height), pos.X(width), 5);
        container.add(nameCharLine);
    }

    private void addFocusListeners() {
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (TextFieldLine.this.getText().equals(preRule)) {
                    TextFieldLine.this.setText("");
                    TextFieldLine.this.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (TextFieldLine.this.getText().isEmpty()) {
                    TextFieldLine.this.setForeground(Color.decode("#AAAAAA"));
                    TextFieldLine.this.setText(preRule); // Restaura el placeholder si está vacío
                }
            }
        });
    }
}
