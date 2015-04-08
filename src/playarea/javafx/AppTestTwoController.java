package playarea.javafx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class AppTestTwoController {
	
	@FXML 
	private MenuButton fallcrest1;
    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;

    private final HashMap<String, ArrayList<Comparable<?>>> hm = new HashMap<>();

    @FXML
    void initialize() {

        hm.put("Location 1", new ArrayList<>(Arrays.asList(424.0, 120.0, "Location 1")));
        hm.put("Location 2", new ArrayList<>(Arrays.asList(466.0, 178.0, "Location 2")));
        hm.put("Location 3", new ArrayList<>(Arrays.asList(400.0, 276.0, "Location 3")));
        hm.put("Location 4", new ArrayList<>(Arrays.asList(326.0, 280.0, "Location 4")));
        hm.put("Location 5", new ArrayList<>(Arrays.asList(658.0, 118.0, "Location 5")));

        ObservableList<String> names = FXCollections.observableArrayList();
        Set<Entry<String, ArrayList<Comparable<?>>>> set = hm.entrySet();
        Iterator<Entry<String, ArrayList<Comparable<?>>>> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<String, ArrayList<Comparable<?>>> me = i.next();
            names.add((String) me.getKey());
        }
        Collections.sort(names);

        map_listview.setItems(names);
    }

    @FXML
    void listClicked(MouseEvent event) {
        String item = map_listview.getSelectionModel().getSelectedItem();
        List<Comparable<?>> list = hm.get(item);
        double mapWidth = 968;
        double mapHeight = 1248;
        double scrollH = (Double) list.get(0) / mapWidth;
        double scrollV = (Double) list.get(1) / mapHeight;
        final Timeline timeline = new Timeline();
        final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
        final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
