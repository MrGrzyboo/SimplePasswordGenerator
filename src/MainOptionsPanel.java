import extendedComponents.ScrollBarItem;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

class MainOptionsPanel extends JPanel {
    private EnumMap<CharactersType, ScrollBarItem> scrollBars;

    private static final int SUM = 100;

    MainOptionsPanel() {
        setLayout(new GridBagLayout());
        scrollBars = new EnumMap<>(CharactersType.class);

        addComponents();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        int i = 0;
        for (CharactersType type : CharactersType.values()) {
            ScrollBarItem item = new ScrollBarItem(type.getName());
            scrollBars.put(type, item);

            int value = Math.round(type.getPercent() * SUM);
            item.bar.setValue(value);

            gbc.gridy = i;

            // Add box
            gbc.gridx = 0;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            add(item.box, gbc);

            // Add bar
            gbc.gridx = 1;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(item.bar, gbc);

            ++i;
        }

        scrollBars.get(CharactersType.LowerCase).box.setSelected(true);
    }

    float[] getProbabilities() {
        int sum = 0;
        for(CharactersType loopType : CharactersType.values()) {
            ScrollBarItem sumItem = scrollBars.get(loopType);
            if(sumItem.box.isSelected())
                sum += sumItem.bar.getValue();
        }

        float probabilities[] = new float[CharactersType.values().length];

        for(CharactersType type : CharactersType.values()) {
            probabilities[type.ordinal()] = getProbability(type, sum);
        }

        return probabilities;
    }

    private float getProbability(CharactersType type, int sum) {
        ScrollBarItem item = scrollBars.get(type);
        if(item.box.isSelected())
            return (float)item.bar.getValue() / sum;

        return 0;
    }

}
