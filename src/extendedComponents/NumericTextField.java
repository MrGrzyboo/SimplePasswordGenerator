package extendedComponents;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Grzyboo on 2017-03-05.
 */
public class NumericTextField extends JTextField implements FocusListener {
    private int lowest;
    private int highest;

    public NumericTextField(int currentValue, int lowest, int highest) {
        super();

        if(lowest > highest) {
            int value = lowest;
            lowest = highest;
            highest = value;
        }

        this.lowest = lowest;
        this.highest = highest;

        currentValue = verifyRange(currentValue);
        setText(String.valueOf(currentValue));

        PlainDocument doc = (PlainDocument) getDocument();
        doc.setDocumentFilter(new Filter());

        addFocusListener(this);
    }

    private int verifyRange(int value) {
        if(value < lowest)
            value = lowest;
        if(value > highest)
            value = highest;

        return value;
    }

    public int getValue() {
        try {
            return Integer.parseInt(getText());
        } catch(NumberFormatException e) {
            return lowest;
        }
    }

    private class Filter extends DocumentFilter {

        private String filterText(String text) {
            int value;

            try {
                value = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                return String.valueOf("");
            }

            return String.valueOf(verifyRange(value));
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            int charsToReplace = sb.toString().length();
            sb.replace(offset, offset + length, text);
            String properText = filterText(sb.toString());

            super.replace(fb, 0, charsToReplace, properText, attrs);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
        if(getText().length() == 0)
            setText(String.valueOf(lowest));
    }
}
