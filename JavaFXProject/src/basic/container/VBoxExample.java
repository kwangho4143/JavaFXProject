

//p.g 875
package basic.container;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application { //추상클래스라서 override 필요

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root =new VBox();
		root.setPadding(new Insets(10,10,10,10));
		
		ImageView iv=new ImageView();//이메진 뷰
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/basic/images/sample.jpg"));
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		
		Button btnPrev = new Button();//hbox의 버튼
		btnPrev.setText("이전");
		Button btnNext = new Button("다음");
		HBox.setHgrow(btnNext,Priority.ALWAYS); //남은 크기를 btnNext만큼 지정하겠다?
		btnNext.setMaxHeight(Double.MAX_VALUE);
		hbox.getChildren().add(btnPrev);
		hbox.getChildren().add(btnNext);
		VBox.setMargin(hbox, new Insets(10));


		root.getChildren().add(iv);
		root.getChildren().add(hbox);
		
		Scene scene = new Scene(root); // 만든것을 Scene에 담는다 
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("p.g 875 VBox 예제");
		
		
		
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);//start 실행
	}
	
	
}
