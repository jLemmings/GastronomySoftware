package slgp.gastrosoftware.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import slgp.gastrosoftware.persister.impl.*;


public class Controller {

	@FXML
	// Variablen
	Button genLogi;

	@FXML
	private TextField genBenu;

	@FXML
	private TextField genPass;

	@FXML
	private Label genPwEr;


	// Userlogin Buttons
		@FXML
		private void loginaction(ActionEvent event) throws Exception {

			LoginDAOImpl login = new LoginDAOImpl();

			if(login.pruefeLogin(genBenu.getText(), genPass.getText())==true) {

				if(login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Kuechenpersonal") {
					Parent kueche_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/KuecheInterface.fxml"));
					Scene kueche_interface_scene = new Scene(kueche_interface_parent);
					Stage kueche_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
					kueche_stage.setScene(kueche_interface_scene);
					kueche_stage.show();

				}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Servicepersonal") {
					Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
					Scene ma_interface_scene = new Scene(ma_interface_parent);
					Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					ma_stage.setScene(ma_interface_scene);
					ma_stage.show();

				}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Barpersonal") {
					Parent bar_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
					Scene bar_interface_scene = new Scene(bar_interface_parent);
					Stage bar_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
					bar_stage.setScene(bar_interface_scene);
					bar_stage.show();

				}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Leiter"){
					Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
					Scene leiter_interface_scene = new Scene(leiter_interface_parent);
					Stage leiter_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
					leiter_stage.setScene(leiter_interface_scene);
					leiter_stage.show();

				}else {
					genPwEr.setText("Bitte wenden Sie sich an den Administrator...");
				}
			}else {
				genPwEr.setText("Benutzername oder Passwort falsch..");
			}
		}


	@FXML
	private void exitaction(ActionEvent event) throws IOException {

		System.exit(0);
	}

	//MaInterface Variablen & Button
	@FXML
	private Label lbltis;


	@FXML
	private void tisch1action(ActionEvent event) throws IOException {
		Parent tisch_anzeigen_parent = FXMLLoader.load(getClass().getResource("/fxml/TischAnzeigen.fxml"));
		Scene tisch_anzeigen_scene = new Scene(tisch_anzeigen_parent);
		Stage tisch_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		tisch_stage.setScene(tisch_anzeigen_scene);
		tisch_stage.show();
		lbltis.setText("Hallo");
	}

	@FXML
	private void tiszuruaction(ActionEvent event) throws IOException {
		Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
		Scene ma_interface_scene = new Scene(ma_interface_parent);
		Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ma_stage.setScene(ma_interface_scene);
		ma_stage.show();
	}
	
	@FXML
	private void leimenuaction() throws IOException{

	}

	@FXML
	private void showKonsumartikel() throws IOException{

	}
}