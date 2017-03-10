package extendedComponents;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollBarItem implements AdjustmentListener {
    public JScrollBar bar;
    public JCheckBox box;

    private static final int SUM = 100;
    private static final int MIN_SHIFT = 10;

    public ScrollBarItem(String checkBoxText) {
        box = new JCheckBox(checkBoxText);

        bar = createBar();
        bar.addAdjustmentListener(this);
    }

    private JScrollBar createBar() {
        JScrollBar bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 2, 0, SUM);
        bar.setBlockIncrement(10);
        bar.setUnitIncrement(10);

        return bar;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getValueIsAdjusting())
            return;

        int value = Math.round(e.getValue() * 1f / MIN_SHIFT) * MIN_SHIFT;
        e.getAdjustable().setValue(value);
    }
}
