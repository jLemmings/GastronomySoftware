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

public class TerrasseControllerTest implements Initializable{


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
		Parent terrasse1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse2.fxml"));
		Scene terrasse1_interface_scene = new Scene(terrasse1_interface_parent);
		Stage terrasse1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		terrasse1_stage.setScene(terrasse1_interface_scene);
		terrasse1_stage.show();
	}

	@FXML
	private void lastSeite(ActionEvent event) throws IOException {
		Parent terrasse2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse1.fxml"));
		Scene terrasse2_interface_scene = new Scene(terrasse2_interface_parent);
		Stage terrasse2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		terrasse2_stage.setScene(terrasse2_interface_scene);
		terrasse2_stage.show();
	}


	
}