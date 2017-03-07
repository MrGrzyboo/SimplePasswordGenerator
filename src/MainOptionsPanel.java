import extendedComponents.ScrollBarItem;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * Created by Grzyboo on 2017-03-06.
 */
public class MainOptionsPanel extends JPanel {
    public EnumMap<CharactersType, ScrollBarItem> scrollBars;

    private static final int SUM = 100;

    public MainOptionsPanel() {
        setLayout(new GridBagLayout());

        scrollBars = new EnumMap<>(CharactersType.class);

        addComponents();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START; // allign everything to left

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

    public JScrollBar getScrollBar(CharactersType type) {
        return scrollBars.get(type).bar;
    }

    public JCheckBox getCheckBox(CharactersType type) {
        return scrollBars.get(type).box;
    }

}
