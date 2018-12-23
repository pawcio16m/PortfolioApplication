package mainPackage;

import java.awt.EventQueue;

import guiPackage.MainWindow;

public class MainApplication
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try
                {
                    MainWindow window = new MainWindow();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
