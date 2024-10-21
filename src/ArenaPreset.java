import ui.GameFont;
import ui.GetPos;
import ui.RoundedButton;
import ui.TransparentComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ArenaPreset {
    public ArenaPreset(graphicInterface background, GetPos pos) {
        background.setFilter(255, 127, 39, 100);
        Font font = new GameFont(90f).getFont();
        Font fieldFont = new GameFont(35f).getFont();
        JLabel title = new JLabel("ARENA PRESETS");
        title.setForeground(Color.white);
        title.setFont(font);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(pos.X(30), pos.Y(10), pos.X(40), pos.Y(10));
        background.add(title);

        JTextField columns = new JTextField("Number of columns (must be even)");
        columns.setFont(fieldFont);
        columns.setOpaque(false);
        columns.setForeground(Color.decode("#AAAAAA"));
        columns.setBorder(null);
        columns.setHorizontalAlignment(SwingConstants.CENTER);
        columns.setBounds(pos.X(35), pos.Y(30), pos.X(30), pos.Y(5));
        background.add(columns);
        columns.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (columns.getText().equals("Number of columns (must be even)")) {
                    columns.setText("");
                    columns.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (columns.getText().isEmpty()) {
                    columns.setForeground(Color.decode("#AAAAAA"));
                    columns.setText("Number of columns (must be even)"); // Restaura el placeholder si está vacío
                }
            }
        });

        JPanel columnsLine = new JPanel();
        columnsLine.setBackground(Color.white);
        columnsLine.setBounds(pos.X(35), pos.Y(35), pos.X(30), 5);
        background.add(columnsLine);

        JTextField rows = new JTextField("Number of rows (must be even)");
        rows.setFont(fieldFont);
        rows.setOpaque(false);
        rows.setForeground(Color.decode("#AAAAAA"));
        rows.setBorder(null);
        rows.setHorizontalAlignment(SwingConstants.CENTER);
        rows.setBounds(pos.X(35), pos.Y(45), pos.X(30), pos.Y(5));
        background.add(rows);
        rows.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (rows.getText().equals("Number of rows (must be even)")) {
                    rows.setText("");
                    rows.setForeground(Color.WHITE); // Cambia el color del texto al obtener foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (rows.getText().isEmpty()) {
                    rows.setForeground(Color.decode("#AAAAAA"));
                    rows.setText("Number of rows (must be even)"); // Restaura el placeholder si está vacío
                }
            }
        });

        JPanel rowsLine = new JPanel();
        rowsLine.setBackground(Color.white);
        rowsLine.setBounds(pos.X(35), pos.Y(50), pos.X(30), 5);
        background.add(rowsLine);

        String[] options = {"Fire", "Water", "Earth", "Air", "Magic"};

        TransparentComboBox<String> charElements = new TransparentComboBox<>(options);
        charElements.setBounds(pos.X(35), pos.Y(60), pos.X(33), pos.Y(5));
        background.add(charElements);

        JPanel elementCharLine = new JPanel();
        elementCharLine.setBackground(Color.white);
        elementCharLine.setBounds(pos.X(35), pos.Y(65), pos.X(30), 5);
        background.add(elementCharLine);


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
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new Arena(background, pos, (String) charElements.getSelectedItem());
            }
        });
    }
}