package org.ui;

import org.engine.Renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Database
{
    public Item empty;
    public Item pistol;
    public Item pack;

    public ArrayList<BufferedImage> itemImages = new ArrayList<>();

    public WeaponDatabase infoData;

    public void CreateItems()
    {
        infoData = new WeaponDatabase();
        infoData.CreateWeaponData();

        GetImages();

        empty = new Item
        (
                "Empty",
                "Nothing",
                0,
                0,
                itemImages.get(0),
                Item.ItemType.Misc,
                Item.ItemEffect.Equip,
                0.0f,
                infoData.empty
        );

        pistol = new Item
        (
                "Pistol",
                "A Small Weapon",
                1,
                1,
                itemImages.get(1),
                Item.ItemType.Weapon,
                Item.ItemEffect.Equip,
                0.0f,
                infoData.pistol
        );
        pack = new Item(

                "HealthPack",
                "a health pack",
                2,
                2,
                itemImages.get(2),
                Item.ItemType.Special,
                Item.ItemEffect.Heal,
                50.0f,
                infoData.empty

        );
    }

    public void GetImages()
    {
        try
        {
            itemImages.add(Renderer.LoadImage("/resources/sprites/Blank.png"));
            itemImages.add(Renderer.LoadImage("/resources/sprites/Pistol_Side.png"));
            itemImages.add(Renderer.LoadImage("/resources/sprites/HealthPack.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
