package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RoundedButton extends JButton {
    private int borderRadius;

    public RoundedButton(String label, int borderRadius, float size, Color color) {
        super(label);
        this.borderRadius = borderRadius;
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/balatro.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            setFont(customFont);
        } catch (FontFormatException | IOException e) {
            System.out.println("No se pudo cargar la fuente: " + e.getMessage());
            e.printStackTrace();
        }
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.white);
        if (color != null){ setBackground(color); }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Llamar a super.paintComponent() primero para evitar sobrescribir el dibujo estándar
        super.paintComponent(g);

        // Configurar el color de fondo
        g.setColor(getBackground());
        // Dibujar un rectángulo con esquinas redondeadas
        g.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        // Configurar el color del texto
        g.setColor(getForeground());
        // Dibujar el texto centrado
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getHeight();
        g.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - fm.getDescent());
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        repaint();  // Volver a pintar el botón para reflejar el nuevo color de fondo
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        repaint();  // Asegurarse de que el nuevo color de texto se aplique
    }
}


