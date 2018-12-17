package edu.insightr.gildedrose;

/**
 * TODO
 *  - updateSellin
 * 
 * @author qunnamed
 */
public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Dexterity_vest("+5 Dexterity Vest", 10, 20),
                new Aged_Brie("Aged Brie", 2, 0),
                new Elixir("Elixir of the Mongoose", 5, 7),
                new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
                new Backstage("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Conjured_mana("Conjured Mana Cake", 3, 6),
                new Aged_Brie("Aged Brie 2", 3, 50),
                new Elixir("Mongoose elix", 5, 0)
        };

    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for(int i=0; i<items.length; i++)
        {
            items[i].updateQuality();
        }
        updateSellIn();
    }

    public void updateSellIn()
    {
        for(int i = 0; i < items.length; i++)
        {
            if(items[i].getSellIn()>0)
            {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }

  public  Item[] getItems() {
        return items;
    }
  
  public void addItem(Item item)
  {
	  Item[] temp = new Item[items.length+1];
	  for(int i=0; i<items.length; i++)
	  {
		  temp[i] = items[i];
	  }
	  temp[items.length] = item;
	  items = temp;
  }
  
  public void removeItem(int index)
  {
	  Item[] temp = new Item[items.length-1];
	  for(int i=0; i<index; i++)
	  {
		  temp[i] = items[i];
	  }
	  for(int i=index; i<items.length; i++)
	  {
		  temp[i] = items[i+1];
	  }
	  items = temp;
  }
}
