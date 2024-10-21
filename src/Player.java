import Character.Character;
import Character.Tower;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerNumber;
    private String username;
    private boolean playingTurn;
    private List<Character> characters;
    private List<Tower> towers;
    public Player(int playerNumber, String username, List<Character> characters) {
        this.playerNumber = playerNumber;
        this.username = username;
        this.playingTurn = false;
        this.characters = characters;
        this.towers = new ArrayList<Tower>();
    }

    public int get_playerNumber() {
        return playerNumber;
    }

    public void set_playerNumber(int number) {
        this.playerNumber = number;
    }

    public String get_username() {
        return username;
    }

    public void set_username(String username) {
        this.username = username;
    }

    public boolean is_playing_turn() {
        return playingTurn;
    }

    public List<Character> get_characters() {
        for(int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
        }
        return characters;
    }

    public void set_characters(List<Character> characters) {
        this.characters = characters;
    }

    public void set_playing_turn(boolean turn) {
        this.playingTurn = turn;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public List<Tower> getTowers() {
        return towers;
    }
}


