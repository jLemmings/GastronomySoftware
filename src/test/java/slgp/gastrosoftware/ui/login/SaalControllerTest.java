package slgp.gastrosoftware.ui.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SaalControllerTest implements Initializable{


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	@FXML
	private void tischAction(ActionEvent event) throws Exception {
		Parent tisch_anzeigen_parent = FXMLLoader.load(getClass().getResource("/fxml/TischAnzeigen.fxml"));
		Scene tisch_anzeigen_scene = new Scene(tisch_anzeigen_parent);
		Stage tisch_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		tisch_stage.setScene(tisch_anzeigen_scene);
		tisch_stage.show();
		
		
		
	}

	@FXML
	private void zurueck(ActionEvent event) throws IOException {
		Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
		Scene ma_interface_scene = new Scene(ma_interface_parent);
		Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ma_stage.setScene(ma_interface_scene);
		ma_stage.show();
	}
	
	@FXML
	private void nextSeite(ActionEvent event) throws IOException {
		Parent saal2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal2.fxml"));
		Scene saal2_interface_scene = new Scene(saal2_interface_parent);
		Stage saal2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		saal2_stage.setScene(saal2_interface_scene);
		saal2_stage.show();
	}

	@FXML
	private void lastSeite(ActionEvent event) throws IOException {
		Parent saal2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal2_interface_scene = new Scene(saal2_interface_parent);
		Stage saal2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		saal2_stage.setScene(saal2_interface_scene);
		saal2_stage.show();
	}


	
}