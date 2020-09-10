package com.yedam.book;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import basic.common.ConnectionDB;
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

public class BookController implements Initializable {
	@FXML
	TableView<Book> tableView;
//	@FXML
//	TableView<Member> tableView2;
	@FXML
	Button btnAdd, btnBring , btnMember,btnUserAdd,btnUserCancel,btnInsert,btnDelete,btnSearch;

	ObservableList<Book> list;

	Stage primaryStage;

	
	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primary stage에서 사용하는 값들을 전달해주기 위해서
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		TableColumn<Book, ?> tc = tableView.getColumns().get(0); // 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("bookname"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("bookuser"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("company"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));

		
		list = FXCollections.observableArrayList();
		tableView.setItems(list);
		
		
		
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				list = getBookList();
				tableView.setItems(list);
			}
			
		});
		
		btnBring.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				handleBtnBringAction();
				
				
			}
			
		});
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// System.out.println(event);
				if (event.getClickCount() == 2) {// 2라는 것은 더블클릭
					String selectedName = tableView.getSelectionModel().getSelectedItem().getBookname();
					handleDoubleClickAction(selectedName);

				}
			}

		});
		/////
		btnMember.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {				
				handleBtnAddAction2();
			}
		});


		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				handleBtnAddAction();
			}	
		});
	}
	public void insertStudentList(Book book) {
		
		Connection conn = ConnectionDB.getDB();

		String sql = "insert into BOOK_DB(book_name,book_author,book_publisher,book_price)"
		+ "values("+"\'"+book.getBookname()+"\',"+"\'"+book.getBookuser()+"\',\'"+book.getCompany()+"\',\'"+book.getPrice()+"\'"+")";
		
		list = FXCollections.observableArrayList();
		
		System.out.println(sql);
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}

	public ObservableList<Book> getBookList(){
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from BOOK_DB";
		list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book book= new Book(rs.getString("book_name"),rs.getString("book_author"),rs.getString("book_publisher"),rs.getInt("book_price"));
				list.add(book);
			}
			System.out.println("조회되었습니다.");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public void handleBtnAddAction2() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnMember.getScene().getWindow());// 나중에 추가 ////////////////

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookMember.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public void handleDoubleClickAction(String name) {

		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);

		Label  lUser, lCompany , lPrice;
		TextField tName, tUser, tCompany, tPrice;

		lUser = new Label("저자");
		lUser.setLayoutX(35);
		lUser.setLayoutY(73);

		lCompany = new Label("출판사");
		lCompany.setLayoutX(35);
		lCompany.setLayoutY(99);

		lPrice = new Label("가격");
		lPrice.setLayoutX(35);
		lPrice.setLayoutY(132);

		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(30);

		tName.setText(name);
		tName.setEditable(false);

		tUser = new TextField();
		tUser.setPrefWidth(110);
		tUser.setLayoutX(72);
		tUser.setLayoutY(69);

		tCompany = new TextField();
		tCompany.setPrefWidth(110);
		tCompany.setLayoutX(72);
		tCompany.setLayoutY(95);

		tPrice = new TextField();
		tPrice.setPrefWidth(110);
		tPrice.setLayoutX(72);
		tPrice.setLayoutY(128);

		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);

		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getBookname().contentEquals(name)) {
						Book book = new Book(name, tUser.getText(),tCompany.getText(), Integer.parseInt(tPrice.getText()));
						list.set(i, book);
					}
				}
				stage.close();
			}

		});

		// 이름기준으로 국어,수학,영어 점수...화면에 입력.
		for (Book stu : list) {
			if (stu.getBookname().contentEquals(name)) {
				tCompany.setText(String.valueOf(stu.getCompany()));
				tUser.setText(String.valueOf(stu.getCompany()));
				tPrice.setText(String.valueOf(stu.getPrice()));
			}
		}

		ap.getChildren().addAll(btnUpdate, tName, tUser, tCompany, tPrice, lUser, lCompany, lPrice);

		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
	}



// 추가화면 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 나중에 추가

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			// 추가화면의 컨트롤 사용하기
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtBookName");
					TextField txtUser = (TextField) parent.lookup("#txtBookUser");
					TextField txtCompany = (TextField) parent.lookup("#txtCompany");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");

					Book book = new Book(txtName.getText(),txtUser.getText(),txtCompany.getText(),Integer.parseInt(txtPrice.getText()));
					list.add(book);	
					insertStudentList(book);
					stage.close();
				}

			});

			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtName = (TextField) parent.lookup("#txtBookName");
				TextField txtUser = (TextField) parent.lookup("#txtBookUser");
				TextField txtCompany = (TextField) parent.lookup("#txtCompany");
				TextField txtPrice = (TextField) parent.lookup("#txtPrice");

				txtName.clear();
				txtUser.clear();
				txtCompany.clear();
				txtPrice.clear();
				

			});

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void removeEmp(String deid) {
		
		Connection conn = ConnectionDB.getDB();

		String sql = "delete from BOOK_DB where book_name='"+deid+"'";
		
		System.out.println(sql);
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제되었습니다.");
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	
	
	public void handleBtnBringAction() {

		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnBring.getScene().getWindow());// 나중에 추가
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BookBring.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			// 추가화면의 컨트롤 사용하기
			Button btnFormDelete = (Button) parent.lookup("#btnFormDelete");
			btnFormDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtDeleteName = (TextField) parent.lookup("#txtDeleteName");
					
					///////
					
					
					
					for(int i=0;i<list.size();i++) {
						if(list.get(i).getBookname().equals(txtDeleteName.getText())) {
							list.remove(i);
						}
					}
					
					removeEmp(txtDeleteName.getText());
					stage.close();

				}

			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
