package Character;

public class Ability {

    private String name;
    private int damage;
    private int manaCost;
    private boolean melee;
    private boolean distance;
    private boolean state_switcher;

    // Constructor
    public Ability(String name, int damage, int manaCost, boolean melee, boolean distance, boolean state_switcher) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
        this.melee = melee;
        this.distance = distance;
        this.state_switcher = state_switcher;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void attack1(Character character, Character enemy){
        if(character.getAbility1().melee == true){
            int character_posx = character.getPosX();
            int character_posy = character.getPosY();

            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            boolean isAdjacent = (Math.abs(character_posx - enemy_posx) == 1 && character_posy == enemy_posy) ||  (Math.abs(character_posy - enemy_posy) == 1 && character_posx == enemy_posx);

            if (isAdjacent) {
                double dmgReduction = (enemy.get_defense_status() * (0.2));
                double finalDamage = (character.getAbility1().getDamage() * (1 - dmgReduction / 100));

                enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);
            } else {
                System.out.println("Enemy is not in attack range!");
            }

        } else if(character.getAbility1().distance == true){
            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            double dmgReduction = (enemy.get_defense_status() * (0.2));

            double finalDamage = (character.getAbility1().getDamage() * (1 - dmgReduction / 100));



            enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);

        } else if(state_switcher){
            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            double dmgReduction = (enemy.get_defense_status() * (0.2));

            double finalDamage = (character.getAbility1().getDamage() * (1 - dmgReduction / 100));



            enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);
        }
    }

    public void attack2(Character character, Character enemy){
        if(character.getAbility2().melee == true){
            int character_posx = character.getPosX();
            int character_posy = character.getPosY();

            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            boolean isAdjacent = (Math.abs(character_posx - enemy_posx) == 1 && character_posy == enemy_posy) ||  (Math.abs(character_posy - enemy_posy) == 1 && character_posx == enemy_posx);

            if (isAdjacent) {
                double dmgReduction = (enemy.get_defense_status() * (0.2));
                double finalDamage = (character.getAbility2().getDamage() * (1 - dmgReduction / 100));

                enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);
            } else {
            System.out.println("Enemy is not in attack range!");
            }
        } else if(character.getAbility2().distance == true){
            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            double dmgReduction = (enemy.get_defense_status() * (0.2));

            double finalDamage = (character.getAbility2().getDamage() * (1 - dmgReduction / 100));

            enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);
        } else if(character.getAbility2().state_switcher == true){
            int enemy_posx = enemy.getPosX();
            int enemy_posy = enemy.getPosY();

            double dmgReduction = (enemy.get_defense_status() * (0.2));

            double finalDamage = (character.getAbility2().getDamage() * (1 - dmgReduction / 100));

            enemy.set_health_status(enemy.get_health_status() - (int) finalDamage);
        }
    }

    public void attackTower1(Character character, Tower enemyTower){
        if(character.getAbility1().melee == true) {
            int tower_posx = enemyTower.get_pos_x();
            int tower_posy = enemyTower.get_pos_y();

            boolean isAdjacent = (Math.abs(character.getPosX() - tower_posx) == 1 && character.getPosY() == tower_posy) ||  (Math.abs(character.getPosY() - tower_posy) == 1 && character.getPosX() == tower_posx);

            if (isAdjacent) {
                enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility1().getDamage());
            } else {
                System.out.println("Tower is not in attack range!");
            }

        } else if(character.getAbility1().distance == true){
            enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility1().getDamage());
        } else if(character.getAbility1().state_switcher == true){
            enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility1().getDamage());
        }
    }

    public void attackTower2(Character character, Tower enemyTower){
        if(character.getAbility2().melee == true) {
            int tower_posx = enemyTower.get_pos_x();
            int tower_posy = enemyTower.get_pos_y();

            boolean isAdjacent = (Math.abs(character.getPosX() - tower_posx) == 1 && character.getPosY() == tower_posy) ||  (Math.abs(character.getPosY() - tower_posy) == 1 && character.getPosX() == tower_posx);

            if (isAdjacent) {
                enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility2().getDamage());
            } else {
                System.out.println("Tower is not in attack range!");
            }

        } else if(character.getAbility2().distance == true){
            enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility2().getDamage());
        } else if(character.getAbility1().state_switcher == true){
            enemyTower.set_resistance(enemyTower.get_resistance() - (int) character.getAbility2().getDamage());
        }
    }
}

