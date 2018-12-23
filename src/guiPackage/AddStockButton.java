package guiPackage;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddStockButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final String BUTTON_NAME = "Add stock";
    private static final int X_POSITION = 15;
    private static final int Y_POSITION = 20;
    private static final int WIDTH = 97;
    private static final int HEIGHT = 25;
    

    public AddStockButton()
    {
        super(BUTTON_NAME);
      //  this.setBounds(X_POSITION, Y_POSITION, WIDTH, HEIGHT);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setVerticalAlignment(SwingConstants.TOP);
    }
}
