package application;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class ControllerInventory implements Initializable {

	@FXML
	ListView <String> TableController;
	@FXML
	ListView <String> TableController1;
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
	@FXML
	PieChart pieChart;
	@FXML 
	Button readJSON;
	@FXML
	Button buy;
	@FXML
	Button sell;

	private Inventory inventory= new  Inventory();
	private Inventory invPNJ = new Inventory();
	String storeName;
	int jour; 
	private Item[] items;
	List<String> descriptif = new ArrayList<String>();
	List<String> descriptifPNJ = new ArrayList<String>();
	private List<Item> historique = new ArrayList<Item>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		updateList();


		//Lecteurjson();
	}
	
	private void updateList()
	{
		System.out.print("initiatlisation du controller");
		   
		System.out.print(" init "+inventory.getItems().length);

		int totalBrie =0;
		int totalBackstage=0;
		int totalConjured=0;
		int totalDexterity =0 ;
		int totalElixir = 0;
		int totalSulfuras =0;
		
		for(int i =0;i<inventory.getItems().length;i++) {
			descriptif.add(inventory.getItems()[i].getName()+ "\nQuality:"+inventory.getItems()[i].getQuality()+ "\nSell:"+inventory.getItems()[i].getSellIn());

			if(inventory.getItems()[i] instanceof Aged_Brie )
			{
				totalBrie++;
			}
			if(inventory.getItems()[i] instanceof Backstage )
			{
				totalBackstage++;
			}
			if(inventory.getItems()[i] instanceof Conjured_mana )
			{
				totalConjured++;
			}
			if(inventory.getItems()[i] instanceof Dexterity_vest )
			{
				totalDexterity++;
			}
			if(inventory.getItems()[i] instanceof Elixir )
			{
				totalElixir++;
			}
			if(inventory.getItems()[i] instanceof Sulfuras )
			{
				totalSulfuras++;
			}
		}

		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Aged_Brie", totalBrie),
						new PieChart.Data("Backstage", totalBackstage),
						new PieChart.Data("Conjured_Mana", totalConjured),
						new PieChart.Data("Dexterity_Vest", totalDexterity),
						new PieChart.Data("Elixir", totalElixir));
						new PieChart.Data("Sulfuras", totalSulfuras);
		final PieChart chart = new PieChart(pieChartData);
		pieChart.setTitle("Stock");

		pieChart.setData(pieChartData);

		ObservableList<String> collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
	}

	private void refreshItems()
	{
		
		System.out.print("initiatlisation du controller");
		   
		System.out.print(" init "+invPNJ.getItems().length);

		int totalBrie =0;
		int totalBackstage=0;
		int totalConjured=0;
		int totalDexterity =0 ;
		int totalElixir = 0;
		int totalSulfuras =0;
		
		for(int i =0;i<invPNJ.getItems().length;i++) {
			descriptifPNJ.add(invPNJ.getItems()[i].getName()+ "\nQuality:"+invPNJ.getItems()[i].getQuality()+ "\nSell:"+invPNJ.getItems()[i].getSellIn());

			if(invPNJ.getItems()[i] instanceof Aged_Brie )
			{
				totalBrie++;
			}
			if(invPNJ.getItems()[i] instanceof Backstage )
			{
				totalBackstage++;
			}
			if(invPNJ.getItems()[i] instanceof Conjured_mana )
			{
				totalConjured++;
			}
			if(invPNJ.getItems()[i] instanceof Dexterity_vest )
			{
				totalDexterity++;
			}
			if(invPNJ.getItems()[i] instanceof Elixir )
			{
				totalElixir++;
			}
			if(invPNJ.getItems()[i] instanceof Sulfuras )
			{
				totalSulfuras++;
			}
		}

		

		ObservableList<String> collection=FXCollections.observableArrayList(descriptifPNJ);
		TableController1.setItems(collection);
	}
	
	@FXML
	private void ListenerButton() {	

		System.out.print("\n listener "+inventory.getItems().length);
		inventory.updateQuality();		
		System.out.print("\n Jour suivant "+inventory.getItems()[0].getQuality());
		
		descriptif.removeAll(descriptif);
		System.out.print("\n descriptif"+descriptif.size());
		for(int i =0;i<inventory.getItems().length;i++) {
			descriptif.add(inventory.getItems()[i].getName()+ "\nQuality:"+inventory.getItems()[i].getQuality()+ "\nSell:"+inventory.getItems()[i].getSellIn());
		}
		ObservableList<String>  collection=FXCollections.observableArrayList(descriptif);
		TableController.setItems(collection);
		
		
		
	}
	
	@FXML
	private void ListenerJSON()
	{
		String reponse = Navigateur();
		System.out.print("\n chemin " + reponse.toString());
		try {
			Scanner in = new Scanner(new FileReader(reponse));
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()) {
			    sb.append(in.next());
			}
			in.close();
			String outString = sb.toString();
			System.out.println(outString);
			LecteurjsonPNJ(outString);
		}
		catch (Exception e)
		{
			System.out.println("Fichier introuvable");
		}
	}
	
	@FXML
	private void ListenerBuy()
	{
		int index = TableController1.getSelectionModel().getSelectedIndex();
		System.out.println(index);
		if(index!=-1)
		{
			inventory.addItem(invPNJ.getItems()[index]);
			invPNJ.removeItem(index);
		}
		updateList();
		refreshItems();
	}
	
	@FXML
	private void ListenerSell()
	{
		int index = TableController.getSelectionModel().getSelectedIndex();
		System.out.println(index);
		if(index!=-1)
		{
			invPNJ.addItem(inventory.getItems()[index]);
			inventory.removeItem(index);
		}
		updateList();
		refreshItems();
		
	}
	
	private String Navigateur() {
		FileSystemView vueSysteme = FileSystemView.getFileSystemView();  
		//récupération des répertoires 
		
		File home = vueSysteme.getHomeDirectory();  
		//création et affichage des JFileChooser 
		
		JFileChooser homeChooser = new JFileChooser(home); 
		homeChooser.showOpenDialog(null);
		String choice=homeChooser.getSelectedFile().getPath();
		
		return choice;
	}

	private void Achat(Item item) {
		Date date=new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int taille =inventory.getItems().length+1;
		Item [] nouvTab = new Item[taille];
		for(int i =0 ; i<taille-1;i++) {
			nouvTab[i]=inventory.getItems()[i];
		}
		
		// item.setDate(date);
		nouvTab[taille]=item;
		//placer nouvTab a la place de inventory.item

	}
	
	private void Vente(Item item) {
		
		Date date=new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		//item.setDate(date);
		historique.add(item);
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
	
	private List<Item> LecteurjsonPNJ(String jsonFile) {
	String jsonF="[{\"name\":\"aged 1\", \"sellIn\":2, \"quality\":0 },{\"name\":\"aged 2\", \"sellIn\":3, \"quality\":0},{\"name\":\"aged 3\", \"sellIn\":4, \"quality\":0 },{ \"name\":\"backstage passe to A concert\", \"sellIn\":15, \"quality\":20 },{ \"name\":\"backstage passe to B concert\", \"sellIn\":16, \"quality\":21},{ \"name\":\"backstage passe to C concert\", \"sellIn\":17, \"quality\":22 },{ \"name\":\"conjured mana 1\", \"sellIn\":3, \"quality\":6 },{\"name\":\"conjured mana 2\", \"sellIn\":4, \"quality\":7},{ \"name\":\"conjured mana 3\", \"sellIn\":5, \"quality\":8 },{ \"name\":\"+1 dexterity vest\", \"sellIn\":10, \"quality\":20 },		  {\"name\":\"+2 dexterity vest\", \"sellIn\":11, \"quality\":21},{\"name\":\"+3 dexterity vest\", \"sellIn\":12, \"quality\":22 },{ \"name\":\"Elixir A\", \"sellIn\":5, \"quality\":7 },{ \"name\":\"Elixir B\", \"sellIn\":6, \"quality\":8},{\"name\":\"Elixir C\",  \"sellIn\":7, \"quality\":9 },{ \"name\":\"Sukfuras A\", \"sellIn\":0, \"quality\":80},{ \"name\":\"Sukfuras B\", \"sellIn\":0, \"quality\":81},{\"name\":\"Sukfuras C\", \"sellIn\":0, \"quality\":82}]";
	 List<Item> inv=new ArrayList<Item>();
	 List<Item> itemf=new ArrayList<Item>();
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode jsonNode;

	try {

		  ObjectMapper mapper = new ObjectMapper();
	      mapper.enable(SerializationFeature.INDENT_OUTPUT);
	       inv = new ArrayList<Item>();
	      inv = mapper.readValue(jsonFile, List.class);

	      for(int i =0;i<inv.size();i++) {

	    	  System.out.println(" item "+inv.get(i));
	    	  String s= String.valueOf(inv.get(i));
	    	  System.out.println(" info  "+s);
	    	  String name="name=";
	    	 String sell=", sellIn=";
	    	String quality=", quality=";
	    	  System.out.println(" info  "+ s.indexOf(name));
	    	  String iname=s.substring(s.indexOf(name)+name.length(),s.indexOf(sell));
	    	  System.out.println(" name  "+ s.substring(s.indexOf(name)+name.length(),s.indexOf(sell)));
	    	  String isell= s.substring(s.indexOf(sell)+sell.length(),s.indexOf(quality));
	    	  System.out.println(" sell  "+ s.substring(s.indexOf(sell)+sell.length(),s.indexOf(quality)));
	    	 String iqual=s.substring(s.indexOf(quality)+quality.length(),s.length()-1);
	    	  System.out.println(" qual  "+ s.substring(s.indexOf(quality)+quality.length(),s.length()-1));



	    	  CharSequence cs1 = "aged";
	    	  CharSequence cs2="backstage";
	    	  CharSequence cs3="dexterity vest";
	    	  CharSequence cs4="Elixir";
	    	  CharSequence cs5="Sukfuras";
	    	  CharSequence cs6="conjured";
	    	  if(iname.contains(cs1)) {
	    		  Aged_Brie a=  new Aged_Brie(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  itemf.add(a);
	    		  invPNJ.addItem(a);
	    	  }

	    	  if(iname.contains(cs2)) {
	    		  Backstage a=  new Backstage(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  itemf.add(a);
	    		  invPNJ.addItem(a);
	    	  }

	    	  if(iname.contains(cs3)) {
	    		  Dexterity_vest a=  new Dexterity_vest(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  invPNJ.addItem(a);
	    	  }

	    	  if(iname.contains(cs4)) {
	    		  Elixir a=  new Elixir(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  itemf.add(a);
	    		  invPNJ.addItem(a);
	    	  }

	    	  if(iname.contains(cs5)) {
	    		  Sulfuras a=  new Sulfuras(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  itemf.add(a);
	    		  invPNJ.addItem(a);
	    	  }

	    	  if(iname.contains(cs6)) {
	    		  Conjured_mana a=  new Conjured_mana(iname, Integer.parseInt(isell),Integer.parseInt(iqual));
	    		  itemf.add(a);
	    		  invPNJ.addItem(a);
	    	  }
	      }
	      
	      refreshItems();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return itemf;
	}
	
}