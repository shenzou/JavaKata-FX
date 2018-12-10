package edu.insightr.gildedrose;

public class Aged_Brie extends Item{

    public Aged_Brie(String name, int sellIn, int quality)
    {
        super(name, sellIn, quality);
    }

    public void updateQuality()
    {
        //"Aged Brie" actually increases in Quality the older it gets
        if (this.getQuality() < 50) {
            this.setQuality(this.getQuality() + 1);
        }

        //Once the sell by date has passed, Quality degrades twice as fast
        if (this.getSellIn() <= 0) {
            if (this.getQuality() < 50) {
                this.setQuality(this.getQuality() + 1);
            }
        }
    }
}
