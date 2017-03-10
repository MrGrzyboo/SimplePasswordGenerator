import extendedComponents.NumericTextField;
import extendedComponents.SpecialCharsTextField;

import javax.swing.*;
import java.awt.*;

class MainWindow extends JFrame {
    private JPanel panel;

    private MainOptionsPanel mainOptionsPanel;

    private SpecialCharsTextField textFieldSymbolList;
    private NumericTextField textFieldPasswordLength;
    private NumericTextField textFieldPasswordsAmount;
    private JTextArea textAreaDisplay;

    private Insets standardInsets = new Insets(2, 2, 2, 2);

    MainWindow() {
        super();
        createComponents();
    }

    private void createComponents() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);

        addTopLabels();
        addMainOptionsPanel();
        addSymbolsField();
        addNumericFields();
        addDisplayField();
        addGeneratePasswordButton();
    }

    private void addTopLabels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = standardInsets;
        gbc.weighty = 0;
        gbc.gridy = 0;

        JLabel labelCheckboxes = new JLabel("Used character:");
        gbc.gridx = 0;
        panel.add(labelCheckboxes, gbc);

        JLabel labelProbabilities = new JLabel("Probabilities:");
        gbc.gridx = 1;
        panel.add(labelProbabilities, gbc);
    }

    private void addMainOptionsPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainOptionsPanel = new MainOptionsPanel();
        panel.add(mainOptionsPanel, gbc);
    }

    private void addSymbolsField() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = standardInsets;
        gbc.weighty = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel labelSymbolsList = new JLabel("Symbols available:");
        gbc.gridx = 0;
        gbc.weightx = 0;
        panel.add(labelSymbolsList, gbc);

        textFieldSymbolList = new SpecialCharsTextField("~`!@#$%^&*()_-+=[]{}|\\:;\"'<>?,./");
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(textFieldSymbolList, gbc);
    }

    private void addNumericFields() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = standardInsets;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel labelPasswordLength = new JLabel("Password length:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelPasswordLength, gbc);

        JLabel labelPasswordsAmount = new JLabel("Passwords amount:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelPasswordsAmount, gbc);



        textFieldPasswordLength = new NumericTextField(8, 1, 64);
        textFieldPasswordLength.setColumns(2);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(textFieldPasswordLength, gbc);

        textFieldPasswordsAmount = new NumericTextField(5, 0, 25);
        textFieldPasswordsAmount.setColumns(2);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(textFieldPasswordsAmount, gbc);
    }

    private void addDisplayField() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 20, 2, 20);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;

        textAreaDisplay = new JTextArea("The passwords will appear here :)");
        gbc.gridx = 0;
        gbc.gridy = 5;
        textAreaDisplay.setPreferredSize(new Dimension(textAreaDisplay.getPreferredSize().width, 200));
        panel.add(textAreaDisplay, gbc);

        textAreaDisplay.setEditable(false);
    }

    private void addGeneratePasswordButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = standardInsets;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 6;

        JButton buttonGeneratePassword = new JButton("Generate");
        panel.add(buttonGeneratePassword, gbc);

        buttonGeneratePassword.addActionListener( e -> generate());
    }

    private void generate() {
        Generator generator = new Generator(textFieldSymbolList, mainOptionsPanel);

        int passwordsAmount = textFieldPasswordsAmount.getValue();
        int passwordLength = textFieldPasswordLength.getValue();

        textAreaDisplay.setText("");

        String[] passwords = generator.generate(passwordsAmount, passwordLength);
        for(String password : passwords)
            textAreaDisplay.append(password + "\n");
    }

    // Tests if the probabilities are okay.
    /*private void test() {
        int lower = 0;
        int upper = 0;
        int numbers = 0;
        int symbols = 0;

        for(int i = 0; i < 100; ++i) {
            generate();
            String text = textAreaDisplay.getText();
            text = text.replaceAll("\n", "");

            for(int j = 0; j < text.length(); ++j) {
                char c = text.charAt(j);
                if(c >= 'a' && c <= 'z') ++lower;
                if(c >= 'A' && c <= 'Z') ++upper;
                if(c >= '0' && c <= '9') ++numbers;
                if(textFieldSymbolList.getText().contains(String.valueOf(c)))
                    ++symbols;
            }
        }

        int together = lower + upper + numbers + symbols;
        System.out.println("lower = " + (float)lower / together);
        System.out.println("upper = " + (float)upper / together);
        System.out.println("numbers = " + (float)numbers / together);
        System.out.println("symbols = " + (float)symbols / together);
    }*/
}
