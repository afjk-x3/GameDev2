package entities;

import static utilz.Constant.Directions.*;
import static utilz.Constant.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity {
    private BufferedImage img, img2, img3, platImg;
    private BufferedImage[] idleAni, pugayAni, strikeAni;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int aniTick2, aniIndex2, aniSpeed2 = 45;
    private int aniTick3, aniIndex3, aniSpeed3 = 21;
    private float playerSpeed = 2.0f;

    private int playerAction = ATTACK;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        // Render based on the current player action (IDLE, RUNNING, or ATTACK)
        switch(playerAction) {
            case RUNNING:
                // Render running animation
                g.drawImage(idleAni[aniIndex], (int) x, (int) y, 150, 110, null);
                break;
            
            case ATTACK:
                // Render attack animation (should be shown in a different layer or position if needed)
                g.drawImage(strikeAni[aniIndex3], (int) x, (int) y - 35, 150, 150, null); // Adjust position if necessary
                break;

            case IDLE:
            default:
                // Render idle animation
                g.drawImage(idleAni[aniIndex], (int) x, (int) y, 150, 110, null);
                break;
        }
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;

                // If attack animation is done, stop attacking and set playerAction back to idle or running
                if (playerAction == ATTACK) {
                    attacking = false; // Reset attacking flag
                    playerAction = IDLE; // Change action to idle after attack
                }
            }
        }

        aniTick3++;
        if (aniTick3 >= aniSpeed3) {
            aniTick3 = 0;
            aniIndex3++;
            if (aniIndex3 >= GetSpriteAmount(playerAction)) {
                aniIndex3 = 0;
                // Stop the attack animation once it completes
                if (attacking) {
                    attacking = false; // Reset attacking flag after attack finishes
                    playerAction = IDLE; // Change to idle after attack
                }
            }
        }
    }


    private void updatePos() {
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

	private void setAnimation() {
	    if (attacking) {
	    	System.out.println("attack");
	        playerAction = ATTACK;  // Attack animation should always take priority
	    } else if (moving) {
	    	System.out.println("running");
	        playerAction = RUNNING;  // Only run if not attacking
	    } else {
	    	System.out.println("iddle");
	        playerAction = IDLE;  // Default to idle animation when not moving or attacking
	    }
	}
    private void loadAnimations() {
        InputStream idle = getClass().getResourceAsStream("/IDLE_RIGHT.png");
        InputStream pugay = getClass().getResourceAsStream("/PUGAY_DEFAULT.png");
        InputStream strike = getClass().getResourceAsStream("/STRIKE.png");
        InputStream platform = getClass().getResourceAsStream("/PLAT.png");

        try {
            BufferedImage img = ImageIO.read(idle);
            idleAni = new BufferedImage[6]; // Ensure the correct number of frames here
            for (int i = 0; i < idleAni.length; i++) {
                idleAni[i] = img.getSubimage(i * 64, 0, 64, 50);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                idle.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            img2 = ImageIO.read(pugay);
            pugayAni = new BufferedImage[8]; // Ensure the correct number of frames here
            for (int j = 0; j < pugayAni.length; j++) {
                pugayAni[j] = img2.getSubimage(j * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pugay.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            img3 = ImageIO.read(strike);
            strikeAni = new BufferedImage[7]; // Ensure the correct number of frames here
            for (int z = 0; z < strikeAni.length; z++) {
                strikeAni[z] = img3.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                strike.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            platImg = ImageIO.read(platform);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                platform.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        if (attacking) {
            this.attacking = true;
            playerAction = ATTACK; // Switch to attack animation immediately
            aniIndex = 0; // Reset animation frame for attack
        }
    }


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
