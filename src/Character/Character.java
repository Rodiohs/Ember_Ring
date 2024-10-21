package Character;

import enums.Element;
import enums.SpeciesName;

public class Character extends Species {

    private String img;
    private Ability ability1;
    private Ability ability2;
    private SpeciesName specie;
    private int maxHP;
    private int maxMana;

    // Constructor
    public Character(String name, Element element, Ability ability1, Ability ability2, int pos_x, int pos_y, int level_status, int health_status, int attack_status, int manna_status, int defense_status, int steps_per_turn, SpeciesName specie, String img) {
        // Call the constructor of the superclass (Species)
        super(name, element, pos_x, pos_y, level_status, health_status, attack_status, manna_status, defense_status, steps_per_turn);

        // Initialize Character-specific fields
        this.name = name;
        this.element = element;
        this.specie = specie;
        this.health_status = health_status;
        this.level_status = level_status;
        this.attack_status = attack_status;
        this.defense_status = defense_status;
        this.manna_status = manna_status;
        this.steps_per_turn = steps_per_turn;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.img = img;
        this.maxHP = health_status;
        this.maxMana = manna_status;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public SpeciesName getSpecie() {
        return specie;
    }

    public void setSpecie(SpeciesName specie) {
        this.specie = specie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPosX() {
        return pos_x;
    }

    public void setPosX(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPosY() {
        return pos_y;
    }

    public void setPosY(int pos_y) {
        this.pos_y = pos_y;
    }

    public int get_MaxMana(){
        return maxMana;
    }

    public void attack(Character character, Character enemy, Tower enemyTower){
        if(enemyTower == null){
            int character_posx = character.getPosX();
            int character_posy = character.getPosY();

            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            boolean isAdjacent = (Math.abs(character_posx - enemy_posx) == 1 && character_posy == enemy_posy) ||  (Math.abs(character_posy - enemy_posy) == 1 && character_posx == enemy_posx);

            if (isAdjacent) {
                enemy.set_health_status(enemy.get_health_status() - (int) character.get_attack_status());
            } else {
                System.out.println("Enemy is not in attack range!");
            }
        } else {
            int tower_posx = enemyTower.get_pos_x();
            int tower_posy = enemyTower.get_pos_y();

            boolean isAdjacent = (Math.abs(character.getPosX() - tower_posx) == 1 && character.getPosY() == tower_posy) ||  (Math.abs(character.getPosY() - tower_posy) == 1 && character.getPosX() == tower_posx);

            if (isAdjacent) {
                enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility1().getDamage());
            } else {
                System.out.println("Tower is not in attack range!");
            }
        }
    }

    public void useAbility1(Ability ability1, Character character, Character enemy, Tower enemyTower) {
        if(enemyTower == null) {
            System.out.println(ability1.getName());
            System.out.println(enemy.get_health_status());
            ability1.attack1(character, enemy);
            System.out.println(enemy.get_health_status());
            if(enemy.get_health_status() <= 0){
                enemy.die();
                character.levelUp(character);
            }
            manna_status -= ability1.getManaCost();
        } else {
            System.out.println(ability1.getName());
            System.out.println(enemyTower.get_resistance());
            ability1.attackTower1(character, enemyTower);
            System.out.println(enemyTower.get_resistance());
            if(enemyTower.get_resistance() <= 0){
                character.levelUp(character);
            }
            manna_status -= ability1.getManaCost();
        }
    }

    public void useAbility2(Ability ability2, Character character, Character enemy, Tower enemyTower) {
        if(enemyTower == null) {
            System.out.println(ability2.getName());
            System.out.println(enemy.get_health_status());
            ability2.attack2(character, enemy);
            System.out.println(enemy.get_health_status());
            if(enemy.get_health_status() <= 0){
                enemy.die();
                character.levelUp(character);
            }
            manna_status -= ability2.getManaCost();
        } else {
            System.out.println(ability2.getName());
            System.out.println(enemyTower.get_resistance());
            ability2.attackTower2(character, enemyTower);
            System.out.println(enemyTower.get_resistance());
            if(enemyTower.get_resistance() <= 0){
                character.levelUp(character);
            }
            manna_status -= ability2.getManaCost();
        }
    }

    public void levelUp(Character character) {
        character.set_health_status(maxHP);
        character.set_manna_status(maxMana);

        character.set_level_status(get_level_status()+1);

        character.set_health_status(get_health_status() + (int) (get_health_status()*0.1));
        maxHP = character.get_health_status();
        character.set_manna_status(get_manna_status() + (int) (get_manna_status()*0.1));
        maxMana = character.get_manna_status();

        character.set_attack_status(get_attack_status() + (int) (get_attack_status()*0.1));
        character.set_defense_status(get_defense_status() + (int) (get_defense_status()*0.1));
        character.set_steps_per_turn(get_steps_per_turn() + 1);

        character.getAbility1().setDamage((int) (getAbility1().getDamage() + (int) getAbility1().getDamage()*0.1));
        character.getAbility1().setManaCost((int) (getAbility1().getManaCost() + (int) getAbility1().getManaCost()*0.1));

        character.getAbility2().setDamage((int) (getAbility2().getDamage() + (int) getAbility2().getDamage()*0.1));
        character.getAbility2().setManaCost((int) (getAbility2().getManaCost() + (int) getAbility2().getManaCost()*0.1));

        System.out.println(character.get_name() + " Leveled Up!");
    }
}
