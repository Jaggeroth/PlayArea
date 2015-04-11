package playarea.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class AppTestTwoController {
	
	@FXML 
	private MenuButton fallcrest1;
    @FXML
    private ListView<String> map_listview;
    @FXML
    private TableView<MapLocale> map_table;
    @FXML
    private TableColumn<MapLocale, Integer> idColumn;
    @FXML
    private TableColumn<MapLocale, String> nameColumn;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private Label location_detail; 

    @FXML
    void initialize() {
    	
        ObservableList<MapLocale> locations = FXCollections.observableArrayList(
        		new MapLocale(1,"Tower of Waiting", 424, 120,"Tower of Waiting description"),
        		new MapLocale(2,"Upper Quays", 466, 178,"Upper Quays description"),
        		new MapLocale(3,"Five-Arched Bridge", 400, 276,"Five-Arched Bridge description"),
        		new MapLocale(4,"Nentir Inn", 326, 280,"Nentir Inn description"),
        		new MapLocale(5,"Knight's Gate", 658, 118,"Knight's Gate description"),
        		new MapLocale(6,"Silver Unicorn Inn", 595, 158,"Silver Unicorn Inn description"),
        		new MapLocale(7,"Half Moon Trading House", 615, 204,"Half Moon Trading House description"),
        		new MapLocale(8,"Moonstone Keep", 810, 193,"Moonstone Keep description"),
        		new MapLocale(9,"Tombwood", 747, 405,"Tombwood description"),
        		new MapLocale(10,"House of the Sun", 587, 298,"House of the Sun description"),
        		new MapLocale(11,"House Azaer", 511, 310,"House Azaer description"),
        		new MapLocale(12,"The Nentir Falls", 302, 435,"The Nentir Falls description"),
        		new MapLocale(13,"Temple of Erathis", 561, 404,"Temple of Erathis description"),
        		new MapLocale(14,"The Bluffs", 511, 527,""),
        		new MapLocale(15,"The Catacombs", 645, 558,""),
        		new MapLocale(16,"Moonsong Temple", 702, 593,""),
        		new MapLocale(17,"Fallcrest Stables", 800, 549,""),
        		new MapLocale(18,"Wizard's Gate", 900, 602,""),
        		new MapLocale(19,"Naerumar's Imports", 855, 687,""),
        		new MapLocale(20,"Kamroth Estate", 760, 763,""),
        		new MapLocale(21,"Moonwash Falls", 679, 749,""),
        		new MapLocale(22,"Septarch's Tower", 683, 846,""),
        		new MapLocale(23,"Blue Moon Alehouse", 559, 860,""),
        		new MapLocale(24,"Teldorthan's Arms", 448, 934,""),
        		new MapLocale(25,"King's Gate", 564, 1003,""),
        		new MapLocale(26,"The Markey Green", 431, 673,""),
        		new MapLocale(27,"Sandercot Provisioners", 467, 618,""),
        		new MapLocale(28,"Lucky Gnome Taphouse", 419, 703,""),
        		new MapLocale(29,"Lower Quays", 286, 707,"")
        		);
        idColumn.setCellValueFactory(new PropertyValueFactory<MapLocale,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<MapLocale,String>("name"));
        map_table.setItems(locations);
        
    }

    @FXML
    void listClicked(MouseEvent event) {
        double mapWidth = 968;
        double mapHeight = 1248;
    	 TableViewSelectionModel<MapLocale> sm = map_table.getSelectionModel();
    	 if (sm.getSelectedIndex()>=0) {
    		 MapLocale loc = sm.getSelectedItem();
    		 double scrollH = loc.getX() / mapWidth;
    		 double scrollV = loc.getY() / mapHeight;
    		 location_detail.setText(loc.getDescription());
    		 final Timeline timeline = new Timeline();
    		 final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
    		 final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
    		 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
    		 timeline.getKeyFrames().add(kf);
    		 timeline.play();
    	 }
    }
    
    public class MapLocale {
    	private int id;
    	private String name;
    	private int x;
    	private int y;
    	private String description;
    	
		public MapLocale(int id, String name, int x, int y, String description) {
			super();
			this.id = id;
			this.name = name;
			this.x = x;
			this.y = y;
			this.description = description;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
    }
}
