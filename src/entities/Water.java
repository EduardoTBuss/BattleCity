package entities;

public class Water extends Entity {

    public Water(int x, int y){
        super(x, y);
    }

    @Override
    public void update(boolean hard){}

    @Override
    public boolean blocksMovement(){ return true; }

    @Override
    public boolean blocksBullet(){ return false; }

    @Override
    public boolean destroyOnShot(){ return false; }
}
