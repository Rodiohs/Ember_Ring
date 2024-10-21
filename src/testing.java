import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class testing {
    public static void main(String[] args) {
        // Ruta al archivo JSON (ajusta la ruta según la ubicación de tu archivo)
        String filePath = "src\\Characters.json";

        // Leer el archivo y convertirlo en un String
        StringBuilder jsonContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertimos el string en un JSONArray
        JSONArray characters = new JSONArray(jsonContent.toString());

        // Iteramos a través de cada personaje
        for (int i = 0; i < characters.length(); i++) {
            JSONObject character = characters.getJSONObject(i);

            // Obtenemos datos principales del personaje
            String name = character.getString("name");
            String specie = character.getString("specie");
            String element = character.getString("element");
            int maxHP = character.getInt("maxHP");
            int maxMana = character.getInt("maxMana");
            int healthStatus = character.getInt("health_status");
            int attackStatus = character.getInt("attack_status");
            int defenseStatus = character.getInt("defense_status");
            int stepsPerTurn = character.getInt("steps_per_turn");
            int posX = character.getInt("pos_x");
            int posY = character.getInt("pos_y");

            // Datos de las habilidades (ability1 y ability2)
            JSONObject ability1 = character.getJSONObject("ability1");
            String ability1Name = ability1.getString("name");
            int ability1Damage = ability1.getInt("damage");
            int ability1ManaCost = ability1.getInt("manaCost");
            boolean ability1Melee = ability1.getBoolean("melee");
            boolean ability1Distance = ability1.getBoolean("distance");
            boolean ability1StateSwitcher = ability1.getBoolean("state_switcher");

            JSONObject ability2 = character.getJSONObject("ability2");
            String ability2Name = ability2.getString("name");
            int ability2Damage = ability2.getInt("damage");
            int ability2ManaCost = ability2.getInt("manaCost");
            boolean ability2Melee = ability2.getBoolean("melee");
            boolean ability2Distance = ability2.getBoolean("distance");
            boolean ability2StateSwitcher = ability2.getBoolean("state_switcher");

            // Imprimimos los datos
            System.out.println("Nombre: " + name);
            System.out.println("Especie: " + specie);
            System.out.println("Elemento: " + element);
            System.out.println("HP Máximo: " + maxHP);
        }
    }
}


