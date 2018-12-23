package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;

import java.util.ArrayList;

public class MediterraneanAvenue extends GroupColoredProperty {

    public MediterraneanAvenue(int x,int y) {
        super("Mediterranean Avenue", 60,x,y, 30, ColorGroup.Purple,
                50, 25, new ArrayList<Integer>() {{
                    add(2);
                    add(10);
                    add(30);
                    add(90);
                    add(160);
                }},
                250, 125, 250,
                300, 150, 750);
    }
}
