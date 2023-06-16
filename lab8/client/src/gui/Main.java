package gui;

import commandManager.CommandLoaderUtility;
import gui.login.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverLogic.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static final int PORT = 50457;
    private static final Logger logger = LogManager.getLogger("lab8");
    private LocalizationUtility locally;

    @Override
    public void start(Stage stage) {
        LoginWindow loginWindow = new LoginWindow(stage);
        loginWindow.show();
    }

    public static void main(String[] args) {
        try {
            ServerConnection connection = new UdpConnectionBlockDecorator((UdpServerConnection) new UdpServerConnectionFactory().openConnection(InetAddress.getLocalHost(), PORT), true);
            ServerConnectionHandler.setServerConnection(connection);
            connection.openConnection();

            launch(args);
        } catch (IOException e) {
            logger.info("Can't connect to the server", e);
            System.err.println("Can't connect to the server!");
        }
    }
}

