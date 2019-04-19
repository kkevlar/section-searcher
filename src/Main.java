import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {

		stage.setTitle("Section Searcher");
		Label title = new Label("");
		GridPane gridPane = new GridPane();

	    gridPane.setPadding(new Insets(10,10,10,10));
	    gridPane.setStyle("-fx-background-fill: black;");

		Scene scene = new Scene(gridPane, 1600, 800);
		stage.setScene(scene);
		
		Button button = new Button("Example Button");
		button.setOnAction(new EventHandler<ActionEvent>()	{			
			public void handle(ActionEvent event) {
				System.out.println("You pushed the button.");
			}		
		});
	    gridPane.setAlignment(Pos.TOP_CENTER);
		title.setText("Section Searching is fun!");
		ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(50);
        
        gridPane.getColumnConstraints().addAll(col1,col2,col3);
    
        col1.setHalignment(HPos.CENTER);
        col2.setHalignment(HPos.CENTER);
        col3.setHalignment(HPos.CENTER);
        
        Label label1 = new Label("Plans");
        label1.setStyle("-fx-font-weight: 500; -fx-font-size: 24px;");
        label1.setTextFill(Color.web("#0076a3"));

        Label label2 = new Label("Categories");
        label2.setStyle("-fx-font-weight: 500; -fx-font-size: 24px;");
        label2.setTextFill(Color.web("#0076a3"));
        
        Label label3 = new Label("Search");
        label3.setStyle("-fx-font-weight: 500; -fx-font-size: 24px;");
        label3.setTextFill(Color.web("#0076a3"));
        
        gridPane.add(label3, 2, 0);
        gridPane.add(label2, 1, 0);
        gridPane.add(label1, 0, 0);
        
		stage.show();
	}
}
