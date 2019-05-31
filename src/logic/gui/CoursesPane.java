package logic.gui;

import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import logic.entity.CheckedSection;
import logic.entity.Course;

public class CoursesPane {
	static GridPane coursePane = new GridPane();
    static TableView<CheckedSection> sectionTable = new TableView<CheckedSection>();
	public static GridPane getCoursesPane() {
	    coursePane.setAlignment(Pos.TOP_CENTER);
        coursePane.getStyleClass().add("borders");
        coursePane.getStyleClass().add("outside");
        
        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.requestFocus();
        textField.getStyleClass().add("search-bar");
        textField.getStyleClass().add("borders");
        coursePane.add(textField, 0, 0);
        
        sectionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        sectionTable.setMinWidth(600);
        sectionTable.setEditable(true);


        
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
		            	for(Course c : Gui.ListCourses()) {
	            			if(c.getName() == selected.getCourseName() &&
	            					!Gui.selectedCategoryContains(c)) {
	            				Gui.addCourseToCategory(c);
	            			}
	            		}
		            } else {
	            		for(Course c : Gui.ListCourses()) {
	            			if(c.getName() == selected.getCourseName() &&
	            					!Gui.selectedCategoryContains(c)) {
	            				Gui.removeCourseFromCategory(c);
	            			}
	            		}
	            	}
	            }
	            });
	        cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	        cell.setAlignment(Pos.CENTER);
	        return cell ;
        });

    
        TableColumn<CheckedSection, String> column2 = new TableColumn<>("Code");
        column2.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
                return new ReadOnlyObjectWrapper<String>(s.getValue().getCourseName());
            }
        });
        column2.setMinWidth(150);
        column2.getStyleClass().add("table-cell");
        
        TableColumn<CheckedSection, String> column3 = new TableColumn<>("Section Id");
        column3.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
                return new ReadOnlyObjectWrapper<String>(s.getValue().getID());
            }
        });
        column3.setMinWidth(150);
        column3.getStyleClass().add("table-cell");


        TableColumn<CheckedSection, String> column4 = new TableColumn<>("Open Spots");
        column4.setCellValueFactory(new Callback<CellDataFeatures<CheckedSection, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<CheckedSection, String> s) {
                return new ReadOnlyObjectWrapper<String>("" + s.getValue().getOpenSpots());
            }
        });
        column4.setMinWidth(150);
        column4.getStyleClass().add("table-cell");

        sectionTable.getColumns().add(column1);
        sectionTable.getColumns().add(column2);
        sectionTable.getColumns().add(column3);
        sectionTable.getColumns().add(column4);

        
        coursePane.add(sectionTable, 0, 1);
		
		return coursePane;
	}
	
	public static void clearItems() {
		sectionTable.getItems().clear();
	}
	public static void addItems(List<CheckedSection> sections) {
		sectionTable.getItems().addAll(sections);
	}
}
