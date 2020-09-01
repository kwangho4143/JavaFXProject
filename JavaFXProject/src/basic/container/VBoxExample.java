

//p.g 875
package basic.container;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		iv.setImage(new Image("/basic/images/fruit1.jpg"));
		
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

		//이벤트 핸들러를 해당 컨트롤에 등록
		btnNext.setOnAction(new EventHandler<ActionEvent>() {

			int loc = 1;
			@Override
			public void handle(ActionEvent ae) {
					if(loc==9)
						loc=1;
					iv.setImage(new Image("/basic/images/fruit"+loc++ +".jpg"));
				
				
				
				
				
			}
			
		});
		
		btnPrev.setOnAction(new EventHandler<ActionEvent>() {

			int loc = 8;
			@Override
			public void handle(ActionEvent ae) {
					if(loc==0)
						loc=loc--;
					iv.setImage(new Image("/basic/images/fruit"+loc-- +".jpg"));
				
				
				
				
				
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		//위에 꺼와 같다 람다식 표현
//		btnNext.setOnAction((ae) -> {
//				System.out.println("handle: "+ae.getSource());		
//		});
		
		

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
