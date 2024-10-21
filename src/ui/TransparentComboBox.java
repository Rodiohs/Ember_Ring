package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class TransparentComboBox<E> extends JComboBox<E> {

    public TransparentComboBox(E[] items) {
        super(items);
        initTransparentComboBox();
    }

    private void initTransparentComboBox() {
        // Hacer el JComboBox no opaco (transparente)
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        setFont(new GameFont(35f).getFont());
        setForeground(Color.white);


        // Personalizar la UI del ComboBox para hacer el popup (lista desplegable) también transparente
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Crear un botón invisible (flecha de despliegue)
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder()); // Sin bordes
                button.setVisible(false); // Hacer que el botón sea invisible
                return button;
            }

            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(comboBox) {
                    @Override
                    protected JList createList() {
                        JList list = super.createList();
                        list.setOpaque(false); // Lista de opciones transparente
                        list.setBackground(new Color(0, 0, 0, 0));
                        return list;
                    }
                };
                return popup;
            }
        });

        // Si el ComboBox es editable, hacer el editor transparente también
        setEditable(true);
        JTextField editor = (JTextField) getEditor().getEditorComponent();
        editor.setOpaque(false);
        editor.setBackground(new Color(0, 0, 0, 0));
        editor.setHorizontalAlignment(JTextField.CENTER); // Centrar texto en el editor
        editor.setForeground(Color.white);
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto en las celdas
                label.setForeground(Color.white);
                return label;
            }
        });
    }
}

