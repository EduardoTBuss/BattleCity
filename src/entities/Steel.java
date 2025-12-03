package entities;

public class Steel extends Entity {

    public Steel(int x, int y){
        super(x, y);
    }

    @Override
    public void update(boolean hard){}

    @Override
    public boolean blocksMovement(){ return true; }

    @Override
    public boolean blocksBullet(){ return true; }

    @Override
    public boolean destroyOnShot(){ return false; }
}
