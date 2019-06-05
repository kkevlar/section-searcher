package logic.gui;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.entity.Category;
import logic.entity.CheckedSection;
import logic.entity.Course;
import logic.entity.Plan;
import logic.entity.PlanFactory;
import logic.entity.Section;
import logic.scraper.ClassDB;

public class Gui extends Application{

	static SimpleObjectProperty<Plan> selectedPlan = new SimpleObjectProperty<>(new Plan("", 1, new ArrayList<Category>()));
	public static SimpleObjectProperty<Category> selectedCategory = new SimpleObjectProperty<>(new Category("",new ArrayList<Course>(), false));
	public static List<Course> courses = new ArrayList<>();
	static List<Plan> plans = new ArrayList<>();
	public static ObservableList<CheckedSection> sections = FXCollections.observableArrayList();
	static ArrayList<Boolean> areInCat;
	static ClassDB classDb = ClassDB.getInstance();
	static GridPane coursePane;
	static GridPane catPane = new GridPane();
	GridPane planPane;
	static final GridPane pane2 = new GridPane();
	
	public static void main(String[] args) {
		classDb.scrapeAll();
		courses = classDb.getCourses();
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		coursePane = CoursesPane.getCoursesPane();
		stage.setTitle("Section Searcher");
		Label title = new Label("");
		String sectionTitle = "section-title";
		
		MenuBar topMenu = new MenuBar();
		
		Menu homeLink = new Menu("Section Searcher");
		homeLink.getStyleClass().clear();
		homeLink.getStyleClass().add("home-link");
		
		topMenu.getMenus().addAll(homeLink);
		
		GridPane gridPane = new GridPane();
	    gridPane.setPadding(new Insets(10,10,10,10));
	    gridPane.getStyleClass().add("padded");
	    
		Scene scene = new Scene(new VBox(), 1600, 800);
		scene.getStylesheets().add("Styles/Style.css");
		stage.setScene(scene);
		((VBox)scene.getRoot()).getChildren().addAll(topMenu, gridPane);
		
	    gridPane.setAlignment(Pos.CENTER);
	    gridPane.setMinHeight(700);
		title.setText("Section Searching is fun!");
		ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(50);
        
        gridPane.getColumnConstraints().addAll(col1,col2,col3);
        
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(80);
        
        gridPane.getRowConstraints().addAll(row1, row2, row3);
    
        col1.setHalignment(HPos.CENTER);
        col2.setHalignment(HPos.CENTER);
        col3.setHalignment(HPos.CENTER);
        
        Label label1 = new Label("Plans");
        label1.getStyleClass().clear();
        label1.getStyleClass().add(sectionTitle);
        Button savePlanButton = new Button("Save Plan");
        savePlanButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                PlanFactory.savePlan(selectedPlan.getValue());
            }
        });
        Button loadPlanButton = new Button("Load Plans");
        loadPlanButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                List<String> planNames = PlanFactory.getPlanList();
                for(String pname : planNames) {
                	Optional<Plan> plan = PlanFactory.getPlan(pname);
                	if(plan.isPresent()) {
                		plans.add(plan.get());
                	}
                }
                PlanPane.setPlans(plans);
                planPane = PlanPane.getPlanPane();
            }
        });
        PlanPane.setPlans(plans);
        planPane = PlanPane.getPlanPane();
        Label label2 = new Label("Categories");
        label2.getStyleClass().clear();
        label2.getStyleClass().add(sectionTitle);
        
        Label label3 = new Label("Search");
        label3.getStyleClass().clear();
        label3.getStyleClass().add(sectionTitle);
        
        gridPane.add(label3, 2, 0);
        gridPane.add(label2, 1, 0);
        gridPane.add(label1, 0, 0);
        HBox planButtons = new HBox(80, loadPlanButton, savePlanButton);
        gridPane.add(planButtons, 0, 1);
	    pane2.setAlignment (Pos.CENTER);
        pane2.getStyleClass().add("borders");
        pane2.getStyleClass().add("outside");
        pane2.setStyle("-fx-alignment: center;");
        pane2.getChildren().add(catPane);
        gridPane.add(planPane, 0, 2);
        gridPane.add(pane2, 1, 2);
        gridPane.add(coursePane, 2, 2);
        resetCourses();
        addListeners();
        
		stage.show();
	}
	
	private static void addListeners() {
        selectedPlan.addListener((obs, newVal, oldVal)->{
        	resetCourses();
        	for(int i=0; i < catPane.getChildren().size(); i++) {
        		catPane.getChildren().remove(0);
        	}
        	catPane = CategoryPane.getCategoryPane(selectedPlan);
        	for(int i=0; i < pane2.getChildren().size(); i++) {
        		pane2.getChildren().remove(0);
        	}
            pane2.add(catPane, 0, 0);
        });
        selectedCategory.addListener((obs, newVal, oldVal)->resetCourses());
	}
	
	public static void setSelectPlan(Plan plan) {
		selectedPlan.set(plan);
	}
	public static void setSelectedCategory(Category cat) {
		selectedCategory.setValue(cat);
	}
	
	public static boolean selectedCategoryContains(Course c) {
		for(Course temp : selectedCategory.getValue().getCourses()){
			if(temp.getName().equals(c.getName())){
				return true;
			}
		}
		return false;
	}
	
	public static void addCourseToCategory(Course c) {
		selectedCategory.getValue().addCourse(c);
	}

	public static void removeCourseFromCategory(Course c) {
		selectedCategory.getValue().removeCourse(c);
	}
	
	public static List<Course> listCourses(){
		return courses;
	}
	
	public static void searchCourses(String dept) {
		if(coursePane != null) {
			CoursesPane.clearItems();
		}
		sections = FXCollections.observableArrayList();
		courses = ClassDB.filterDepartment(courses, dept);
        for(int i = 0; i < courses.size(); i++) {
			List<Section> sects = (List<Section>)courses.get(i).getSections();
			Boolean checked = false;
			for(Course c : selectedCategory.getValue().getCourses()) {
				if(c.getName().equals(courses.get(i).getName())) {
					checked = true;
				}
			}
			for(Section s : sects) {
				sections.add(new CheckedSection(s, checked));
			}
        }

		if(coursePane != null) {
			CoursesPane.addItems(sections);
		}
	}
	public static void clearSearch() {
    	CoursesPane.clearItems();
    	sections = FXCollections.observableArrayList();
		courses = classDb.getCourses();
        for(int i = 0; i < courses.size(); i++) {
			List<Section> sects = (List<Section>)courses.get(i).getSections();
			Boolean checked = false;
			for(Course c : selectedCategory.getValue().getCourses()) {
				if(c.getName().equals(courses.get(i).getName())) {
					checked = true;
				}
			}
			for(Section s : sects) {
				sections.add(new CheckedSection(s, checked));
			}
        }
        CoursesPane.addItems(sections);
	}
	
	public static void resetCourses() {
		CoursesPane.clearItems();
    	sections = FXCollections.observableArrayList();
        for(int i = 0; i < courses.size(); i++) {
			ArrayList<Section> sects = (ArrayList<Section>)courses.get(i).getSections();
			Boolean checked = false;
			for(Course c : selectedCategory.getValue().getCourses()) {
				if(c.getName().equals(courses.get(i).getName())) {
					checked = true;
				}
			}
			for(Section s : sects) {
				sections.add(new CheckedSection(s, checked));
			}
        }
        CoursesPane.addItems(sections);
	}
	
}
