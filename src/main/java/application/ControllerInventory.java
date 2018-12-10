package application;
import java.net.URL;
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

	private Inventory inventory= new  Inventory();
	String storeName;
	int jour; 
	private Item[] items;
	List<String> descriptif = new ArrayList<String>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.print("initiatlisation du controller");
		   
		System.out.print(" init "+inventory.getItems().length);
		
		
		for(int i =0;i<inventory.getItems().length;i++) {
			descriptif.add(inventory.getItems()[i].getName()+ " Q:"+inventory.getItems()[i].getQuality()+ " S:"+inventory.getItems()[i].getSellIn());
		}
		
		ObservableList<String> collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
		
	}
	
	
	@FXML
	private void ListenerButton() {	

		System.out.print("\n listener "+inventory.getItems().length);
		inventory.updateQuality();		
		System.out.print("\n Jour suivant "+inventory.getItems()[0].getQuality());
		
		descriptif.removeAll(descriptif);
		System.out.print("\n descriptif"+descriptif.size());
		for(int i =0;i<inventory.getItems().length;i++) {
			descriptif.add(inventory.getItems()[i].getName()+ " Q:"+inventory.getItems()[i].getQuality()+ " S:"+inventory.getItems()[i].getSellIn());
		}
		ObservableList<String>  collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
		
		
		
	}
	@FXML
	private void ListenerList() {		
		/*
		int index= TableController.getSelectionModel().getSelectedIndex();
		Item[] items =inventory.getItems();		
		List<Item> listitem=Arrays.asList(items);
		*/
		System.out.println("Listener list ");
		
		
		
	}
	
}