package guiPackage;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import frontendPackage.IResultCalculator;
import frontendPackage.ResultCalculator;
import frontendPackage.ResultData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainWindow
{
    private JFrame frame;
    private JPanel mainPane;
    private static final String APP_NAME = "Portfolio calculation";
    
    //Stock Form
    private JLabel stockNameDisplay = new JLabel("Stock Name");
    private JTextField stockNameValue = new JTextField("StockName");
    
    private JLabel actualPriceDisplay = new JLabel("Actual Price");
    private JTextField actualPriceValue = new JTextField("10.5");
    
    private JLabel returnOfInvestmentDisplay = new JLabel("Return of Investment");
    private JTextField returnOfInvestmentValue = new JTextField("5.5");
    
    private JLabel volatibilityRateDisplay = new JLabel("Volability Rate");
    private JTextField volatibilityRateValue = new JTextField("2");
    
    private JLabel timeOfInvestmentDisplay = new JLabel("Time of investment");
    private JTextField timeOfInvestmentValue = new JTextField("5");
    
    private static final int FORM_WIDTH = 180;
    private static final int FORM_HEIGHT = 20;
    
    //Buttons
    private JButton fillDataButton = new AddStockButton();
    private JButton calculateButton = new CalculateButton();
    private JButton deleteStockButton = new DeleteStockButton();   
    
    //Tables
    private StocksTable stocksData;
    private ResultsTable resultsData;
    
    //Panels
    private JPanel stockName;
    private JPanel actualPrice;
    private JPanel returnOfInvestment;
    private JPanel volatibilityRate;
    private JPanel timeOfInvestment;
    private JPanel stockFormPanel;
    private JPanel stocksPanel;
    private JPanel dataPanel;
    private JPanel resultsPanel;
    private JPanel chartPanel;
    
    //Algorithm
    private IResultCalculator resultCalculator = new ResultCalculator();

    public MainWindow()
    {
        initialize();
        addButtonListeners();
    }

    private void initialize()
    {
        //Frame
        frame = new JFrame(getClass().getSimpleName());
        frame.setBounds(10, 10, 1200, 1000);
        frame.setTitle(APP_NAME);
    
        //Tables
        stocksData = new StocksTable();
        stocksData.setPreferredSize(new Dimension(600, 200));
        stocksData.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resultsData = new ResultsTable();
      
        //Chart        
        //TODO implement it :)

        //Panels        
        preparePanels();
         
        //Layout
        mainPane = new JPanel();
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        frame.getContentPane().add(dataPanel);
       // frame.getContentPane().add(stocksPanel);
        frame.getContentPane().add(resultsPanel);
        frame.getContentPane().add(chartPanel);
       
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareStockFormPanel()
    {   
        stockName = new JPanel();
        stockName.setLayout(new BoxLayout(stockName, BoxLayout.X_AXIS));
        stockNameDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        stockName.add(stockNameDisplay);
        stockNameValue.setHorizontalAlignment(SwingConstants.CENTER);
        stockNameValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        stockName.add(stockNameValue);
     //   stockName.setSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));
        
        actualPrice = new JPanel();
        actualPrice.setLayout(new BoxLayout(actualPrice, BoxLayout.X_AXIS));
        actualPriceDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        actualPrice.add(actualPriceDisplay);
        actualPriceValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        actualPriceValue.setHorizontalAlignment(SwingConstants.CENTER);
        actualPrice.add(actualPriceValue);
     //   actualPrice.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));
        
        returnOfInvestment = new JPanel();
        returnOfInvestment.setLayout(new BoxLayout(returnOfInvestment, BoxLayout.X_AXIS));
        returnOfInvestmentDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        returnOfInvestment.add(returnOfInvestmentDisplay);
        returnOfInvestmentValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        returnOfInvestmentValue.setHorizontalAlignment(SwingConstants.CENTER);
        returnOfInvestment.add(returnOfInvestmentValue);
     //   returnOfInvestment.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));      

        volatibilityRate = new JPanel();
        volatibilityRate.setLayout(new BoxLayout(volatibilityRate, BoxLayout.X_AXIS));
        volatibilityRateDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        volatibilityRate.add(volatibilityRateDisplay);
        volatibilityRateValue.setHorizontalAlignment(SwingConstants.CENTER);
        volatibilityRateValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        volatibilityRate.add(volatibilityRateValue);
  //      volatibilityRate.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));            

        timeOfInvestment = new JPanel();
        timeOfInvestment.setLayout(new BoxLayout(timeOfInvestment, BoxLayout.X_AXIS));
        timeOfInvestmentDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        timeOfInvestment.add(timeOfInvestmentDisplay);
        timeOfInvestmentValue.setHorizontalAlignment(SwingConstants.CENTER);
        timeOfInvestmentValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        timeOfInvestment.add(timeOfInvestmentValue);
  //      timeOfInvestment.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));
        
        stockFormPanel.setLayout(new BoxLayout(stockFormPanel, BoxLayout.Y_AXIS));
        stockFormPanel.add(stockName);
        stockFormPanel.add(actualPrice);
        stockFormPanel.add(returnOfInvestment);
        stockFormPanel.add(volatibilityRate);
        stockFormPanel.add(timeOfInvestment);
        stockFormPanel.add(fillDataButton);
        stockFormPanel.add(deleteStockButton);         
     //   stockFormPanel.setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT*7));


    }
    
    private void preparePanels()
    {
        stockFormPanel = new JPanel();
        prepareStockFormPanel();        
        
        stocksPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) stocksPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        stocksPanel.add(stocksData);
        
        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
        dataPanel.add(stockFormPanel);
        dataPanel.add(stocksPanel);
      //  dataPanel.setSize(new Dimension(FORM_WIDTH*5, FORM_HEIGHT*18));
        
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        calculateButton.setHorizontalAlignment(SwingConstants.LEFT);
        resultsPanel.add(calculateButton);
        resultsPanel.add(resultsData);
        
        chartPanel = new JPanel(); 
    }
    
    
    private void addButtonListeners() {
        fillDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    stocksData.addRowTable(stockNameValue.getText(), 
                                           Double.parseDouble(actualPriceValue.getText()),
                                           Double.parseDouble(returnOfInvestmentValue.getText()),
                                           Double.parseDouble(volatibilityRateValue.getText()));     
                }
                catch (NumberFormatException exception)
                {
                    System.err.println("Invalid data format - stock not added!");
                }
            }
        });
        
        deleteStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(stocksData.removeRowTable())
                {
                    System.out.println("Stock deleted from Portfolio!");
                }
                else
                {
                    System.err.println("Stock not selected - abort delete operation!");
                }
            }
        });
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //This is a main part when algorithm is launch and results are printed
                if (stocksData.getData().size() > 0)
                {
                    try
                    { 
                        Vector<ResultData> results = resultCalculator.calculate(stocksData.getData(), Integer.parseInt(timeOfInvestmentValue.getText()));                
                        resultsData.fillResults(results);
                        return;
   
                    }
                    catch (NumberFormatException exception)
                    {
                        System.err.println("Invalid data format - cannot calculate!");
                    } 
                }
                System.err.println("Input data not filled - cannot calculate!");               
            }
        });
    }    
}
