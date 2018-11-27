package edu.insightr.gildedrose;

import jdk.internal.jline.internal.TestAccessible;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
    protected  Inventory inv;
    Item[] itemsList;

    @Before
    public void setUp() {
        inv = new Inventory();
        itemsList = inv.getItems();
    }

    @After
    public void tearDown() {

    }

    public void assertEquals(int a, int b)
    {

    }

    @Test
    public void testUpdateQualityWhenSellInFinished() throws Exception{
        inv.updateQuality();
        for(int i = 0; i < itemsList.length; i++){
            if(itemsList[i].getName()=="Sulfuras, Hand of Ragnaros"){
                assertEquals(80, itemsList[i].getQuality());
            }
            if(itemsList[i].getName()=="+5 Dexterity Vest"){
                itemsList[i].setSellIn(-1);
                inv.updateQuality();
                assertEquals(17, itemsList[i].getQuality());
            }
            if(itemsList[i].getName()=="Elixir of the Mongoose"){
                itemsList[i].setSellIn(-1);
                inv.updateQuality();
                assertEquals(3, itemsList[i].getQuality());
            }
        }
    }

    @Test
    public void testQualityNeverNegative() throws Exception{
        for(int i = 0; i < itemsList.length; i++){
            if(itemsList[i].getName()=="+5 Dexterity Vest"){
                for(int j = 0; j<25; j++){
                    inv.updateQuality();
                }
                assertEquals(0, itemsList[i].getQuality());
            }
            if (itemsList[i].getName()=="Elixir of the Mongoose"){
                assertEquals(0, itemsList[i].getQuality());
            }
            if(itemsList[i].getName()=="Conjured Mana Cake"){
                assertEquals(0, itemsList[i].getQuality());
            }
        }
    }

    @Test
    public void testAgedBrieQuality() throws Exception{
        itemsList[1].setSellIn(3);
        inv.updateQuality();
        assertEquals(1,itemsList[1].getQuality());
        //itemsList[1].setSellIn(4);
        inv.updateQuality();
        assertEquals(2,itemsList[1].getQuality());
    }

    @Test
    public void testQualityNeverMoreThan50() throws Exception{
        for(int i = 0; i < 75; i++){
            inv.updateQuality();
        }
        assertEquals(50, itemsList[1].getQuality());
    }

    @Test
    public void testSulfuras() throws Exception{
        //test the SellIn var
        itemsList[3].setSellIn(4);
        inv.updateQuality();
        assertEquals(0, itemsList[3].getSellIn());

        //test the Quality var
        for(int i=0; i < 100; i++){
            inv.updateQuality();
        }
        assertEquals(80, itemsList[3].getQuality());
    }

    @Test
    public void testBackstagePasses() throws Exception{
        //test if SellIn <= 10
        itemsList[4].setSellIn(10);
        inv.updateQuality();
        assertEquals(22, itemsList[4].getQuality());

        //test if sellIn <= 5
        itemsList[4].setSellIn(5);
        inv.updateQuality();
        assertEquals(25, itemsList[4].getQuality());

        //test if SellIn < 0
        itemsList[4].setSellIn(-1);
        inv.updateQuality();
        assertEquals(0, itemsList[4].getQuality());
    }

    @Test
    public void testConjuredItems() throws Exception{
        inv.updateQuality();
        assertEquals(4, itemsList[5].getQuality());
        itemsList[5].setSellIn(-1);
        inv.updateQuality();
        assertEquals(0, itemsList[5].getQuality());
    }
}
