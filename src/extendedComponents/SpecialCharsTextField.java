package extendedComponents;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * Created by Grzyboo on 2017-03-05.
 */
public class SpecialCharsTextField extends JTextField {

    public SpecialCharsTextField(String text) {
        super(text);

        PlainDocument doc = (PlainDocument) getDocument();
        doc.setDocumentFilter(new Filter());
    }

    private class Filter extends DocumentFilter {
        private boolean test(String text) {
            /*try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }*/

            for(int i = 0; i < text.length(); ++i) {
                char c = text.charAt(i);

                if(c >= 'A' && c <= 'Z')
                    return false;

                if(c >= 'a' && c <= 'z')
                    return false;

                if(c >= '0' && c <= '9')
                    return false;

                for(int j = 0; j < i; ++j) // Duplicate
                    if(text.charAt(j) == c)
                        return false;
            }

            return true;
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString()))
                super.remove(fb, offset, length);
            else
                Toolkit.getDefaultToolkit().beep();
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString()))
                super.insertString(fb, offset, string, attr);
            else
                Toolkit.getDefaultToolkit().beep();
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString()))
                super.replace(fb, offset, length, text, attrs);
            else
                Toolkit.getDefaultToolkit().beep();

        }
    }
}
