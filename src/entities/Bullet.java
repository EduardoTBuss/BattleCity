package entities;

import world.Map;
import java.util.List;

public class Bullet extends Entity {

    private int dx, dy;
    private Map map;
    private List<Entity> entities;

    public Bullet(int x, int y, int dx, int dy, Map map, List<Entity> entities){
        super(x, y);
        this.dx = dx;
        this.dy = dy;
        this.map = map;
        this.entities = entities;
    }

    @Override
    public void update(boolean hard){
        x += dx;
        y += dy;

        if (x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()){
            destroyed = true;
            return;
        }

        for (Entity block : List.copyOf(map.get(x, y))){
            if (block == this) continue;

            if (block.blocksBullet()){
                if (block.destroyOnShot()){
                    block.destroy();
                    map.remove(block);
                }
                destroy();
                return;
            }
        }

        for (Entity e : List.copyOf(entities)){
            if (e == this) continue;
            if (e.getX() != x || e.getY() != y) continue;

            if (e.blocksBullet()){
                if (e.destroyOnShot()) {
                    if (e instanceof Tank) {
                        ((Tank) e).damage();
                    } else {
                        e.destroy();
                    }
                }
                destroy();
                return;
            }
        }
    }

    @Override
    public boolean blocksMovement(){ return false; }
    @Override
    public boolean blocksBullet(){ return false; }
    @Override
    public boolean destroyOnShot(){ return false; }
}
