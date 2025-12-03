package logic;

import entities.*;
import world.Map;
import java.util.List;
import java.util.Random;

public class Spawner {

    public static void spawnEnemies(List<EnemyTank> enemies, List<Entity> entities, Map map, int count){
        Random r = new Random();

        for (int i = 0; i < count; i++){

            int x, y;

            while (true){
                x = r.nextInt(map.getWidth());
                y = r.nextInt(map.getHeight());

                boolean ok = true;
                for (Entity e : map.get(x, y))
                    if (e.blocksMovement()) ok = false;

                if (ok) break;
            }

            EnemyTank e = new EnemyTank(x, y, entities, map);
            enemies.add(e);
            entities.add(e);
        }
    }
}
