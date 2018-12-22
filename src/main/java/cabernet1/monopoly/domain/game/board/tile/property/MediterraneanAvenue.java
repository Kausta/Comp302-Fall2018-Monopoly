package cabernet1.monopoly.domain.game.board.tile.property;

import cabernet1.monopoly.domain.game.board.tile.enumerators.ColorGroup;

import java.util.ArrayList;

public class MediterraneanAvenue extends GroupColoredProperty {

    private static final long serialVersionUID = -1060606226044564804L;

    public MediterraneanAvenue() {
        super("Mediterranean Avenue", 60, 30, ColorGroup.Purple,
                50, 25, new ArrayList<Integer>() {
                    private static final long serialVersionUID = -9058521490174646447L;

                    {
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
