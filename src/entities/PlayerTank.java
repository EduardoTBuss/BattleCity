package entities;

import world.Map;
import java.util.List;

public class PlayerTank extends Tank {

    public PlayerTank(int x, int y, List<Entity> entities, Map map){
        super(x, y, entities, map);
        this.health = 3;
    }

    @Override
    public void update(boolean hard){}
}
