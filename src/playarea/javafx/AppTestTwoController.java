package playarea.javafx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    void initialize() {

        ObservableList<MapLocale> locations = FXCollections.observableArrayList(
        		new MapLocale(1,"Location 1", 424, 120),
        		new MapLocale(2,"Location 2", 466, 178),
        		new MapLocale(3,"Location 3", 400, 276),
        		new MapLocale(4,"Location 4", 326, 280),
        		new MapLocale(5,"Location 5", 658, 118),
        		new MapLocale(6,"Location 6", 595, 158),
        		new MapLocale(7,"Location 7", 615, 204),
        		new MapLocale(8,"Location 8", 810, 193),
        		new MapLocale(9,"Location 9", 747, 405),
        		new MapLocale(10,"Location 10", 587, 298)
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
    	
		public MapLocale(int id, String name, int x, int y) {
			super();
			this.id = id;
			this.name = name;
			this.x = x;
			this.y = y;
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
    }
}
