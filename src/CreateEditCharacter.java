import enums.Element;
import enums.SpeciesName;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import Character.Character;
import Character.Ability;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CreateEditCharacter {
    private TransparentComboBox<String> charElements = null;
    private TransparentComboBox<String> charSpecies = null;
    private boolean melee;
    private boolean distance;
    private boolean state_switcher;
    private Element element;
    private String specie;
    private SpeciesName currentSpecie;
    private String currentImg;


    public CreateEditCharacter(JSONArray characters, List<Character> characterList,graphicInterface background, GetPos pos, int maxPlayers, Player player1, Player player2, JSONObject pastChar){

        background.setFilter(10, 200, 10, 100);
        Font font = new GameFont(90f).getFont();
        Font playerFont = new GameFont(35f).getFont();

        JLabel title = new JLabel("CHARACTER CREATION");
        title.setForeground(Color.white);
        title.setFont(font);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(pos.X(30), pos.Y(5), pos.X(40), pos.Y(10));
        background.add(title);

        JLabel titlePoints = new JLabel("Points left: 1000");
        titlePoints.setForeground(Color.white);
        titlePoints.setFont(playerFont);
        titlePoints.setVerticalAlignment(SwingConstants.CENTER);
        titlePoints.setHorizontalAlignment(SwingConstants.CENTER);
        titlePoints.setBounds(pos.X(30), pos.Y(15), pos.X(40), pos.Y(3));
        background.add(titlePoints);

        RoundedPanel charPanel = new RoundedPanel(30, 0, Color.decode("#181818"));
        charPanel.setBackground(Color.decode("#181818"));
        charPanel.setBounds(pos.X(35), pos.Y(20), pos.X(30), pos.Y(60));
        charPanel.setLayout(null);
        background.add(charPanel);

        RoundedPanel ab1Panel = new RoundedPanel(30, 0, Color.decode("#181818"));
        ab1Panel.setBackground(Color.decode("#181818"));
        ab1Panel.setBounds(pos.X(3), pos.Y(35), pos.X(30), pos.Y(30));
        ab1Panel.setLayout(null);
        background.add(ab1Panel);

        RoundedPanel ab2Panel = new RoundedPanel(30, 0, Color.decode("#181818"));
        ab2Panel.setBackground(Color.decode("#181818"));
        ab2Panel.setBounds(pos.X(67), pos.Y(35), pos.X(30), pos.Y(30));
        ab2Panel.setLayout(null);
        background.add(ab2Panel);

        GetPos forPanels = new GetPos(charPanel.getWidth(), charPanel.getHeight());
        GetPos forSidePanels = new GetPos(ab2Panel.getWidth(), ab2Panel.getHeight());

        String[] options = {"Fire", "Water", "Earth", "Air", "Magic"};

        TextFieldLine charName =     new TextFieldLine(playerFont, forPanels, charPanel, 10, 8,  80, 7, "Character's name");
        TextFieldLine charHP =       new TextFieldLine(playerFont, forPanels, charPanel, 10, 19, 80, 7, "Character's HP");
        TextFieldLine charDefense =  new TextFieldLine(playerFont, forPanels, charPanel, 10, 30, 80, 7, "Character's defense");
        TextFieldLine charATK =      new TextFieldLine(playerFont, forPanels, charPanel, 10, 41, 80, 7, "Character's attack");
        TextFieldLine charMana =     new TextFieldLine(playerFont, forPanels, charPanel, 10, 52, 80, 7, "Character's mana");
        TextFieldLine charMovement = new TextFieldLine(playerFont, forPanels, charPanel, 10, 63, 80, 7, "Character's movement");

        TextFieldLine ab1Name =      new TextFieldLine(playerFont, forSidePanels, ab1Panel, 10, 15, 80, 14, "Ability 1's name");
        TextFieldLine ab1Attack =    new TextFieldLine(playerFont, forSidePanels, ab1Panel, 10, 43, 80, 14, "Ability 1's attack");
        TextFieldLine ab1Mana =      new TextFieldLine(playerFont, forSidePanels, ab1Panel, 10, 71, 80, 14, "Ability 1's mana cost");

        TextFieldLine ab2Name =      new TextFieldLine(playerFont, forSidePanels, ab2Panel, 10, 15, 80, 14, "Ability 2's name");
        TextFieldLine ab2Attack =    new TextFieldLine(playerFont, forSidePanels, ab2Panel, 10, 43, 80, 14, "Ability 2's attack");
        TextFieldLine ab2Mana =      new TextFieldLine(playerFont, forSidePanels, ab2Panel, 10, 71, 80, 14, "Ability 2's mana cost");

        if(pastChar != null){
            charName.setText(pastChar.getString("name"));
            charHP.setText(String.valueOf(pastChar.getInt("health_status")));
            charDefense.setText(String.valueOf(pastChar.getInt("defense_status")));
            charATK.setText(String.valueOf(pastChar.getInt("attack_status")));
            charMana.setText(String.valueOf(pastChar.getInt("manna_status")));
            charMovement.setText(String.valueOf(pastChar.getInt("steps_per_turn")));

            JSONObject ability1 = pastChar.getJSONObject("ability1");
            JSONObject ability2 = pastChar.getJSONObject("ability2");

            ab1Name.setText(ability1.getString("name"));
            ab1Attack.setText(String.valueOf(ability1.getInt("damage")));
            ab1Mana.setText(String.valueOf(ability1.getInt("manaCost")));

            ab2Name.setText(ability2.getString("name"));
            ab2Attack.setText(String.valueOf(ability2.getInt("damage")));
            ab2Mana.setText(String.valueOf(ability2.getInt("manaCost")));
        }

        TransparentComboBox<String> charElements = new TransparentComboBox<>(options);
        charElements.setBounds(forPanels.X(10), forPanels.Y(74), forPanels.X(90), forPanels.Y(7));
        charPanel.add(charElements);

        JPanel elementCharLine = new JPanel();
        elementCharLine.setBackground(Color.white);
        elementCharLine.setBounds(forPanels.X(10), forPanels.Y(81), forPanels.X(80), 5);
        charPanel.add(elementCharLine);

        TransparentComboBox<String> charSpecies = new TransparentComboBox<>(options);
        charSpecies.setBounds(forPanels.X(10), forPanels.Y(85), forPanels.X(90), forPanels.Y(7));
        charPanel.add(charSpecies);

        JPanel speciesCharLine = new JPanel();
        speciesCharLine.setBackground(Color.white);
        speciesCharLine.setBounds(forPanels.X(10), forPanels.Y(92), forPanels.X(80), 5);
        charPanel.add(speciesCharLine);

        if(pastChar != null){
            charElements.setSelectedItem(pastChar.getString("element"));
            if (element == Element.Fire) {
                charSpecies.removeAllItems();
                charSpecies.addItem("Charigator");
                charSpecies.addItem("Fire Goblin");
                charSpecies.addItem("Flame Cat");
            } else {
                if (element == Element.Water) {
                    charSpecies.removeAllItems();
                    charSpecies.addItem("Firefighter");
                    charSpecies.addItem("Kraken Man");
                    charSpecies.addItem("Fish Man");
                } else {
                    if (element == Element.Air) {
                        charSpecies.removeAllItems();
                        charSpecies.addItem("Katana Monkey");
                        charSpecies.addItem("Air Ninja");
                        charSpecies.addItem("Killer Rooster");
                    } else {
                        if (element == Element.Earth) {
                            charSpecies.removeAllItems();
                            charSpecies.addItem("Attack Gorila");
                            charSpecies.addItem("Crazy Sloth");
                            charSpecies.addItem("Mud Monster");
                        } else {
                            if (element == Element.Magic) {
                                charSpecies.removeAllItems();
                                charSpecies.addItem("Wizard");
                                charSpecies.addItem("Dobbie");
                                charSpecies.addItem("Rainbowcorn");
                            }
                        }
                    }
                }
            }
            charSpecies.setSelectedItem(pastChar.getString("specie"));
        }

        RoundedButton back = new RoundedButton("BACK", 30, 50f, Color.decode("#181818"));
        back.setBounds(pos.X(29), pos.Y(85), pos.X(20), pos.Y(10));
        background.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.removeAll();  // Eliminar todos los componentes del panel
                background.revalidate(); // Actualizar el diseño del panel
                background.repaint();
                new CharSelection(background, pos, characters, maxPlayers, player1, player2);
            }
        });

        charElements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String preElement = charElements.getSelectedItem().toString();
                element = Element.valueOf(preElement);


                System.out.println(element);
                if (element == Element.Fire) {
                    charSpecies.removeAllItems();
                    charSpecies.addItem("Charigator");
                    charSpecies.addItem("Fire Goblin");
                    charSpecies.addItem("Flame Cat");
                } else {
                    if (element == Element.Water) {
                        charSpecies.removeAllItems();
                        charSpecies.addItem("Firefighter");
                        charSpecies.addItem("Kraken Man");
                        charSpecies.addItem("Fish Man");
                    } else {
                        if (element == Element.Air) {
                            charSpecies.removeAllItems();
                            charSpecies.addItem("Katana Monkey");
                            charSpecies.addItem("Air Ninja");
                            charSpecies.addItem("Killer Rooster");
                        } else {
                            if (element == Element.Earth) {
                                charSpecies.removeAllItems();
                                charSpecies.addItem("Attack Gorila");
                                charSpecies.addItem("Crazy Sloth");
                                charSpecies.addItem("Mud Monster");
                            } else {
                                if (element == Element.Magic) {
                                    charSpecies.removeAllItems();
                                    charSpecies.addItem("Wizard");
                                    charSpecies.addItem("Dobbie");
                                    charSpecies.addItem("Rainbowcorn");
                                }
                            }
                        }
                    }
                }
                specie = charSpecies.getSelectedItem().toString();
                getSpecie();
            }
        });

        RoundedButton next = new RoundedButton("NEXT", 30, 50f, Color.decode("#181818"));
        next.setBounds(pos.X(51), pos.Y(85), pos.X(20), pos.Y(10));
        background.add(next);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCharacter(characters,
                        characterList,
                        pastChar,
                        charName.getText(),
                        Integer.parseInt(charHP.getText()),
                        Integer.parseInt(charDefense.getText()),
                        Integer.parseInt(charATK.getText()),
                        Integer.parseInt(charMana.getText()),
                        Integer.parseInt(charMovement.getText()),
                        ab1Name.getText(),
                        Integer.parseInt(ab1Attack.getText()),
                        Integer.parseInt(ab1Mana.getText()),
                        ab2Name.getText(),
                        Integer.parseInt(ab2Attack.getText()),
                        Integer.parseInt(ab2Mana.getText()));
            }
        });
    }

    private void updateCharacter(JSONArray characters, List<Character> characterList, JSONObject pastChar, String charName, int charHP, int charDefense, int charATK, int charMana, int charMovement, String ab1Name, int ab1Attack, int ab1Mana, String ab2Name, int ab2Attack, int ab2Mana){
        if(currentImg == null || currentSpecie == null){

        } else {

            Ability ability1 = new Ability(
                    ab1Name,
                    ab1Attack,
                    ab1Mana,
                    melee,
                    distance,
                    state_switcher
            );

            Ability ability2 = new Ability(
                    ab2Name,
                    ab2Attack,
                    ab2Mana,
                    melee,
                    distance,
                    state_switcher
            );

            Character currentChar = new Character(
                    charName,
                    element,
                    ability1,
                    ability2,
                    0,
                    0,
                    0,
                    charHP,
                    charATK,
                    charMana,
                    charDefense,
                    charMovement,
                    currentSpecie,
                    currentImg
            );

            saveCharacterToJson(pastChar, currentChar);
        }
    }

    private void saveCharacterToJson(JSONObject pastCharacter, Character editedCharacter) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("src\\Characters.json");
        List<Character> characterList = new ArrayList<Character>();

        // Cargar personajes existentes desde JSON
        try {
            if (file.exists()) {
                try (Reader reader = new FileReader(file)) { // Use try-with-resources to ensure the reader is closed
                    Type characterListType = new TypeToken<List<Character>>() {}.getType();
                    characterList = gson.fromJson(reader, characterListType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean characterFoundAndReplaced = false;
        if (pastCharacter != null) {
            String pastCharacterName = pastCharacter.getString("name");
            for (int i = 0; i < characterList.size(); i++) {
                Character existingCharacter = characterList.get(i);
                if (existingCharacter.get_name().equals(pastCharacterName)) {
                    characterList.set(i, editedCharacter); // Reemplaza el personaje existente
                    characterFoundAndReplaced = true;
                    break;
                }
            }
        }

        if (!characterFoundAndReplaced) { // Si pastCharacter es null o no se encontró el personaje
            characterList.add(editedCharacter); // Añadir como un nuevo personaje
        }

        // Guardar de nuevo al archivo JSON
        try (Writer writer = new FileWriter(file)) { // Use try-with-resources to ensure the writer is closed
            gson.toJson(characterList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Character saved successfully!");
    }


    private void getSpecie() {
        switch (specie) {
            case "Charigator":
                currentSpecie = SpeciesName.CHARIGATOR;
                currentImg = "src\\images\\fire\\charigator1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
            case "Fire Goblin":
                currentSpecie = SpeciesName.FIRE_GOBLIN;
                currentImg = "src\\images\\fire\\fireGoblin1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Flame Cat":
                currentSpecie = SpeciesName.FLAME_CAT;
                currentImg = "src\\images\\fire\\flameCat1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Katana Monkey":
                currentSpecie = SpeciesName.KATANA_MONKEY;
                currentImg = "src\\images\\air\\katanaMonkey1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
            case "Killer Rooster":
                currentSpecie = SpeciesName.KILLER_ROOSTER;
                currentImg = "src\\images\\air\\killerRooster1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Air Ninja":
                currentSpecie = SpeciesName.MONK;
                currentImg = "src\\images\\air\\airNinja1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Kraken Man":
                currentSpecie = SpeciesName.KRAKEN_MAN;
                currentImg = "src\\images\\water\\krakenMan1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Fish Man":
                currentSpecie = SpeciesName.FISH_MAN;
                currentImg = "src\\images\\water\\fishMan1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
            case "Firefighter":
                currentSpecie = SpeciesName.FIREFIGHTER;
                currentImg = "src\\images\\water\\firefighter1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Dobbie":
                currentSpecie = SpeciesName.DOBI;
                currentImg = "src\\images\\magic\\dobi1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
            case "Rainbowcorn":
                currentSpecie = SpeciesName.RAINBOWCORN;
                currentImg = "src\\images\\magic\\rainbowcorn1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Wizard":
                currentSpecie = SpeciesName.WIZARD;
                currentImg = "src\\images\\magic\\wizard1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Attack Gorilla":
                currentSpecie = SpeciesName.BAZOOKA_GORILLA;
                currentImg = "src\\images\\dirt\\attackGorilla1.png";
                melee = false;
                distance = true;
                state_switcher = false;
                break;
            case "Crazy Sloth":
                currentSpecie = SpeciesName.CRAZY_SLOTH;
                currentImg = "src\\images\\dirt\\crazySloth1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
            case "Mud Monster":
                currentSpecie = SpeciesName.MUD_MONSTER;
                currentImg = "src\\images\\dirt\\mudMonster1.png";
                melee = true;
                distance = false;
                state_switcher = false;
                break;
        }
    }
}
