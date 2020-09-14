package com.yedam.book.test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.yedam.book.Book;

import basic.common.ConnectionDB;
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LogController implements Initializable {

	@FXML
	TableView<Login> tableView;
	@FXML
	Button btnLogin;
	
	ObservableList<Login> list;
	
	Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primary stage에서 사용하는 값들을 전달해주기 위해서
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = FXCollections.observableArrayList();
		tableView.setItems(list);

		Button btnLogin = (Button) parent.lookup("#btnLogin");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextField txtIdInf = (TextField) parent.lookup("#txtIdInf");
				TextField txtPwInf = (TextField) parent.lookup("#txtPwInf");
				
				
				try {
					list = getLogInf();
					String selectedID = tableView.getSelectionModel().getSelectedItem().getId();
					String selectedPW = tableView.getSelectionModel().getSelectedItem().getPassword();
					for(int i = 0;i<list.size();i++) {
						if(list.get(i).getId()==selectedID && list.get(i).getPassword()==selectedPW) {
							Stage stage = new Stage(StageStyle.UTILITY);
							stage.initModality(Modality.WINDOW_MODAL);
							stage.initOwner(btnLogin.getScene().getWindow());
							Parent parent = FXMLLoader.load(getClass().getResource("BookList.fxml"));
							Scene scene = new Scene(parent);
							stage.setScene(scene);
							stage.show();
						}
						else
							Platform.exit();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}//이니셜라이즈
	public ObservableList<Login> getLogInf(){
		Connection conn = ConnectionDB.getDB();
		String sql = "select login_id from LOG_DB";
		list = FXCollections.observableArrayList();	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Login lg= new Login(rs.getString("log_id"),rs.getString("log_password"));
				list.add(lg);
			}
			System.out.println("조회되었습니다.");
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return list;
	}
}
