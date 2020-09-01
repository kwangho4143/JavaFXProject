package basic.container;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();
		root.setPadding(new Insets(100,100,100,100));
		
		ImageView iv = new ImageView();
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);//비율맟추기
		iv.setImage(new Image("/basic/images/fruit1.jpg"));
		
		ImageView iv2 = new ImageView();
		iv2.setFitWidth(200);
		iv2.setPreserveRatio(true);
		iv2.setImage(new Image("/basic/images/fruit2.jpg"));
		
		ImageView iv3 = new ImageView();
		iv3.setFitWidth(200);
		iv3.setPreserveRatio(true);
		iv3.setImage(new Image("/basic/images/fruit3.jpg"));
		
		ImageView iv4 = new ImageView();
		iv4.setFitWidth(200);
		iv4.setPreserveRatio(true);
		iv4.setImage(new Image("/basic/images/fruit4.jpg"));
		

		root.getChildren().add(iv);
		root.getChildren().add(iv2);
		root.getChildren().add(iv3);
		root.getChildren().add(iv4);
		
		Scene scene = new Scene(root); // 만든것을 Scene에 담는다 
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("p.g 879 TailPane 예제");
		
		
		
		
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);//start 실행
	}
	
	
	
}
