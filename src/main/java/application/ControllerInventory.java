package application;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javax.swing.table.TableColumn;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
		Lecteurjson();
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
	private List<Item> Lecteurjson() {
	String json1 = "{\"name\":\"aged 1\", \"sellIn\":2, \"quality\":0 }";
	String json2 = "[{\"name\":\"aged 1\", \"sellIn\":2, \"quality\":0 },{ \"name\":\"aged 2\", \"sellIn\":3, \"quality\":0},{\"name\":\"aged 3\",\"sellIn\":4,\"quality\":0 }]";	
	String jsonF="[{\"name\":\"aged 1\", \"sellIn\":2, \"quality\":0 },{\"name\":\"aged 2\", \"sellIn\":3, \"quality\":0},{\"name\":\"aged 3\", \"sellIn\":4, \"quality\":0 },{ \"name\":\"backstage passe to A concert\", \"sellIn\":15, \"quality\":20 },{ \"name\":\"backstage passe to B concert\", \"sellIn\":16, \"quality\":21},{ \"name\":\"backstage passe to C concert\", \"sellIn\":17, \"quality\":22 },{ \"name\":\"conjured mana 1\", \"sellIn\":3, \"quality\":6 },{\"name\":\"conjured mana 2\", \"sellIn\":4, \"quality\":7},{ \"name\":\"conjured mana 3\", \"sellIn\":5, \"quality\":8 },{ \"name\":\"+1 dexterity vest\", \"sellIn\":10, \"quality\":20 },		  {\"name\":\"+2 dexterity vest\", \"sellIn\":11, \"quality\":21},{\"name\":\"+3 dexterity vest\", \"sellIn\":12, \"quality\":22 },{ \"name\":\"Elixir A\", \"sellIn\":5, \"quality\":7 },{ \"name\":\"Elixir B\", \"sellIn\":6, \"quality\":8},{\"name\":\"Elixir C\",  \"sellIn\":7, \"quality\":9 },{ \"name\":\"Sukfuras A\", \"sellIn\":0, \"quality\":80},{ \"name\":\"Sukfuras B\", \"sellIn\":0, \"quality\":81},{\"name\":\"Sukfuras C\", \"sellIn\":0, \"quality\":82}]";
	 List<Item> inv=new ArrayList<Item>();
	
	ObjectMapper objectMapper = new ObjectMapper();	
	JsonNode jsonNode;
	
	try {
		/*jsonNode = objectMapper.readTree(json1);
		String color = jsonNode.get("name").asText(); 
		 System.out.println(color);
		 */
		
		 
		  ObjectMapper mapper = new ObjectMapper();
	      mapper.enable(SerializationFeature.INDENT_OUTPUT);
	       inv = new ArrayList<Item>();
	      inv =mapper.readValue(jsonF, List.class);
	      
	      for(int i =0;i<inv.size();i++) {
	    	  
	    	  System.out.println(" item "+inv.get(i));	 
	    	  //System.out.println( inv.get(i).getName());
	    	  //System.out.println("name "+inv.get(i).getQuality());
	    	 // String s=inv.get(i).toString();
	    	 /* jsonNode = objectMapper.readTree(s);
	    	  String name = jsonNode.get("name").asText(); 
	    	  System.out.println(" n info  "+name);
	    	  String sell = jsonNode.get("sellIn").asText(); 
	    	  System.out.println(" s info  "+sell);
	    	  String quality = jsonNode.get("quality").asText(); 
	    	  System.out.println(" q info  "+quality);
	    	  */
	      }
	      
		 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return inv;
	}
	
}