package logic.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import logic.entity.Category;
import logic.entity.Plan;

public class CategoryPane {
	public static GridPane getCategoryPane(SimpleObjectProperty<Plan> selectedPlan) {
		GridPane catPane = new GridPane();
	    catPane.setAlignment(Pos.TOP_CENTER);
	    catPane.setMinHeight(640);
	    ColumnConstraints col = new ColumnConstraints();
	    col.setPercentWidth(100);
        col.setHalignment(HPos.CENTER);
	    catPane.getColumnConstraints().add(col);
		catPane.setAlignment(Pos.TOP_CENTER);
		catPane.setStyle("-fx-alignment: center;");
	    List<Label> labels = new ArrayList<Label>();
	    int count = 0;
	    ArrayList<Category> cats = (ArrayList<Category>) selectedPlan.getValue().getCategories();
	    for(Category cat : cats) {
	    	Label catLabel = new Label();
	        catLabel.getStyleClass().add("block");
	        catLabel.getStyleClass().add("block-cat");
			catLabel.setMaxWidth(300);
	    	
	        catLabel.setText(cat.getName());
	    	catPane.add(catLabel, 0, count);
	    	catLabel.setOnMouseClicked(event ->{
	    		for(Label label : labels) {
	    			label.getStyleClass().clear();
	    			label.getStyleClass().add("block");
	    			label.getStyleClass().add("block-cat");
	    			label.setMaxWidth(300);
	    		}
	    		catLabel.getStyleClass().add("selected-block");
	    		Gui.setSelectedCategory(cat);
	    	});
	    	labels.add(catLabel);
	    	count++;
	    }
	    return catPane;
	}
}
