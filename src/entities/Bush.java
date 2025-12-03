package entities;

public class Bush extends Entity {

    public Bush(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(boolean hard) {}

    public boolean blocksMovement() { return false; }
    public boolean blocksBullet() { return false; }
    public boolean destroyOnShot() { return false; }
    public char icon() { return '@'; }
}
