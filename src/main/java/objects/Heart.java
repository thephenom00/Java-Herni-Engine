package objects;
import static utilz.Constants.ObjectConstants.*;
import static utilz.Size.*;

public class Heart extends Object{
    private static final int HEART_HITBOX_WIDTH = 12;
    private static final int HEART_HITBOX_HEIGHT = 10;
    public static final int HEART_XOFFSET = 7;
    public static final int HEART_YOFFSET = 3;
    public Heart(float x, float y) {
        super(x, y, HEART_WIDTH, HEART_HEIGHT, HEART);
        createHitbox(x, y, HEART_HITBOX_WIDTH * SCALE, HEART_HITBOX_HEIGHT * SCALE);
    }

    public void update() {
        updateAnimationTick();
    }
}