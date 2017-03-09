import extendedComponents.SpecialCharsTextField;

import java.util.Random;

/**
 * Created by Grzyboo on 2017-03-08.
 */
public class Generator {
    private SpecialCharsTextField textField;
    private MainOptionsPanel panel;

    public Generator(SpecialCharsTextField textField, MainOptionsPanel panel) {
        this.textField = textField;
        this.panel = panel;
    }

    public String[] generate(int amount, int length) {
        String[] passwords = new String[amount];
        float[] probabilities = panel.getProbabilities();
        char[] symbols = getSymbolsArray();

        for(int i = 0; i < amount; ++i) {
            StringBuffer buffer = new StringBuffer(length);
            for(int j = 0; j < length; ++j) {
                int index = randomize(probabilities);
                switch (index) {
                    case 0:
                        buffer.append((char) randomNum('a', 'z'));
                        break;
                    case 1:
                        buffer.append((char) randomNum('A', 'Z'));
                        break;

                    case 2: {
                        buffer.append((char) randomNum('0', '9'));
                        break;
                    }
                    case 3: {
                        int randomChar = randomNum(0, symbols.length - 1);
                        buffer.append(symbols[randomChar]);
                        break;
                    }
                }
            }

            passwords[i] = buffer.toString();
        }

        return passwords;
    }

    private char[] getSymbolsArray() {
        String symbolsText = textField.getText();
        char[] symbols = new char[symbolsText.length()];

        for(int i = 0; i < symbolsText.length(); ++i)
            symbols[i] = symbolsText.charAt(i);

        return symbols;
    }


    private int randomize(float[] chances) {
        float ranges[] = new float[chances.length];

        float sum = chances[0];

        int i;
        for(i = 0; i < chances.length - 1; ++i) {
            ranges[i] = sum;
            sum += chances[i+1];
        }
        ranges[i] = sum;

        Random random = new Random();
        float result = random.nextFloat();

        if(result <= ranges[0])
            return 0;

        for(i = 1; i < ranges.length; ++i) {
            if(result <= ranges[i]) {
                return i;
            }
        }

        return -1;
    }

    private int randomNum(int a, int b) {
        Random random = new Random();

        return random.nextInt(b - a + 1) + a;
    }
}
