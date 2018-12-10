package edu.insightr.gildedrose;

public class Dexterity_vest extends Item {

    public Dexterity_vest(String name, int sellIn, int quality)
    {
        super(name, sellIn, quality);

    }

    public void updateQuality()
    {
        if(getQuality() > 0){

            setQuality(getQuality() - 1);
            if (getSellIn() == 0 ){
                setQuality(getQuality() - 1);
            }
        }
    }
}
