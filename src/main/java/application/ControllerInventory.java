package application;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInventory {
	@FXML
	TextField nameTxt;	
	@FXML
	TextArea commentsTxt;
	@FXML
	TextArea marksTxt;
	@FXML
	ListView <Item> table_controller;
	@FXML
	TableColumn name_controller;
	@FXML
	TableColumn sellin_controller;
	@FXML
	TableColumn quality_controller;
	@FXML
	Button button_controller;
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
		labelError.setText("Pour la modification ou l'ajout entrez une date");
		manager= new DBManager();
		List<String> gvalues= new ArrayList<String>();
		gvalues.add("male");
		gvalues.add("female");
		ObservableList<String> gender= FXCollections.observableArrayList(gvalues);
		genderTxt.setItems(gender);
		fetchStudents();
		
			//final URL imageURL = getClass().getResource("Hieronymus_Bosch.png");  
			//final Image image = new Image(imageURL.toExternalForm());
		final String imageURI = new File("C:\\Users\\MERLIN\\eclipse-workspace\\projet1\\res\\Woman_in_front_of_the_sun.jpg").toURI().toString(); 
		final Image image = new Image(imageURI);
		image1.setImage(image);
		
	}



	@FXML
	private void ListenerButton() {	
		int index= listInventory.getSelectionModel().getSelectedIndex();
		List<Integer> a =manager.loadStudentsID();
		if(birthTxt.getValue()!=null) {
		manager.ModifyStudent(nameTxt.getText(),genderTxt.getValue(),birthTxt.getValue(),marksTxt.getText(),commentsTxt.getText(),a.get(index));}
		else {labelError.setText("Veuillez entrer une date de naissance");}
		fetchStudents();
	}
		@FXML
	private void UpgradeButton() {
		inventory.updateQuality();
		ListenerList();
	}
	@FXML
	private void ListenerList() {
		
		int index= listView1.getSelectionModel().getSelectedIndex();
		Item[] items =inventory.getItems();		
		List<Item> nom=Arrays.asList(items);
		//System.out.println(b.get(index));
		
		
	}
}