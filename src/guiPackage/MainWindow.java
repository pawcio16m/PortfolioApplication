package guiPackage;

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
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import backendPackage.IResultCalculator;
import backendPackage.ResultCalculator;
import backendPackage.ResultData;

public class MainWindow
{
    private static final String APP_NAME = "Portfolio calculation";

    private JFrame frame;
    
    //Stock Form
    private JLabel stockNameDisplay = new JLabel("Stock Name");
    private JTextField stockNameValue = new JTextField("StockName");
    
    private JLabel actualPriceDisplay = new JLabel("Actual Price [$]");
    private JTextField actualPriceValue = new JTextField("10.5");
    
    private JLabel returnOfInvestmentDisplay = new JLabel("Return of Investment [%]");
    private JTextField returnOfInvestmentValue = new JTextField("5.5");
    
    private JLabel volatilityRateDisplay = new JLabel("Volatility Rate [%]");
    private JTextField volatilityRateValue = new JTextField("2");
    
    private JLabel weightOfStockDisplay = new JLabel("Weight of Stock [%]");
    private JTextField weightOfStockValue = new JTextField("25");
    
    private JLabel timeOfInvestmentDisplay = new JLabel("Time of investment [year]");
    private JTextField timeOfInvestmentValue = new JTextField("5");
    
  
    //Buttons
    private JButton fillDataButton = new AddStockButton();
    private JButton calculateButton = new CalculateButton();
    private JButton deleteStockButton = new DeleteStockButton();   
    
    //Tables
    private StocksTable stocksData;
    private ResultsTable resultsData;
    
    //Panels
    private JPanel stockName = new JPanel();
    private JPanel actualPrice = new JPanel();
    private JPanel returnOfInvestment = new JPanel();
    private JPanel volatilityRate = new JPanel();
    private JPanel weightOfStock = new JPanel();
    private JPanel timeOfInvestment = new JPanel();
    private JPanel stockFormPanel = new JPanel();
    private JPanel stocksPanel = new JPanel();
    private JPanel dataPanel = new JPanel();
    private JPanel resultsPanel = new JPanel();
    private JPanel chartPanel = new JPanel();
    
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
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        frame.getContentPane().add(dataPanel);
        frame.getContentPane().add(resultsPanel);
        frame.getContentPane().add(chartPanel);
       
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareStockFormPanel()
    {   
        stockName.setLayout(new BoxLayout(stockName, BoxLayout.X_AXIS));
        stockNameDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        stockName.add(stockNameDisplay);
        stockNameValue.setHorizontalAlignment(SwingConstants.CENTER);
        stockNameValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        stockName.add(stockNameValue);
        
        actualPrice.setLayout(new BoxLayout(actualPrice, BoxLayout.X_AXIS));
        actualPriceDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        actualPrice.add(actualPriceDisplay);
        actualPriceValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        actualPriceValue.setHorizontalAlignment(SwingConstants.CENTER);
        actualPrice.add(actualPriceValue);
        
        returnOfInvestment.setLayout(new BoxLayout(returnOfInvestment, BoxLayout.X_AXIS));
        returnOfInvestmentDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        returnOfInvestment.add(returnOfInvestmentDisplay);
        returnOfInvestmentValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        returnOfInvestmentValue.setHorizontalAlignment(SwingConstants.CENTER);
        returnOfInvestment.add(returnOfInvestmentValue);

        volatilityRate.setLayout(new BoxLayout(volatilityRate, BoxLayout.X_AXIS));
        volatilityRateDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        volatilityRate.add(volatilityRateDisplay);
        volatilityRateValue.setHorizontalAlignment(SwingConstants.CENTER);
        volatilityRateValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        volatilityRate.add(volatilityRateValue);
        
        weightOfStock.setLayout(new BoxLayout(weightOfStock, BoxLayout.X_AXIS));
        weightOfStockDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        weightOfStock.add(weightOfStockDisplay);
        weightOfStockValue.setHorizontalAlignment(SwingConstants.CENTER);
        weightOfStockValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        weightOfStock.add(weightOfStockValue);

        timeOfInvestment.setLayout(new BoxLayout(timeOfInvestment, BoxLayout.X_AXIS));
        timeOfInvestmentDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        timeOfInvestment.add(timeOfInvestmentDisplay);
        timeOfInvestmentValue.setHorizontalAlignment(SwingConstants.CENTER);
        timeOfInvestmentValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        timeOfInvestment.add(timeOfInvestmentValue);
        
        stockFormPanel.setLayout(new BoxLayout(stockFormPanel, BoxLayout.Y_AXIS));
        stockFormPanel.add(stockName);
        stockFormPanel.add(actualPrice);
        stockFormPanel.add(returnOfInvestment);
        stockFormPanel.add(volatilityRate);
        stockFormPanel.add(weightOfStock);
        stockFormPanel.add(timeOfInvestment);
        stockFormPanel.add(fillDataButton);
        stockFormPanel.add(deleteStockButton);         
    }
    
    private void preparePanels()
    {
        prepareStockFormPanel();        
        
        FlowLayout flowLayout = (FlowLayout) stocksPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        stocksPanel.add(stocksData);
        
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
        dataPanel.add(stockFormPanel);
        dataPanel.add(stocksPanel);
        
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.add(calculateButton);
        resultsPanel.add(resultsData);        
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
                                           Double.parseDouble(volatilityRateValue.getText()),
                                           Double.parseDouble(weightOfStockValue.getText()));     
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
                try
                { 
                    if (stocksData.getData().size() > 0)
                    {    
                        Vector<ResultData> results = resultCalculator.calculate(stocksData.getData(), Integer.parseInt(timeOfInvestmentValue.getText()));                
                        resultsData.fillResults(results);
                    }
                    else
                    {
                        System.err.println("Input data not filled - cannot calculate!");     
                    }
                }
                catch (NumberFormatException exception)
                {
                    System.err.println("Invalid data format - cannot calculate!");
                } 
            }
        });
    }    
}
