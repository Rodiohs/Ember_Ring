import enums.Element;
import enums.SpeciesName;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.GameFont;
import ui.GetPos;
import ui.RoundedButton;
import ui.RoundedPanel;
import Character.Character;
import enums.SpeciesName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CharSelection {

    private int layer = 0;
    private JSONObject selected;
    private JSONArray characters;
    private int maxCharacters;
    private Player player1;
    private Player player2;
    private List<Character> characterList;

    public CharSelection(graphicInterface background, GetPos pos, JSONArray chars, int maxCharacters, Player player1, Player player2){
        this.characters = chars;
        this.maxCharacters = maxCharacters;
        this.player1 = player1;
        this.player2 = player2;
        this.characterList = new ArrayList<Character>();

        background.setFilter(116, 12, 177, 100);
        Font font = new GameFont(90f).getFont();
        Font playerFont = new GameFont(35f).getFont();

        JLabel title = new JLabel("CHARACTER SELECTION");
        title.setForeground(Color.white);
        title.setFont(font);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(pos.X(30), pos.Y(5), pos.X(40), pos.Y(10));
        background.add(title);

        JLabel playerTitle = new JLabel(player1.get_username());
        playerTitle.setForeground(Color.white);
        playerTitle.setFont(playerFont);
        playerTitle.setVerticalAlignment(SwingConstants.CENTER);
        playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        playerTitle.setBounds(pos.X(30), pos.Y(15), pos.X(40), pos.Y(5));
        background.add(playerTitle);

        RoundedPanel container = new RoundedPanel(30, 0, Color.decode("#181818"));
        container.setBackground(Color.decode("#181818"));
        container.setBounds(pos.X(15), pos.Y(25), pos.X(70), pos.Y(50));
        background.add(container);

        Font btnFont = new GameFont(150f).getFont();

        JButton left = new JButton("<");
        left.setFont(btnFont);
        left.setForeground(Color.white);
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorder(null);
        left.setBounds(pos.X(10), pos.Y(25), pos.X(5), pos.Y(50));
        background.add(left);

        JButton right = new JButton(">");
        right.setFont(btnFont);
        right.setForeground(Color.white);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorder(null);
        right.setBounds(pos.X(85), pos.Y(25), pos.X(5), pos.Y(50));
        background.add(right);

        RoundedButton selectChar = new RoundedButton("SELECT CHARACTER", 30, 40f, Color.decode("#181818"));
        selectChar.setBounds(pos.X(6), pos.Y(85), pos.X(16), pos.Y(10));
        background.add(selectChar);

        RoundedButton newChar = new RoundedButton("NEW CHARACTER", 30, 40f, Color.decode("#181818"));
        newChar.setBounds(pos.X(24), pos.Y(85), pos.X(16), pos.Y(10));
        background.add(newChar);

        RoundedButton play = new RoundedButton("PLAY!", 30, 40f, Color.decode("#A00000"));
        play.setBounds(pos.X(42), pos.Y(85), pos.X(16), pos.Y(10));
        background.add(play);
        play.setEnabled(false);

        RoundedButton editChar = new RoundedButton("EDIT CHARACTER", 30, 40f, Color.decode("#181818"));
        editChar.setBounds(pos.X(60), pos.Y(85), pos.X(16), pos.Y(10));
        background.add(editChar);

        RoundedButton deleteChar = new RoundedButton("DELETE CHARACTER", 30, 40f, Color.decode("#181818"));
        deleteChar.setBounds(pos.X(78), pos.Y(85), pos.X(16), pos.Y(10));
        background.add(deleteChar);

        String filePath = "src/Characters.json";

        // Leer el archivo y convertirlo en un String
        if (characters == null){
            StringBuilder jsonContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    jsonContent.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            characters = new JSONArray(jsonContent.toString());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("C:\\Users\\Kenneth\\IdeaProjects\\Tec of legends\\src\\Characters.json");

        // Load existing characters from JSON
        try {
            if (file.exists()) {
                Reader reader = new FileReader(file);
                Type characterListType = new TypeToken<List<Character>>() {}.getType();
                characterList = gson.fromJson(reader, characterListType);
                reader.close();
            } else {
                characterList = new ArrayList<>(); // Initialize if file does not exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (layer != 0){
                    layer-=8;
                    printingCharacters(container);
                }
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (characters != null){
                    if (layer < characters.length()){
                        layer+=8;
                        printingCharacters(container);
                    }
                }
                else {
                    System.out.println("nigga");
                }
            }
        });

        selectChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmSelected(container, play, playerTitle);
            }
        });
        printingCharacters(container);

        newChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new CreateEditCharacter(characters, characterList, background, pos, maxCharacters, player1, player2);
            }
        });

        deleteChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < characters.length(); i++) {
                    if (characters.getJSONObject(i) == selected) {
                        deleteCharacterFromJson(selected.getString("name"), container);
                    }
                }
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new ArenaPreset(background, pos);
            }
        });
    }

    private void printingCharacters(RoundedPanel container){
        container.removeAll();  // Eliminar todos los componentes del panel
        container.revalidate(); // Actualizar el diseño del panel
        container.repaint();
        GetPos pos = new GetPos(container.getWidth(), container.getHeight());
        container.setLayout(null);
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (i+(4*j)+layer < characters.length()){
                    JSONObject character = characters.getJSONObject(i+(4*j)+layer);
                    JButton charHolder = new JButton();
                    charHolder.setBounds(pos.X(5+(25*i)), pos.Y(5+(50*j)), pos.X(15), pos.X(15));
                    String imagePath = character.getString("img");
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage();
                    Image newImage = img.getScaledInstance(pos.X(15), pos.X(15), Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImage);
                    charHolder.setIcon(icon);
                    buttons.add(charHolder);
                    charHolder.setBorder(null);
                    charHolder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            charSelected(buttons, charHolder, character);
                        }
                    });
                    container.add(charHolder);
                    JLabel name = new JLabel(character.getString("name"));
                    name.setFont(new GameFont(20f).getFont());
                    name.setForeground(Color.white);
                    name.setHorizontalAlignment(SwingConstants.CENTER);
                    name.setVerticalAlignment(SwingConstants.CENTER);
                    name.setBounds(pos.X(5+(25*i)), pos.Y(5+(50*j))+pos.X(15), pos.X(15), pos.Y(5));
                    container.add(name);
                }
            }
        }
    }

    private void charSelected(ArrayList<JButton> buttons, JButton charHolder, JSONObject character){
        for(JButton button: buttons){
            button.setBorder(null);
        }
        charHolder.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#A520F5"), 5),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        this.selected = character;
    }

    private void confirmSelected(RoundedPanel container, RoundedButton play, JLabel playerTitle){
        String compName = null;

        for(int i = 0; i < characters.length(); i++){
            if (characters.getJSONObject(i) == selected){
                compName = characters.getJSONObject(i).getString("name");
                for (Character newChar : characterList) {
                    System.out.println(newChar.get_name());
                    if (compName.equals(newChar.get_name())) {
                        if (player1.get_characters().size() < maxCharacters+1) {
                            player1.get_characters().add(newChar);
                            if (player1.get_characters().size() == maxCharacters+1) {
                                playerTitle.setText(player2.get_username());
                            }
                        } else if (player2.get_characters().size() < maxCharacters+1 && player1.get_characters().size() == maxCharacters+1) {
                            player2.get_characters().add(newChar);
                            if(player2.get_characters().size() == maxCharacters+1){
                                play.setEnabled(true);
                            }
                        }
                    }
                }
                if(player2.get_characters().size() != maxCharacters+1){
                    characters.remove(i);
                    printingCharacters(container);
                }
            }
            i++;
        }
    }

    private void deleteCharacterFromJson(String characterName, RoundedPanel container) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("C:\\Users\\Kenneth\\IdeaProjects\\Tec of legends\\src\\Characters.json");

        List charactersList = new ArrayList();

        // Load existing characters from JSON
        try {
            if (file.exists()) {
                Reader reader = new FileReader(file);
                Type characterListType = new TypeToken<List<Character>>() {}.getType();
                charactersList = gson.fromJson(reader, characterListType);
                reader.close();
            } else {
                charactersList = new ArrayList<>(); // Initialize if file does not exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find and remove the character by name
        for (int i = 0; i < charactersList.size(); i++) {
            Character existingCharacter = (Character) charactersList.get(i);
            if (existingCharacter.get_name().equals(characterName)) {
                charactersList.remove(i); // Remove the character
                break;
            }
        }

        // Save back to the JSON file
        try {
            Writer writer = new FileWriter(file);
            gson.toJson(charactersList, writer);
            writer.close();
            updateLists(container);
            printingCharacters(container);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Character deleted successfully!");
    }

    private void updateLists(RoundedPanel container){
        String filePath = "src/Characters.json";
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        characters = new JSONArray(jsonContent.toString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("C:\\Users\\Kenneth\\IdeaProjects\\Tec of legends\\src\\Characters.json");

        // Load existing characters from JSON
        try {
            if (file.exists()) {
                Reader reader = new FileReader(file);
                Type characterListType = new TypeToken<List<Character>>() {}.getType();
                characterList = gson.fromJson(reader, characterListType);
                reader.close();
                printingCharacters(container);
            } else {
                characterList = new ArrayList<>(); // Initialize if file does not exist
                printingCharacters(container);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
