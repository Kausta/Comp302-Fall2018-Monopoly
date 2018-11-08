package cabernet1.monopoly.domain.tile.property.building;

import java.util.ArrayList;
import java.util.HashMap;

public class House extends Building{
    
    public HashMap<Integer, Integer> rents;

    public House(int price, int sellPrice, ArrayList<Integer> rents){
        super(price, sellPrice);
        for(int i = 0; i < 5; i++){
            this.rents.put(i, rents.get(i));
        }
        limit = 4;
    }

    @Override
    public int getRent(){
        return rents.get(amount);
    }

    
}