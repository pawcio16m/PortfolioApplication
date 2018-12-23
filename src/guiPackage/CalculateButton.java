package guiPackage;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CalculateButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final String BUTTON_NAME = "Calculate";
    
    public CalculateButton()
    {
        super(BUTTON_NAME);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

}
