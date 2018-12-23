package guiPackage;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddStockButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final String BUTTON_NAME = "Add stock";

    public AddStockButton()
    {
        super(BUTTON_NAME);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setVerticalAlignment(SwingConstants.TOP);
    }
}
