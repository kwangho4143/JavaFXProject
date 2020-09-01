package basic.container.eventhandle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

public class RootController implements Initializable { //화면에 로딩될때 처리하는 것

	//Root.fxml에서 설정한 변수값을 지정해준다
	@FXML Label label;
	@FXML Slider slider;
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number startValue, Number endValue) {
				System.out.println("startValue:"+startValue.doubleValue());
				System.out.println("endValue:"+endValue.doubleValue());
				label.setFont(new Font(endValue.doubleValue()));
			}
			
			
			
		});
	
		
	}

}
