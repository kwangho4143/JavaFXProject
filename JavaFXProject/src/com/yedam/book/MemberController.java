package com.yedam.book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MemberController implements Initializable{
	@FXML
	TableView<Member> tableView;
	@FXML
	Button btnMember,btnUserAdd,btnUserCancel,btnInsert,btnDelete;

	ObservableList<Member> list;

	Stage primaryStage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Member, ?> tc = tableView.getColumns().get(0); // 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("age"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("phone"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("email"));

		// 성적저장
		list = FXCollections.observableArrayList();

		tableView.setItems(list);

		// 추가버튼

	
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// System.out.println(event);
				if (event.getClickCount() == 2) {// 2라는 것은 더블클릭
					String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
					handleDoubleClickAction(selectedName);

				}
			}

		});
	}
	public void handleDoubleClickAction(String name) {

		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);

		Label  lAge, lPhone , lEmail;
		TextField tName, tAge, tPhone, tEmail;

		lAge = new Label("나이");
		lAge.setLayoutX(35);
		lAge.setLayoutY(73);

		lPhone = new Label("전화번호");
		lPhone.setLayoutX(35);
		lPhone.setLayoutY(99);

		lEmail = new Label("이메일");
		lEmail.setLayoutX(35);
		lEmail.setLayoutY(132);

		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(30);

		tName.setText(name);
		tName.setEditable(false);

		tAge = new TextField();
		tAge.setPrefWidth(110);
		tAge.setLayoutX(72);
		tAge.setLayoutY(69);

		tPhone = new TextField();
		tPhone.setPrefWidth(110);
		tPhone.setLayoutX(72);
		tPhone.setLayoutY(95);

		tEmail = new TextField();
		tEmail.setPrefWidth(110);
		tEmail.setLayoutX(72);
		tEmail.setLayoutY(128);

		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);

		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().contentEquals(name)) {
						Member member = new Member(name, Integer.parseInt(tAge.getText()),tPhone.getText(), tEmail.getText());
						list.set(i, member);
					}
				}
				stage.close();
			}

		});
		// 이름기준으로 국어,수학,영어 점수...화면에 입력.
		for (Member mem : list) {
			if (mem.getName().contentEquals(name)) {
				tAge.setText(String.valueOf(mem.getAge()));
				tPhone.setText(String.valueOf(mem.getPhone()));
				tEmail.setText(String.valueOf(mem.getEmail()));
			}
		}

		ap.getChildren().addAll(btnUpdate, tName, tAge, tPhone, tEmail, lAge, lPhone, lEmail);

		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
	}

}
