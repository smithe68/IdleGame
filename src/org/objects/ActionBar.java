package org.objects;

import org.engine.logic.Input;
import org.engine.logic.UIObject;
import org.engine.rendering.Renderer;
import org.inventory.InventoryManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ActionBar extends UIObject
{
    public ActionBar(double x, double y) {
        super(x, y);
    }

    @Override
    public void update()
    {
        if(Input.getKeyDown(KeyEvent.VK_1))
            if(InventoryManager.indicesOK(0))
                InventoryManager.useItem(0);

        if(Input.getKeyDown(KeyEvent.VK_2))
            if(InventoryManager.indicesOK(1))
                InventoryManager.useItem(1);

        if(Input.getKeyDown(KeyEvent.VK_3))
            if(InventoryManager.indicesOK(2))
                InventoryManager.useItem(2);

        if(Input.getKeyDown(KeyEvent.VK_4))
            if(InventoryManager.indicesOK(3))
                InventoryManager.useItem(3);
    }

    @Override
    public void render(Graphics2D g)
    {
        int barY = Renderer.getResolution().height - 25;

        // Draw Slots
        g.setColor(Color.white);
        for(int i = 0; i < InventoryManager.MAX_SIZE; i++)
            g.drawRect((i * 16) + 5, barY, width, height);

        // Draw Items
        for(int i = 0; i < InventoryManager.itemImages.size(); i++)
        {
            BufferedImage currImage = InventoryManager.itemImages.get(i);

            int centerY = barY - height / 2;
            int centerX = (i * 16 + 5) - width / 2;
            int imageHeight = currImage.getHeight();
            int imageWidth = currImage.getWidth();

            g.drawImage(currImage, centerX, centerY, imageWidth, imageHeight, null);
        }
    }
}
