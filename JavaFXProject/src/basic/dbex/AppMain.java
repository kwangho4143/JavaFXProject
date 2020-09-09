package basic.dbex;
//UI: Root.fxml(기본),AddForm.fxml(추가),BarChart.fxml(차트)
//Control:RootController.java
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppMain extends Application{

	@FXML
	TableView<Student2> tableView;
	@FXML
	Button btnAdd, btnBarChart;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
		BorderPane root = loader.load(); //기존과 다른점
		
		RootController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
