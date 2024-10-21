package Character;
import enums.Element;

public class Species {
    protected String name;
    protected Element element;
    protected int pos_x;
    protected int pos_y;
    protected int level_status;
    protected int health_status;
    protected int attack_status;
    protected int manna_status;
    protected int defense_status;
    protected int steps_per_turn;
    public Species(String name, Element element, int pos_x, int pos_y, int level_status, int health_status, int attack_status, int manna_status, int defense_status, int steps_per_turn) {
        this.name = name;
        this.element = element;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.level_status = level_status;
        this.health_status = health_status;
        this.attack_status = attack_status;
        this.manna_status = manna_status;
        this.defense_status = defense_status;
        this.steps_per_turn = steps_per_turn;
    }
    public String get_name() {
        return name;
    }
    public int get_pos_x() {
        return pos_x;
    }
    public int get_pos_y() {
        return pos_y;
    }
    public int get_level_status() {
        return level_status;
    }
    public int get_health_status() {
        return health_status;
    }
    public int get_attack_status() {
        return attack_status;
    }
    public int get_manna_status() {
        return manna_status;
    }
    public int get_defense_status() {
        return defense_status;
    }
    public int get_steps_per_turn() {
        return steps_per_turn;
    }
    public void set_name(String name) {
        this.name = name;
    }
    public void set_pos_x(int pos_x) {
        this.pos_x = pos_x;
    }
    public void set_pos_y(int pos_y) {
        this.pos_y = pos_y;
    }
    public void set_level_status(int level_status) {
        this.level_status = level_status;
    }
    public void set_health_status(int health_status) {
        this.health_status = health_status;
    }
    public void set_attack_status(int attack_status) {
        this.attack_status = attack_status;
    }
    public void set_manna_status(int manna_status) {
        this.manna_status = manna_status;
    }
    public void set_defense_status(int defense_status) {
        this.defense_status = defense_status;
    }
    public void set_steps_per_turn(int steps_per_turn) {
        this.steps_per_turn = steps_per_turn;
    }
    public void set_element(Element element) {this.element = element;}
    public Element get_element() {return this.element;}
    public void move(String direction) {
    }
    public void basic_attack(int enemy_pos_x, int enemy_pos_y) {
    }
    public void receive_damage(int damage) {
    }
    public void spawn() {
    }
    public void die() {
    }
}