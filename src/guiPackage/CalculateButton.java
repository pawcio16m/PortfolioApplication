package guiPackage;

import javax.swing.JButton;

public class CalculateButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final String BUTTON_NAME = "Calculate";
    private static final int X_POSITION = 15;
    private static final int Y_POSITION = 400;
    private static final int WIDTH = 97;
    private static final int HEIGHT = 20;
    
    public CalculateButton()
    {
        super(BUTTON_NAME);
  //      this.setBounds(X_POSITION, Y_POSITION, WIDTH, HEIGHT);
    }

}
