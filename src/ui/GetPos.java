package ui;

import javax.swing.*;

public class GetPos {

    private int posx;
    private int posy;

    public GetPos(int x, int y){
        this.posx = x;
        this.posy = y;
    }

    public int X(int percent){
        return (percent * posx) / 100;
    }

    public int Y(int percent){
        return (percent * posy) / 100;
    }
}
