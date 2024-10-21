package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFont {

    private Font gameFont;

    public GameFont(float size){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/balatro.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            this.gameFont = customFont;
        } catch (FontFormatException | IOException e) {
            System.out.println("No se pudo cargar la fuente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Font getFont(){
        return gameFont;
    }
}
