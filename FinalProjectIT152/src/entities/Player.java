
package entities;

import static utilz.Constant.Directions.*;
import static utilz.Constant.PlayerConstants.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class Player extends Entity {
	private float gravity = 0.3f;  // Gravity force pulling the player down
	private float yVelocity = 0;   // Vertical velocity
	private boolean isOnGround = false; // Whether the player is standing on the platform
	
    private BufferedImage img, img2, img3, platImg, aniImg, jumpImg, runImg, sungkitImg, downImg;
    private BufferedImage[] idleAni, pugayAni, strikeAni, jumpAni, runAni, sungkitAni, downAni;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int aniPugayTick2, aniPugayIndex2, aniPugaySpeed2 = 45;
    private int aniTick3, aniIndex3, aniSpeed3 = 21;
    private int aniJumpTick, aniJumpIndex, aniJumpSpeed = 21;
    private int aniRunTick, aniRunIndex, aniRunSpeed= 21;
    private int aniSungkitTick, aniSungkitIndex, aniSungkitSpeed = 21;
    private int aniDoTick, aniDownIndex, aniDownSpeed = 21;
    private float playerSpeed = 2.0f;

    private int playerAction = JUMP;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false, sungkit = false;
	private Platform platform;
	
	private boolean crouching = false;
	private boolean jump = false;
	private boolean running = false;

    public Player(float x, float y, Platform platform) {
        super(x, y);
        this.platform = platform; // Assign the platform for collision checks
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        // Render based on the current player action (IDLE, RUNNING, or ATTACK)
    	if (crouching) {
            g.drawImage(downAni[aniDownIndex], (int) x, (int) y + 30, 150, 80, null);  // Adjust size for crouching
        } else {
        	switch(playerAction) {
        
            case RUNNING:
                // Render running animation
            	g.drawImage(runAni[aniRunIndex], (int) x, (int) y, 150, 110, null);
                break;
            
            case ATTACK:
                // Render attack animation (should be shown in a different layer or position if needed)
                g.drawImage(strikeAni[aniIndex3], (int) x, (int) y - 35, 150, 150, null); // Adjust position if necessary
                break;
                
            case JUMP:
            	g.drawImage(jumpAni[aniJumpIndex], (int) x, (int) y - 35, 150, 150, null);
            	break;
            	
            case SUNGKIT:
            	g.drawImage(sungkitAni[aniSungkitIndex], (int) x, (int) y - 35, 150, 150, null);

            case IDLE:
            	g.drawImage(idleAni[aniIndex], (int) x, (int) y, 150, 110, null);
                break;
                
            case DOWN:
            	g.drawImage(downAni[aniDownIndex], (int) x, (int) y, 150, 110, null);
                
            default:
                // Render idle animation
                g.drawImage(idleAni[aniIndex], (int) x, (int) y, 150, 110, null);
                break;
        	}
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
        
        aniJumpTick++;
        if (aniJumpTick >= aniJumpSpeed) {
            aniJumpTick = 0;
            aniJumpIndex++;
            if (aniJumpIndex >= GetSpriteAmount(playerAction)) {
                aniJumpIndex = 0;
                // Stop the attack animation once it completes
                if (jump) {
                    jump = false; // Reset attacking flag after attack finishes
                    playerAction = IDLE; // Change to idle after attack
                }
            }
        }
        
        aniRunTick++;
        if (aniRunTick >= aniRunSpeed) {
            aniRunTick = 0;
            aniRunIndex++;
            if (aniRunIndex >= GetSpriteAmount(playerAction)) {
                aniRunIndex = 0;
                // Stop the attack animation once it completes
                if (left || right) {
                    left = false; // Reset attacking flag after attack finishes
                    right = false;
                    playerAction = IDLE; // Change to idle after attack
                }
            }
        }
    }


    private void updatePos() {
        moving = false;

        // Horizontal movement
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        // Apply gravity if not on the ground
        if (!isOnGround) {
            yVelocity += gravity;  // Increase velocity due to gravity
        }

        float nextX = x;
        float nextY = y + yVelocity; // Add vertical velocity to position

        // Check collision with the top of the platform
        if (nextX + 64 > platform.getX() && nextX < platform.getX() + platform.getWidth()) {
            if (nextY + 64 > platform.getY() && y + 64 <= platform.getY()) {
                // Player is falling onto the platform
                nextY = platform.getY() - 64; // Position player on top of the platform
                yVelocity = 0;                // Stop vertical velocity
                isOnGround = true;            // Mark player as on the ground
            }
        } else {
            isOnGround = false; // Player is not on any platform
        }

        // Update position
        x = nextX;
        y = nextY;
    }


	private void setAnimation() {
	    if (attacking) {
	    	System.out.println("attack");
	        playerAction = ATTACK;  // Attack animation should always take priority
	    } else if(crouching) {
	    	playerAction = CROUCH;
	    }else if (moving) {
	    	System.out.println("running");
	        playerAction = RUNNING;  // Only run if not attacking
	    } else if(jump) {
	    	playerAction = JUMP;
	    }
	    else {
	    	System.out.println("iddle");
	        playerAction = IDLE;  // Default to idle animation when not moving or attacking
	    }
	}
    private void loadAnimations() {
    	
    	InputStream idle = getClass().getResourceAsStream("/IDLE_RIGHT.png");
        InputStream pugay = getClass().getResourceAsStream("/PUGAY_DEFAULT.png");
        InputStream strike = getClass().getResourceAsStream("/STRIKE.png");
        InputStream run = getClass().getResourceAsStream("/RUN.png");
        InputStream crouch = getClass().getResourceAsStream("/CROUCH.png");
        InputStream sungkit = getClass().getResourceAsStream("/SUNGKIT.png");
        InputStream up = getClass().getResourceAsStream("/JUMP.png");

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
        	BufferedImage img2 = ImageIO.read(pugay);
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
        	BufferedImage img3 = ImageIO.read(strike);
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
        	BufferedImage runImg = ImageIO.read(run);
            runAni = new BufferedImage[12]; // Ensure the correct number of frames here
            for (int z = 0; z < runAni.length; z++) {
                runAni[z] = runImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                run.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage jumpImg = ImageIO.read(up);
            jumpAni = new BufferedImage[12]; // Ensure the correct number of frames here
            for (int z = 0; z < jumpAni.length; z++) {
                jumpAni[z] = jumpImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                up.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage sungkitImg = ImageIO.read(sungkit);
            sungkitAni = new BufferedImage[7]; // Ensure the correct number of frames here
            for (int z = 0; z < sungkitAni.length; z++) {
                sungkitAni[z] = sungkitImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sungkit.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
        	BufferedImage downImg = ImageIO.read(crouch);
            downAni = new BufferedImage[1]; // Ensure the correct number of frames here
            for (int z = 0; z < downAni.length; z++) {
                downAni[z] = downImg.getSubimage(z * 64, 0, 64, 64);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                crouch.close();
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
    
    public void setJumped(boolean jump) {
    	this.jump = jump;
        if (jump && isOnGround) {
            yVelocity = -15;  // Apply an upward velocity to simulate jump
            isOnGround = false;
        }
    }

    public void setCrouch(boolean crouch) {
        this.crouching = crouch;
    }

    public boolean isCrouching() {
        return crouching;
    }
    
    public void setSungkit(boolean sungkit) {
    	if(sungkit) {
    		this.sungkit = true;
    		playerAction = SUNGKIT;
    		aniIndex = 0;
    	}
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
    
    public boolean isJump() {
    	return jump;
    }
    
    public void setJump(boolean jump) {
    	this.jump = jump;
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