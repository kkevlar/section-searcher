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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
import logic.scraper.WebScraper;

public class Gui extends Application{

	static SimpleObjectProperty<Plan> selectedPlan = new SimpleObjectProperty<Plan>(new Plan("", 1, new ArrayList<Category>()));
	static SimpleObjectProperty<Category> selectedCategory = new SimpleObjectProperty<Category>(new Category("",new ArrayList<Course>(), false));
	static List<Course> courses;
	static List<Plan> plans = new ArrayList<Plan>();
	static ObservableList<CheckedSection> sections = FXCollections.observableArrayList();
	static ArrayList<Boolean> areInCat;
	static ClassDB classDb = ClassDB.getInstance();
	final GridPane coursePane = CoursesPane.getCoursesPane();
	static GridPane catPane = new GridPane();
	static GridPane planPane;
	final GridPane pane2 = new GridPane();
	
	public static void main(String[] args) {
		try {
			//classDb.scrapeAll();
			courses = classDb.getCourses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {

		stage.setTitle("Section Searcher");
		Label title = new Label("");
		
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
        label1.getStyleClass().add("section-title");
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
        label2.getStyleClass().add("section-title");
        
        Label label3 = new Label("Search");
        label3.getStyleClass().clear();
        label3.getStyleClass().add("section-title");
        
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
        sections = FXCollections.observableArrayList();
        for(int i = 0; i < courses.size(); i++) {
			ArrayList<Section> sects = (ArrayList<Section>)courses.get(i).getSections();
			for(Section s : sects) {
				sections.add(new CheckedSection(s, false));
			}
        }
        CoursesPane.addItems(sections);
        addListeners();
        
		stage.show();
	}
	
	private void addListeners() {
        selectedPlan.addListener((obs, newVal, oldVal)->{
        	CoursesPane.clearItems();
        	sections = FXCollections.observableArrayList();
	        for(int i = 0; i < courses.size(); i++) {
				ArrayList<Section> sects = (ArrayList<Section>)courses.get(i).getSections();
				for(Section s : sects) {
					sections.add(new CheckedSection(s, false));
				}
	        }
	        CoursesPane.addItems(sections);
        	for(int i=0; i < catPane.getChildren().size(); i++) {
        		catPane.getChildren().remove(0);
        	}
        	catPane = CategoryPane.getCategoryPane(selectedPlan);
        	for(int i=0; i < pane2.getChildren().size(); i++) {
        		pane2.getChildren().remove(0);
        	}
            pane2.add(catPane, 0, 0);
        });
        selectedCategory.addListener((obs, newVal, oldVal)->{
         	CoursesPane.clearItems();
			sections = FXCollections.observableArrayList();
	        for(int i = 0; i < courses.size(); i++) {
				ArrayList<Section> sects = (ArrayList<Section>)courses.get(i).getSections();
				for(Section s : sects) {
					if(selectedCategory.getValue().getCourses().contains(courses.get(i))){
						sections.add(new CheckedSection(s, true));
					}else {
						sections.add(new CheckedSection(s, false));
					}
				}
	        }
			CoursesPane.addItems(sections);
        });
        sections.addListener(new ListChangeListener<CheckedSection>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends CheckedSection> col) {
                while (col.next()) {
                    if (col.wasUpdated()) {
                    	int param = col.getFrom();
                    	if(sections.get(param).getChecked()) {
                    		for(Course c : courses) {
                    			if(c.getName() == sections.get(param).getCourseName() &&
                    					!selectedCategory.getValue().getCourses().contains(c)) {
                    				selectedCategory.getValue().addCourse(c);
                    			}
                    		}
                    	} else {
                    		for(Course c : courses) {
                    			if(c.getName() == sections.get(param).getCourseName() &&
                    					!selectedCategory.getValue().getCourses().contains(c)) {
                    				selectedCategory.getValue().removeCourse(c);
                    			}
                    		}
                    	}
                    	CoursesPane.clearItems();
            			sections = FXCollections.observableArrayList();
            	        for(int i = 0; i < courses.size(); i++) {
            				ArrayList<Section> sects = (ArrayList<Section>)courses.get(i).getSections();
            				for(Section s : sects) {
            					if(selectedCategory.getValue().getCourses().contains(courses.get(i))){
            						sections.add(new CheckedSection(s, true));
            					}else {
            						sections.add(new CheckedSection(s, false));
            					}
            				}
            	        }
            	        CoursesPane.addItems(sections);
                    }
                  }
            }
        });
	}
	
	public static void setSelectPlan(Plan plan) {
		selectedPlan.set(plan);
	}
	public static void setSelectedCategory(Category cat) {
		selectedCategory.setValue(cat);
	}
	
	public static boolean selectedCategoryContains(Course c) {
		return selectedCategory.getValue().getCourses().contains(c);
	}
	
	public static void addCourseToCategory(Course c) {
		selectedCategory.getValue().addCourse(c);
	}

	public static void removeCourseFromCategory(Course c) {
		selectedCategory.getValue().removeCourse(c);
	}
	
	public static List<Course> ListCourses(){
		return courses;
	}
	
	public static void searchCourses(String dept) {
    	CoursesPane.clearItems();
		sections = FXCollections.observableArrayList();
		courses = ClassDB.filterDepartment(courses, dept);
        for(int i = 0; i < courses.size(); i++) {
			List<Section> sects = (List<Section>)courses.get(i).getSections();
			for(Section s : sects) {
				if(selectedCategory.getValue().getCourses().contains(courses.get(i))){
					sections.add(new CheckedSection(s, true));
				}else {
					sections.add(new CheckedSection(s, false));
				}
			}
        }
        CoursesPane.addItems(sections);
	}
	public static void clearSearch() {
    	CoursesPane.clearItems();
    	sections = FXCollections.observableArrayList();
		courses = classDb.getCourses();
        for(int i = 0; i < courses.size(); i++) {
			List<Section> sects = (List<Section>)courses.get(i).getSections();
			for(Section s : sects) {
				if(selectedCategory.getValue().getCourses().contains(courses.get(i))){
					sections.add(new CheckedSection(s, true));
				}else {
					sections.add(new CheckedSection(s, false));
				}
			}
        }
        CoursesPane.addItems(sections);
	}
	
}
