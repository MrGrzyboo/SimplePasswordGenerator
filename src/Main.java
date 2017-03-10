import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setTitle("Password Generator");
            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            mainWindow.pack();
            mainWindow.setVisible(true);
        });
    }
}
