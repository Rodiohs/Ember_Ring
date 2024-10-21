package Character;

public class Tower {
    private int player;
    private int pos_x;
    private int pos_y;
    private int resistance;
    public Tower(int player, int pos_x, int pos_y) {
        this.player = player;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.resistance = 100;
    }
    public void destroy_tower() {}
    public int get_resistance() {
        return resistance;
    }
    public void set_resistance(int resistance) {
        this.resistance = resistance;
    }
    public int get_player() {
        return player;
    }
    public void set_player(int player) {}
    public int get_pos_x() {
        return pos_x;
    }
    public void set_pos_x(int pos_x) {
        this.pos_x = pos_x;
    }
    public int get_pos_y() {
        return pos_y;
    }
    public void set_pos_y(int pos_y) {
        this.pos_y = pos_y;
    }
}
