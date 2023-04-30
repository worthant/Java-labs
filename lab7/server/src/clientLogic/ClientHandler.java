package clientLogic;

import exceptions.NotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientHandler implements ActionListener {
    private static final Logger logger = LogManager.getLogger("com.github.worthant.lab6");
    private static ClientHandler instance;
    private final javax.swing.Timer timer;
    boolean availability = true;
    private CallerBack callerBack;

    private ClientHandler() {
        timer = new Timer(300000, this);
    }

    public static ClientHandler getInstance() {
        if (instance == null)
            instance = new ClientHandler();
        return instance;
    }

    public void approveCallerBack(CallerBack callerBack) throws NotAvailableException {
        if (availability || this.callerBack.equals(callerBack)) {
            this.callerBack = callerBack;
            availability = true;
            timer.start();
        } else throw new NotAvailableException(callerBack);
    }

    public boolean isAvailability() {
        return availability;
    }

    public void allowNewCallerBack() {
        availability = true;
    }

    public void restartTimer() {
        logger.info("Timer restarted!");
        this.timer.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.debug("Allowed new connection.");
        allowNewCallerBack();
    }
}
