package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.Main.connect;

/**
 * obsluga ekranu logowania
 */
public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String login;
    private String password;

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text warning;

    /**
     * sprawdza czy dane do logowania zostaly podane i wysayla je do serwera
     * @param event
     * @throws IOException
     */
    public void onZalogujButtonClick(ActionEvent event) throws IOException {

        login = loginField.getText();
        password = passwordField.getText();

        if(login.equals("") && password.equals(""))
        {
            warning.setText("Wprowadź login i hasło");
        }
        else if(login.equals(""))
        {
            warning.setText("Wprowadź login");
        }
        else if(password.equals(""))
        {
            warning.setText("Wprowadź hasło");
        }
        else
        {
            try
            {
                connect.sendMessage("login,"+login+","+password);

                if (connect.readMessage().equals("true"))
                {
                    root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    warning.setText("Błędne dane");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * sprawdza czy dane do rejestracji zostaly podane i wysayla je do serwera
     * @param event
     * @throws IOException
     */
    public void onZarejestrujButtonClick(ActionEvent event) throws IOException {

        login = loginField.getText();
        password = passwordField.getText();

        if(login.equals("") && password.equals(""))
        {
            warning.setText("Wprowadź login i hasło");
        }
        else if(login.equals(""))
        {
            warning.setText("Wprowadź login");
        }
        else if(password.equals(""))
        {
            warning.setText("Wprowadź hasło");
        }
        else
        {
            try
            {
                connect.sendMessage("register,"+login+","+password);

                if (connect.readMessage().equals("true"))
                {
                    warning.setText("Utworzono konto");
                }
                else
                {
                    warning.setText("Podany login jest zajęty");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}