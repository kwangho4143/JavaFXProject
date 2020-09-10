package com.yedam.book.test;

import com.yedam.book.BookController;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LogMain extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BookList.fxml"));
		BorderPane root = loader.load(); //기존과 다른점
		
		BookController controller = loader.getController();
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
