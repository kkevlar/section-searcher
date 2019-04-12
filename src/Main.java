import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {

		stage.setTitle("Section Searcher");
		Label title = new Label("");
		VBox root = new VBox();

		Scene scene = new Scene(root, 1600, 800);
		stage.setScene(scene);
		
		Button button = new Button("Example Button");
		button.setOnAction(new EventHandler<ActionEvent>()	{			
			public void handle(ActionEvent event) {
				System.out.println("You pushed the button.");
			}		
		});
		title.setText("Section Searching is fun!");
		root.getChildren().add(title);
		root.getChildren().add(button);
		stage.show();
	}
}
