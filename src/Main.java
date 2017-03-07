import javax.swing.*;

/**
 * Created by Grzyboo on 2017-03-02.
 */
public class Main {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setTitle("Password Generator");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.createItems();

        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
