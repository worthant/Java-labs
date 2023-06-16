package gui.login;

import client.Client;
import gui.AlertUtility;
import gui.UTF8Control;
import gui.collections.CollectionsWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import requestLogic.requestSenders.AuthRequestSender;
import requestLogic.requestSenders.RegRequestSender;
import responses.AuthResponse;
import responses.RegResponse;
import serverLogic.ServerConnectionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginWindowController {
    private ResourceBundle currentBundle;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label accountLabel;
    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("en", "NZ"),
            new Locale("ru"),
            new Locale("hr"),
            new Locale("cs")
    );
    private int currentLocaleIndex = 0;

    @FXML
    public void initialize() {
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
        updateUI();
    }

    /**
     * Update LoginWindow UI
     */
    private void updateUI() {
        accountLabel.setText(currentBundle.getString("accountLabel"));
        welcomeLabel.setText(currentBundle.getString("welcomeLabel"));
        detailsLabel.setText(currentBundle.getString("detailsLabel"));
        signInButton.setText(currentBundle.getString("signInButton"));
        signUpLabel.setText(currentBundle.getString("signUpLabel"));
        usernameLabel.setText(currentBundle.getString("usernameLabel"));
        passwordLabel.setText(currentBundle.getString("passwordLabel"));
    }

    @FXML
    protected void onGeoIconClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
        updateUI();
    }

    @FXML
    protected void onSignInButtonClick() {
        try {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getText().trim().toCharArray();

            AuthRequestSender rqSender = new AuthRequestSender();
            AuthResponse response = rqSender.sendAuthData(username, password, ServerConnectionHandler.getCurrentConnection());

            if (response.isAuth()) {
                Client.getInstance(username, password);
                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();


                CollectionsWindow collectionsWindow = new CollectionsWindow(currentLocaleIndex);
                collectionsWindow.show();
            } else {
                AlertUtility.errorAlert("There is no user with this name, or password is incorrect");
            }
        } catch (Exception e) {
            //errorAlert("Server is dead :(");
        }
    }

    @FXML
    protected void onSignUpLabelClick() {
        try {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getText().trim().toCharArray();

            RegRequestSender rqSender = new RegRequestSender();
            RegResponse response = rqSender.sendRegData(username, password, ServerConnectionHandler.getCurrentConnection());

            if (response.isReg()) {
                Client.getInstance(username, password);
                AlertUtility.infoAlert("User successfully registered");
            } else {
                AlertUtility.errorAlert("There is no user with this name, or password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

