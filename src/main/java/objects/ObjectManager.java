package objects;

import entities.Pig;
import entities.Player;
import gameStates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.PIG_HEIGHT;
import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {
    private Player player;

    //Diamonds
    private BufferedImage[][] diamondArray;
    private static ArrayList<Diamond> diamonds = new ArrayList<>();
    public int numberOfDiamondsToTake;

    // Hearts
    private BufferedImage[][] heartArray;
    private static ArrayList<Heart> hearts = new ArrayList<>();

    public ObjectManager (Player player) {
        this.player = player;

        //Hearts
        loadHeartsImg();
        getHeartsFromPng();

        // Diamonds
        loadDiamondImg();
        getDiamondsFromPng();
    }

    public void draw(Graphics g) {
        drawDiamonds(g);
        drawHearts(g);
    }

    public void update() {
        for (Heart oneHeart : hearts) {
            if(oneHeart.isCollected == false)
                oneHeart.update();
        }

        for (Diamond oneDiamond : diamonds)
            if(oneDiamond.isCollected == false)
                oneDiamond.update();
    }

    private void getHeartsFromPng() {
        hearts = LoadSave.GetHearts();
    }

    private void getDiamondsFromPng() {
        diamonds = LoadSave.GetDiamonds();
        numberOfDiamondsToTake = diamonds.size();
    }

    private void loadHeartsImg() {
        heartArray = new BufferedImage[1][8];
        BufferedImage heartSprite = LoadSave.GetSpriteAtlas(LoadSave.HEART_SPRITE);
        for (int i = 0; i < heartArray[0].length; i++)
            heartArray[0][i] = heartSprite.getSubimage(i * HEART_WIDTH_DEFAULT, 0, HEART_WIDTH_DEFAULT, HEART_HEIGHT_DEFAULT);
    }

    private void loadDiamondImg() {
        diamondArray = new BufferedImage[1][10];
        BufferedImage diamondSprite = LoadSave.GetSpriteAtlas(LoadSave.DIAMOND_SPRITE);
        for (int i = 0; i < diamondArray[0].length; i++)
            diamondArray[0][i] = diamondSprite.getSubimage(i * DIAMOND_WIDTH_DEFAULT, 0, DIAMOND_WIDTH_DEFAULT, DIAMOND_HEIGHT_DEFAULT);
    }

    private void drawHearts(Graphics g) {
        for (Heart oneHeart : hearts) {
            if(oneHeart.isCollected == false) {
                g.drawImage(heartArray[0][oneHeart.getObjectIndex()],
                        (int) oneHeart.hitbox.x - Heart.HEART_XOFFSET,
                        (int) oneHeart.hitbox.y - Heart.HEART_YOFFSET + 10,
                        HEART_WIDTH,
                        HEART_HEIGHT,
                        null);
            }

        }
    }

    public void drawDiamonds(Graphics g) {
        for (Diamond oneDiamond : diamonds) {
            if(oneDiamond.isCollected == false) {
                g.drawImage(diamondArray[0][oneDiamond.getObjectIndex()],
                        (int) oneDiamond.hitbox.x - Diamond.DIAMOND_XOFFSET,
                        (int) oneDiamond.hitbox.y - Diamond.DIAMOND_YOFFSET + 10,
                        DIAMOND_WIDTH,
                        DIAMOND_HEIGHT,
                        null);
            }

        }
    }

    private void drawHeartHitbox(Graphics g) {
        for (Heart oneHeart : hearts) {
            if(oneHeart.isCollected == false)
                oneHeart.drawHitbox(g);
        }
    }


    private void drawDiamondHitbox(Graphics g) {
        for (Diamond oneDiamond: diamonds) {
            if(oneDiamond.isCollected == false)
                oneDiamond.drawHitbox(g);
        }
    }

    public void checkHeartCollected(Rectangle2D playerHitbox) {
        for (Heart oneHeart: hearts) {
            if (playerHitbox.intersects(oneHeart.hitbox) && !oneHeart.isCollected) {
                oneHeart.isCollected = true;
                player.addLife();
            }
        }
    }



    public void checkDiamondCollected(Rectangle2D.Float playerHitbox) {
        for (Diamond oneDiamond : diamonds) {
            if (playerHitbox.intersects(oneDiamond.hitbox) && !oneDiamond.isCollected) {
                oneDiamond.isCollected = true;
                numberOfDiamondsToTake--;
            }
        }
    }

    public void resetObjects() {
        for (Diamond oneDiamond : diamonds) {
            oneDiamond.isCollected = false;
        }
        numberOfDiamondsToTake = diamonds.size();

        for (Heart oneHeart : hearts) {
            oneHeart.isCollected = false;
        }

    }

}

