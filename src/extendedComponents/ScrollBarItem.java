package extendedComponents;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by Grzyboo on 2017-03-06.
 */
public class ScrollBarItem implements AdjustmentListener {
    public JScrollBar bar;
    public JCheckBox box;

    public static final int SUM = 100;
    public static final int MIN_SHIFT = 10;

    public ScrollBarItem(String checkBoxText) {
        box = new JCheckBox(checkBoxText);

        bar = createBar();
        bar.addAdjustmentListener(this);
    }

    public ScrollBarItem() {
        this("");
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

    public static int getMinimalShift() {
        return MIN_SHIFT;
    }
}
