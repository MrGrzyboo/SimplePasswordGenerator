import extendedComponents.NumericTextField;
import extendedComponents.ScrollBarItem;
import extendedComponents.SpecialCharsTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Grzyboo on 2017-03-02.
 */
public class MainWindow extends JFrame {
    private GridBagLayout layout;
    private JPanel panel;

    private Insets insets = new Insets(2, 2, 2, 2);

    private JLabel labelSymbolsList;
    private SpecialCharsTextField textFieldSymbolList;

    private NumericTextField textFieldPasswordLength;
    private NumericTextField textFieldPasswordsAmount;

    private JTextArea textAreaDisplay;

    private JButton buttonGeneratePassword;

    private MainOptionsPanel mainOptionsPanel;


    public void createItems() {

        prepareLayout();
        addMainOptionsPanel();
        addPossibleSymbolsField();
        addTextFields();
        addDisplayField();
        addGeneratePasswordButton();

        add(panel);
    }

    private void prepareLayout() {
        layout = new GridBagLayout();
        panel = new JPanel();
        panel.setLayout(layout);
    }

    private void addMainOptionsPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainOptionsPanel = new MainOptionsPanel();
        panel.add(mainOptionsPanel, gbc);
    }

    private void addPossibleSymbolsField() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.weighty = 1;
        gbc.gridy = 4; // First row

        labelSymbolsList = new JLabel("Symbols available:");
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        panel.add(labelSymbolsList, gbc);

        textFieldSymbolList = new SpecialCharsTextField("~`!@#$%^&*()_-+=[]{}|\\:;\"'<>?,./");
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(textFieldSymbolList, gbc);
    }

    private void addTextFields() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = insets;

        JLabel labelPasswordLength = new JLabel("Password length:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(labelPasswordLength, gbc);

        JLabel labelPasswordsAmount = new JLabel("Passwords amount:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(labelPasswordsAmount, gbc);


        gbc.insets.left = 25;
        gbc.insets.right = 25;

        textFieldPasswordLength = new NumericTextField(8, 1, 32);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(textFieldPasswordLength, gbc);

        textFieldPasswordsAmount = new NumericTextField(5, 0, 12);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(textFieldPasswordsAmount, gbc);
    }

    private void addDisplayField() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 20, 2, 20);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        textAreaDisplay = new JTextArea("The passwords will appear here :)");
        gbc.gridx = 0;
        gbc.gridy = 7;
        textAreaDisplay.setPreferredSize(new Dimension(textAreaDisplay.getPreferredSize().width, 200));
        panel.add(textAreaDisplay, gbc);

        textAreaDisplay.setEditable(false);
    }

    private void addGeneratePasswordButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 8;

        buttonGeneratePassword = new JButton("Generate");
        panel.add(buttonGeneratePassword, gbc);

        buttonGeneratePassword.addActionListener( e -> generate());
    }

    private void generate() {
        textAreaDisplay.setText("");

        int amountOfPasswords = textFieldPasswordsAmount.getValue();
        int passwordLength = textFieldPasswordLength.getValue();

        ArrayList<Character> possibleCharacters = new ArrayList<>();
        if(mainOptionsPanel.getCheckBox(CharactersType.LowerCase).isSelected()) {
            for(char c = 'a'; c <= 'z'; ++c) {
                int repeat = mainOptionsPanel.getScrollBar(CharactersType.LowerCase).getValue() / ScrollBarItem.MIN_SHIFT;
                for (int i = 0; i < repeat; ++i)
                    possibleCharacters.add(c);
            }
        }

        if(mainOptionsPanel.getCheckBox(CharactersType.UpperCase).isSelected()) {
            for(char c = 'A'; c <= 'Z'; ++c) {
                int repeat = mainOptionsPanel.getScrollBar(CharactersType.UpperCase).getValue() / ScrollBarItem.MIN_SHIFT;
                for (int i = 0; i < repeat; ++i)
                    possibleCharacters.add(c);
            }
        }

        if(mainOptionsPanel.getCheckBox(CharactersType.Numbers).isSelected()) {
            for(char c = '0'; c <= '9'; ++c) {
                int repeat = mainOptionsPanel.getScrollBar(CharactersType.Numbers).getValue() / ScrollBarItem.MIN_SHIFT;
                for (int i = 0; i < repeat; ++i)
                    possibleCharacters.add(c);
            }
        }

        if(mainOptionsPanel.getCheckBox(CharactersType.Symbols).isSelected()) {
            String symbols = textFieldSymbolList.getText();
            for(int id = 0; id < symbols.length(); ++id) {
                char c = symbols.charAt(id);

                int repeat = mainOptionsPanel.getScrollBar(CharactersType.Symbols).getValue() / ScrollBarItem.MIN_SHIFT;
                for (int i = 0; i < repeat; ++i)
                    possibleCharacters.add(c);
            }
        }

        Random random = new Random();
        int size = possibleCharacters.size();

        for(int i = 0; i < amountOfPasswords; ++i) {
            String text = "";
            for(int j = 0; j < passwordLength; ++j) {
                int index = random.nextInt(size);
                text += possibleCharacters.get(index);
            }
            textAreaDisplay.append(text + "\n");
        }
    }


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
