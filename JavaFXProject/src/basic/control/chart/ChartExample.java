package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//UI: Chart.fxml(P.g 925)
//Control: ChartController.java
public class ChartExample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = FXMLLoader.load(this.getClass().getResource("chart.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	 public static void main(String[] args) {
	      Application.launch(args);
	   }
		
}
