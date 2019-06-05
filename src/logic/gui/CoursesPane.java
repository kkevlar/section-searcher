package logic.gui;

import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import logic.entity.CheckedSection;
import logic.entity.Course;

public class CoursesPane {
	static GridPane coursePane = new GridPane();
    static TableView<CheckedSection> sectionTable;
    static String tableCellStyle = "table-cell";
    private CoursesPane() 
    {
        throw new IllegalStateException("Utility class");
    }
	public static GridPane getCoursesPane() {
		sectionTable = new TableView<>();
	    coursePane.setAlignment(Pos.TOP_CENTER);
        coursePane.getStyleClass().add("borders");
        coursePane.getStyleClass().add("outside");
        
        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.requestFocus();
        textField.getStyleClass().add("search-bar");
        textField.getStyleClass().add("borders");
        coursePane.add(textField, 0, 0);
        Button searchButton = new Button("Search");
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                Gui.searchCourses(textField.textProperty().getValue());
            }
        });
        coursePane.add(searchButton, 1, 0);

        Button clearButton = new Button("Clear Search");
        clearButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            	textField.clear();
                Gui.clearSearch();
            }
        });
        coursePane.add(clearButton, 1, 1);
        
        sectionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        sectionTable.setMinWidth(600);
        sectionTable.setEditable(true);


        TableColumn<CheckedSection, Boolean> column1 = getColumn1();


        TableColumn<CheckedSection, String> column2 = getColumn2();
        
        
        TableColumn<CheckedSection, String> column3 = getColumn3();


        TableColumn<CheckedSection, String> column4 = getColumn4();

        sectionTable.getColumns().add(column1);
        sectionTable.getColumns().add(column2);
        sectionTable.getColumns().add(column3);
        sectionTable.getColumns().add(column4);

        
        coursePane.add(sectionTable, 0, 2);
		
		return coursePane;
	}
	private static TableColumn<CheckedSection, Boolean> getColumn1(){
        TableColumn<CheckedSection, Boolean> column1 = new TableColumn<>("");
        column1.setCellValueFactory(new PropertyValueFactory<CheckedSection, Boolean>("Checked"));
        column1.setCellFactory(p ->{
	    		CheckBox checkBox = new CheckBox();
	    		TableCell<CheckedSection, Boolean> cell = new TableCell<CheckedSection, Boolean>() {
	    		@Override
	    		public void updateItem(Boolean item, boolean empty) {
	    			if (empty) {
	    				setGraphic(null);
	    			} else {
	    				checkBox.setSelected(item);
	    				setGraphic(checkBox);
	    			}
	    		}
	        };
	        checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
	        	CheckedSection selected = ((CheckedSection)cell.getTableRow().getItem());
	        	if(selected != null) {
	        		selected.setChecked(isSelected);
	            	if(selected.getChecked()) {
	            		findAndAddCourse(selected);
	            	}else {
	            		findAndRemoveCourse(selected);
	            	}
		            
	            }
	            });
	        cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	        cell.setAlignment(Pos.CENTER);
	        return cell ;
        });
        return column1;
	}
	
	private static void findAndRemoveCourse(CheckedSection selected) {
		for(Course c : Gui.listCourses()) {
			if(c.getName().equals(selected.getCourseName()) &&
					!Gui.selectedCategoryContains(c)) {
            	Gui.removeCourseFromCategory(c);
			}
		}
	}
	
	private static void findAndAddCourse(CheckedSection selected) {
		for(Course c : Gui.listCourses()) {
			if(c.getName().equals(selected.getCourseName()) &&
					!Gui.selectedCategoryContains(c)) {
            	Gui.addCourseToCategory(c);
			}
		}
	}
	
	private static TableColumn<CheckedSection, String> getColumn2(){
		TableColumn<CheckedSection, String> column2 = new TableColumn<>("Code");
        column2.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
                return new ReadOnlyObjectWrapper<>(s.getValue().getCourseName());
            }
        });
        column2.setMinWidth(150);
        column2.getStyleClass().add(tableCellStyle);
        return column2;
	}
	
	private static TableColumn<CheckedSection, String> getColumn3(){
		TableColumn<CheckedSection, String> column3 = new TableColumn<>("Section Id");
        column3.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
                return new ReadOnlyObjectWrapper<>(s.getValue().getID());
            }
        });
        column3.setMinWidth(150);
        column3.getStyleClass().add(tableCellStyle);
        return column3;
	}
	private static TableColumn<CheckedSection, String> getColumn4(){
		TableColumn<CheckedSection, String> column4 = new TableColumn<>("Open Spots");
	    column4.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
	        public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
	            return new ReadOnlyObjectWrapper<>("" + s.getValue().getOpenSpots());
	        }
	    });
	    column4.setMinWidth(150);
	    column4.getStyleClass().add(tableCellStyle);
	    return column4;
	}
	
	
	public static void clearItems() {
		if(sectionTable != null) {
			sectionTable.getItems().clear();
		}
	}
	public static void addItems(List<CheckedSection> sections) {
		if(sectionTable != null) {
			sectionTable.getItems().addAll(sections);
		}
	}
}
