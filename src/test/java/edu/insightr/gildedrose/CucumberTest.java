package edu.insightr.gildedrose;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CucumberTest {

    @Test
    public void BrieQuality()
    {
        Inventory inv = new Inventory();
        Item[] listeItems = inv.getItems();
        Item brie = listeItems[2];
        assertThat(brie.getName(), is("Aged Brie"));
        assertThat(brie.getQuality(), is(0));
        inv.updateQuality();
        assertThat(brie.getQuality(), is(1));
    }

    @Test
    public void DateCheck()
    {
        Inventory inv = new Inventory();
        Item[] listeItems = inv.getItems();
        Item itemConjured = listeItems[3];
        assertThat(itemConjured.getSellIn(), is(0));
        assertThat(itemConjured.getName(), is("+5 Dexterity Vest"));
        assertThat(itemConjured.getQuality(), is(20));
        inv.updateQuality();
        assertThat(itemConjured.getQuality(), is(18));
    }

    @Test
    public void QualityMoreThan50()
    {
        Inventory inv = new Inventory();
        Item[] listeItems = inv.getItems();

        Item item = listeItems[6];
        assertThat(item.getName(), is("Aged Brie 2"));
        assertThat(item.getQuality(), is(50));
        inv.updateQuality();
        assertThat(item.getQuality(), is(50));
    }

    @Test
    public void QualityLessThanZero()
    {
        Inventory inv = new Inventory();
        Item[] listeItems = inv.getItems();

        Item item = listeItems[4];
        assertThat(item.getName(), is("Mongoose elix"));
        assertThat(item.getQuality(), is(0));
        inv.updateQuality();
        assertThat(item.getQuality(), is(0));
    }

    @Test
    public void SulfurasNoChange()
    {
        Inventory inv = new Inventory();
        Item[] listeItems = inv.getItems();

        Item item = listeItems[6];
        assertThat(item.getName(), is("Sulfuras Hand of Ragnaros"));
        assertThat(item.getQuality(), is(80));
        assertThat(item.getSellIn(), is(20));
        inv.updateQuality();
        assertThat(item.getQuality(), is(80));
        assertThat(item.getSellIn(), is(20));
    }
}
