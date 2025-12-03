package utils;

import world.Map;
import entities.*;
import java.util.List;

public class Renderer {

    public static void draw(Map map, List<Entity> entities){

        int w = map.getWidth();
        int h = map.getHeight();

        char[][] screen = new char[h][w];

        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                List<Entity> cell = map.get(x,y);

                char c = ' ';

                for (Entity e : cell){
                    if (e instanceof Brick)  c = '#';
                    else if (e instanceof Bush)  c = '@';
                    else if (e instanceof Water) c = 'O';
                    else if (e instanceof Steel) c = '|';
                }

                screen[y][x] = c;
            }
        }

        for (Entity e : entities){
            char c =
                (e instanceof Bullet) ? '*' :
                (e instanceof EnemyTank) ? 'T' :
                (e instanceof PlayerTank) ? 'P' : '?';

            screen[e.getY()][e.getX()] = c;
        }

        clear();

        System.out.print("█".repeat(w + 2) + "\n");

        for (int y = 0; y < h; y++){
            System.out.print("█");
            for (int x = 0; x < w; x++){
                System.out.print(screen[y][x]);
            }
            System.out.print("█\n");
        }

        System.out.print("█".repeat(w + 2) + "\n");
    }

    private static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
