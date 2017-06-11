package org.objects;

import org.engine.CustomMathf;
import org.engine.Game;
import org.engine.ID;
import org.engine.Renderer;
import org.input.Input;
import org.ui.HUD;
import org.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject
{
    public static Player player;

    private BufferedImage image;
    private GameObject hudObj;
    private HUD hud;

    public float speed = 1.5f;

    private float rotation = 0;

    private float horiAxis = 0;
    private float vertAxis = 0;

    public Player(int x, int y, ID id)
    {
        super(x, y, id);

        player = this;

        try
        {
            // Get the Sprite for the Player
            image = Renderer.LoadImage("/resources/sprites/PlayerDude.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        hud = new HUD(x, y + 15, ID.HUD);
        hudObj = Game.Instantiate(hud);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,45,45);
    }

    public void tick(float deltaTime)
    {
        // UI

        if(hudObj != null)
        {
            hudObj.x = player.x;
            hudObj.y = player.y - 16;
        }

        // Movement

        x+= velX;
        y += velY;

        x = CustomMathf.Clamp(x,0, Renderer.gameWidth +761);
        y = CustomMathf.Clamp(y,0, Renderer.gameHeight +531);

        horiAxis = 0;
        vertAxis = 0;

        // Move Player Left
        if(Input.GetKey(KeyEvent.VK_A))
        {
            x -= speed;
            horiAxis = -1;
        }

        // Move Player Right
        if(Input.GetKey(KeyEvent.VK_D))
        {
            x += speed;
            horiAxis = 1;
        }

        // Move Player Up
        if(Input.GetKey(KeyEvent.VK_W))
        {
            y -= speed;
            vertAxis = 1;
        }

        // Move Player Down
        if(Input.GetKey(KeyEvent.VK_S))
        {
            y += speed;
            vertAxis = -1;
        }

        // Test Linecast
        if(Input.GetKeyDown(KeyEvent.VK_E))
        {
            Point thisPos = new Point((int)x, (int)y);
            Point endPos = new Point((int)x + 15, (int)y);
        }

        rotation = CustomMathf.NineAxisRotation(rotation, horiAxis, vertAxis, 3, deltaTime);

        collision();
    }

    private void collision()
    {
        for(int i = 0; i < World.gameObjects.size(); i++)
        {
            GameObject tempObject = World.gameObjects.get(i);

            if (tempObject.GetID() == ID.BasicZombie)
            {
                if( getBounds().intersects(tempObject.getBounds()))
                {
                    hud.HEALTH -= 1;
                }
            }
        }

        if(hud.HEALTH == 0)
        {
            System.exit(1);
        }
    }

    public void render(Graphics g)
    {
        if (id == ID.Player)
        {
            AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            at.rotate(Math.toRadians(rotation), image.getWidth() / 2, image.getHeight() / 2);
            Graphics2D g2d = (Graphics2D) g;

            // Draw the Player's Sprite
            g2d.drawImage(image, at, null);
        }
    }
}