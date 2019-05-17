package logic;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application{

	static SimpleObjectProperty<Plan> selectedPlan = new SimpleObjectProperty<Plan>(new Plan("", 1, new ArrayList<Category>()));
	static SimpleObjectProperty<Category> selectedCategory = new SimpleObjectProperty<Category>(new Category("",new ArrayList<Course>(), false));
	static List<Course> courses;
	static ArrayList<Boolean> areInCat;
	static Course course1 = new Course("101");
	static Course course2 = new Course("202");
	static Course course3 = new Course("203");
	static Course course4 = new Course("357");
	final GridPane coursePane = new GridPane();
	static GridPane catPane = new GridPane();
	final GridPane planPane = new GridPane();
	final GridPane pane2 = new GridPane();
	
	public static void main(String[] args) {
		WebScraper scraper = new WebScraper();
		try {
			courses = scraper.scrapeCoursesByDept("CPE");
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
        row2.setPercentHeight(90);
        
        gridPane.getRowConstraints().addAll(row1, row2);
    
        col1.setHalignment(HPos.CENTER);
        col2.setHalignment(HPos.CENTER);
        col3.setHalignment(HPos.CENTER);
        
        Label label1 = new Label("Plans");
        label1.getStyleClass().clear();
        label1.getStyleClass().add("section-title");

        Label label2 = new Label("Categories");
        label2.getStyleClass().clear();
        label2.getStyleClass().add("section-title");
        
        Label label3 = new Label("Search");
        label3.getStyleClass().clear();
        label3.getStyleClass().add("section-title");
        
        gridPane.add(label3, 2, 0);
        gridPane.add(label2, 1, 0);
        gridPane.add(label1, 0, 0);
        
        final GridPane pane1 = getPlanPane();
	    pane2.setAlignment(Pos.CENTER);
        pane2.getStyleClass().add("borders");
        pane2.getStyleClass().add("outside");
        pane2.setStyle("-fx-alignment: center;");
        final GridPane pane3 = getCoursesPane();
        
        gridPane.add(pane1, 0, 1);
        gridPane.add(pane2, 1, 1);
        gridPane.add(pane3, 2, 1);
        
		stage.show();
	}
	
	private GridPane getPlanPane() {
        planPane.setAlignment(Pos.TOP_CENTER);
        planPane.getStyleClass().add("borders");
        planPane.getStyleClass().add("outside");
        planPane.setStyle("-fx-alignment: center;");
        List<Plan> plans = new ArrayList<Plan>();
        plans.add(new Plan("Plan 1", 1, new ArrayList<Category>()));

    	plans.get(0).addCategory(new Category("GE's",new ArrayList<Course>(), false));
    	ArrayList<Course> majorCourses = new ArrayList<Course>();
    	majorCourses.add(courses.get(0));
    	plans.get(0).addCategory(new Category("Major Courses", majorCourses, false));
        plans.add(new Plan("Plan 2", 2, new ArrayList<Category>()));
        List<Label> labels = new ArrayList<Label>();
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
                selectedPlan.set(plan);
        	});
        	labels.add(planLabel);
        	count++;
        }
		return planPane;
	}
	
	private GridPane getCategoryPane() {
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
	    		selectedCategory.setValue(cat);
	    	});
	    	labels.add(catLabel);
	    	count++;
	    }
	    return catPane;
	}
	
	private GridPane getCoursesPane() {
	    coursePane.setAlignment(Pos.TOP_CENTER);
        coursePane.getStyleClass().add("borders");
        coursePane.getStyleClass().add("outside");
        
        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.requestFocus();
        textField.getStyleClass().add("search-bar");
        textField.getStyleClass().add("borders");
        coursePane.add(textField, 0, 0);
        
        TableView<Section> sectionTable = new TableView<Section>();
        sectionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        sectionTable.setMinWidth(600);
        sectionTable.setEditable(true);

        selectedPlan.addListener((obs, newVal, oldVal)->{
			sectionTable.getItems().clear();
        	if(catPane.getChildren().size() > 0) {
        		catPane.getChildren().remove(0);
        	}
        	catPane = getCategoryPane();
            pane2.add(catPane, 0, 0);
        });
        selectedCategory.addListener((obs, newVal, oldVal)->{
			sectionTable.getItems().clear();        
			ArrayList<Section> demoSects = new ArrayList<Section>();
	        for(int i = 0; i < courses.size(); i++) {
	        	demoSects.addAll(courses.get(i).getSections());
	        }
			sectionTable.getItems().addAll(0, demoSects);
        });
        
        TableColumn<Section, Boolean> column1 = new TableColumn<>("");
        column1.setCellValueFactory(
        column1.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {

        	        @Override
        	        public ObservableValue<Boolean> call(Integer param) {
        	        	selectedCategory.getValue().addCourse(courses.get(param));
        	            return new SimpleBooleanProperty(selectedCategory.getValue().getCourses().contains(courses.get(param)));
        	        }
        	    }));

    
        TableColumn<Section, String> column2 = new TableColumn<>("Code");
        column2.setCellValueFactory(new Callback<CellDataFeatures<Section, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Section, String> s) {
                return new ReadOnlyObjectWrapper<String>(s.getValue().getCourseName());
            }
        });
        column2.setMinWidth(150);
        column2.getStyleClass().add("table-cell");
        
        TableColumn<Section, String> column3 = new TableColumn<>("Section Id");
        column3.setCellValueFactory(new Callback<CellDataFeatures<Section, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Section, String> s) {
                return new ReadOnlyObjectWrapper<String>(s.getValue().getID());
            }
        });
        column3.setMinWidth(150);
        column3.getStyleClass().add("table-cell");


        TableColumn<Section, String> column4 = new TableColumn<>("Open Spots");
        column4.setCellValueFactory(new Callback<CellDataFeatures<Section, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Section, String> s) {
                return new ReadOnlyObjectWrapper<String>("" + s.getValue().getOpenSpots());
            }
        });
        column4.setMinWidth(150);
        column4.getStyleClass().add("table-cell");

        sectionTable.getColumns().add(column1);
        sectionTable.getColumns().add(column2);
        sectionTable.getColumns().add(column3);
        sectionTable.getColumns().add(column4);

        TimeBlock tbTest = new TimeBlock(new Time(11, 10), new Time(12, 10));
        TimeBlock[] testtimes = {null, tbTest, null, tbTest, null, tbTest, null};
        ArrayList<Section> demoSects = new ArrayList<Section>();
        for(int i = 0; i < courses.size(); i++) {
        	demoSects.addAll(courses.get(i).getSections());
        }
        selectedPlan.addListener(
        		(obs, newVal, oldVal)->{
        			sectionTable.getItems().clear();
        			sectionTable.getItems().addAll(0, demoSects);
        		});
        
        coursePane.add(sectionTable, 0, 1);
		
		return coursePane;
	}
	
}
