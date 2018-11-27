package edu.insightr.gildedrose;

public class Conjured_mana extends Item {

    public Conjured_mana(String name, int sellIn, int quality)
    {
        super(name, sellIn, quality);

    }

    public void updateQuality()
    {
        if(getQuality() < 50)
        {
            if(getQuality()>1)
            {
                setQuality(getQuality() - 2);
            }
        }
    }
}
