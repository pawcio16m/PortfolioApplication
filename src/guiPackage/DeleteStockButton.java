package guiPackage;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DeleteStockButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final String BUTTON_NAME = "Delete selected stock";
    private static final int X_POSITION = 15;
    private static final int Y_POSITION = 20;
    private static final int WIDTH = 97;
    private static final int HEIGHT = 20;
    

    public DeleteStockButton()
    {
        super(BUTTON_NAME);
   //     this.setBounds(X_POSITION, Y_POSITION, WIDTH, HEIGHT);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }
}
