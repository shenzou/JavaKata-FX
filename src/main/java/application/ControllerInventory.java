package application;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.table.TableColumn;

import edu.insightr.gildedrose.Aged_Brie;
import edu.insightr.gildedrose.Backstage;
import edu.insightr.gildedrose.Conjured_mana;
import edu.insightr.gildedrose.Dexterity_vest;
import edu.insightr.gildedrose.Elixir;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;
import edu.insightr.gildedrose.Sulfuras;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControllerInventory implements Initializable {

	@FXML
	ListView <String> TableController;
	@FXML
	TableColumn QualityColumn;
	@FXML
	TableColumn SellColumn;
	@FXML
	TableColumn NameColumn;
	@FXML
	Button UpdateButton;
	@FXML
	Label labelError;

	private Inventory inventory;
	String storeName;
	int jour; 
	private Item[] items;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		items = new Item[]{
		new Dexterity_vest("+5 Dexterity Vest", 10, 20),
        new Aged_Brie("Aged Brie", 2, 0),
        new Elixir("Elixir of the Mongoose", 5, 7),
        new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
        new Backstage("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Conjured_mana("Conjured Mana Cake", 3, 6)};
		// TODO Auto-generated method stub
		//labelError.setText("Pour la modification ou l'ajout entrez une date");
		System.out.print("initiatlisation du controller");
		
		List<String> descriptif = new ArrayList<String>();
		for(int i =0;i<items.length;i++) {
			descriptif.add(items[i].getName()+ " Q:"+items[i].getQuality()+ " S:"+items[i].getSellIn());
		}
		
		ObservableList<String> collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
	}
	
	
	@FXML
	private void ListenerButton() {	
		List<String> descriptif = new ArrayList<String>();
		for(int i =0;i<items.length;i++) {
			descriptif.add(items[i].getName()+ " Q:"+items[i].getQuality()+ " S:"+items[i].getSellIn());
		}
		
		ObservableList<String> collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
		
	}
	@FXML
	private void ListenerList() {		
		int index= TableController.getSelectionModel().getSelectedIndex();
		Item[] items =inventory.getItems();		
		List<Item> listitem=Arrays.asList(items);
		System.out.println("Listener list ");
		
		
		
	}
	
}