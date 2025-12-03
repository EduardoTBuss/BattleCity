package entities;

import world.Map;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Tank {

    private Random r = new Random();

    private int moveCooldown = 0;
    private int shootCooldown = 0;

    private int steps = 0; 
    private int targetSteps = 0;

    public EnemyTank(int x, int y, List<Entity> entities, Map map){
        super(x, y, entities, map);
    }

    @Override
    public void update(boolean hard){
        if (moveCooldown > 0) moveCooldown--;
        if (shootCooldown > 0) shootCooldown--;

        if (hard){
            detectPlayerLine();
        } else {
            randomShoot();
        }

        aiMove();
    }


    private void detectPlayerLine(){
        List<Entity> copy = List.copyOf(entities);

        for (Entity e : copy){
            if (e instanceof PlayerTank){
                int px = e.getX();
                int py = e.getY();

                if (px == x){
                    if (py < y){ dx = 0; dy = -1; }
                    else{ dx = 0; dy = 1; }

                    if (shootCooldown == 0){
                        shoot();
                        shootCooldown = 25;
                    }
                }

                if (py == y){
                    if (px < x){ dx = -1; dy = 0; }
                    else{ dx = 1; dy = 0; }

                    if (shootCooldown == 0){
                        shoot();
                        shootCooldown = 25;
                    }
                }
            }
        }
    }

    private void randomShoot(){
        if (shootCooldown == 0){
            if (r.nextInt(5) == 0){
                shoot();
                shootCooldown = 25;
            }
        }
    }

    public void aiMove(){
        if (moveCooldown > 0) return;

        if (steps == 0){
            targetSteps = r.nextInt(5) + 3;
            int d = r.nextInt(4);

            if (d == 0){ dx = 1; dy = 0;  }
            if (d == 1){ dx = -1; dy = 0; }
            if (d == 2){ dx = 0; dy = 1;  }
            if (d == 3){ dx = 0; dy = -1; }
        }

        int oldX = x;
        int oldY = y;

        move(dx, dy);

        if (x == oldX && y == oldY){
            steps = 0;
            return;
        }

        steps++;

        if (steps >= targetSteps){
            steps = 0;
        }

        moveCooldown = 5;
    }
}
