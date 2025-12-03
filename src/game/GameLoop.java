package game;

import world.Map;

import entities.PlayerTank;
import entities.EnemyTank;
import entities.Entity;

import logic.Spawner;
import logic.Collision;

import utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameLoop{

    private Map map;
    private PlayerTank player;
    private InputHandler input = new InputHandler();
    private List<EnemyTank> enemies = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    private boolean running = true;
    private int width = 45, height = 20;
    private int tankCount = 3;
    public static int kills = 0;
    
    public static boolean hardMode = true;

    public GameLoop(){
        map = new Map(width, height);
        player = new PlayerTank(width / 2, height / 2, entities, map);

        entities.add(player);
        Spawner.spawnEnemies(enemies, entities, map, tankCount);
    }

    public void start(){
        while (running){

            Renderer.draw(map, entities);

            if (player.getHealth() <= 0){
                System.out.println("=== GAME OVER ===");
                return;
            }

            handlePlayer(input.poll());

            updateEntities();
            Collision.process(entities, map);

            moveEnemies();

            sleep(50);
        }
    }

    private void handlePlayer(char input){
        switch (input) {
            case 'w': 
                player.move(0, -1); 
            break;
            case 's': 
                player.move(0, 1); 
            break;
            case 'a': 
                player.move(-1, 0); 
            break;
            case 'd': 
                player.move(1, 0); 
            break;
            case ' ': 
                player.shoot(); 
            break;
            case 'q': 
                running = false; 
            break;
        }
    }

    private void updateEntities(){
        List<Entity> snapshot = new ArrayList<>(entities);

        for (Entity e : snapshot) {
            e.update(hardMode);
            if (e.isDestroyed()) {
                entities.remove(e);
            }
        }
    }

    private void moveEnemies(){
        for (EnemyTank e : enemies) e.aiMove();
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }
}
