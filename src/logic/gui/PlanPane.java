package logic.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Plan;

public class PlanPane {
	private PlanPane() {
	    throw new IllegalStateException("Utility class");
	}
	static List<Plan> plans;
	public static GridPane getPlanPane() {
		GridPane planPane = new GridPane();
        planPane.setAlignment(Pos.TOP_CENTER);
        planPane.getStyleClass().add("borders");
        planPane.getStyleClass().add("outside");
        planPane.setStyle("-fx-alignment: center;");
        if(plans.isEmpty()) {
	        plans.add(new Plan("Plan 1", 1, new ArrayList<Category>()));
	
	    	plans.get(0).addCategory(new Category("GE's",new ArrayList<Course>(), false));
	    	plans.get(0).addCategory(new Category("Major Courses", new ArrayList<Course>(), false));
	        plans.add(new Plan("Plan 2", 2, new ArrayList<Category>()));
        }
        List<Label> labels = new ArrayList<>();
        int count = 0;
        for(Plan plan : plans) {
        	Label planLabel = new Label();
            planLabel.getStyleClass().add("block");
            planLabel.getStyleClass().add("block-plan");
        	
        	planLabel.setText(plan.getName());
        	planPane.add(planLabel, 0, count);
        	planLabel.setOnMouseClicked(event ->{
        		for(Label label : labels) {
        			label.getStyleClass().clear();
        			label.getStyleClass().add("block");
        			label.getStyleClass().add("block-plan");
        		}
                planLabel.getStyleClass().add("selected-block");
                Gui.setSelectPlan(plan);
        	});
        	labels.add(planLabel);
        	count++;
        }
		return planPane;
	}
	public static void setPlans(List<Plan> p) {
		plans = p;
	}
}
