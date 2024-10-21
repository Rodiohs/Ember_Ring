package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class RoundedPanel extends JPanel {

    private int cornerRadius;
    private int borderThickness;
    private Color borderColor;

    // Constructor para definir el radio de los bordes redondeados
    public RoundedPanel(int radius, int borderThickness, Color borderColor) {
        this.cornerRadius = radius;
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
        setOpaque(false); // Permite que el fondo sea transparente para mostrar los bordes redondeados
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing para bordes más suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color de fondo del panel
        g2.setColor(getBackground());

        // Dibujar un rectángulo redondeado (RoundRectangle2D)
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing para bordes más suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color del borde (puedes cambiar este color)
        g2.setColor(borderColor);

        // Dibujar el borde redondeado
        g2.setStroke(new BasicStroke(borderThickness));  // Aquí ajustamos el grosor del borde
        g2.draw(new RoundRectangle2D.Double(borderThickness / 2.0, borderThickness / 2.0,
                getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius));

        g2.dispose();
    }
}

