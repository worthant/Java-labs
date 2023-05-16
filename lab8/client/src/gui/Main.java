package gui;

import commandManager.CommandLoaderUtility;
import gui.login.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import serverLogic.*;

import java.net.InetAddress;

public class Main extends Application {
    public static final int PORT = 50457;

    @Override
    public void start(Stage primaryStage) {
        try {
            //ServerConnection connection = new UdpServerConnectionFactory().openConnection(InetAddress.getByName(HOST_ADDRESS), PORT);
            ServerConnection connection = new UdpConnectionBlockDecorator((UdpServerConnection) new UdpServerConnectionFactory().openConnection(InetAddress.getLocalHost(), PORT), true);
            ServerConnectionHandler.setServerConnection(connection);
            connection.openConnection();

            LoginWindow loginWindow = new LoginWindow();
            loginWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

