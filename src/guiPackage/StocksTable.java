package guiPackage;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import frontendPackage.StockData;

public class StocksTable extends JScrollPane
{
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = {"Stock Name", "Actual Value", "Return Of Investment", "Volatibility Rate"};
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    
    private static DefaultTableModel tableModel = new DefaultTableModel();
    private static JTable stockTable = new JTable(tableModel);
    
    public StocksTable()
    {
        super(stockTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        tableModel.setColumnIdentifiers(COLUMN_NAMES);
   //     stockTable.setPreferredScrollableViewportSize(new Dimension(WIDTH, HEIGHT));
        stockTable.setFillsViewportHeight(true); 
    }
    
    public void addRowTable(String stockName, double actualValue, double returnOfInvestment, double volatibilityRate)
    {
        tableModel.addRow(new Object[]{stockName, actualValue, returnOfInvestment, volatibilityRate});
    }
    
    public boolean removeRowTable()
    {
        int rowNumber = stockTable.getSelectedRow();
        if (rowNumber >= 0)
        {
            tableModel.removeRow(rowNumber);
            return true;
        }
        return false;
    }
    
    public Vector<StockData> getData()
    {
        int stockDataSize = tableModel.getRowCount();
        Vector<StockData> stockData = new Vector<StockData>(stockDataSize);
        
        for(int rowNumber = 0; rowNumber < stockDataSize; ++rowNumber)
        {
            String stockName = (String) tableModel.getValueAt(rowNumber, 0);
            double actualValue = (double) tableModel.getValueAt(rowNumber, 1);
            double returnOfInvestment = (double) tableModel.getValueAt(rowNumber, 2);
            double volatibilityRate = (double) tableModel.getValueAt(rowNumber, 3);
            
            StockData stock = new StockData(stockName, actualValue, returnOfInvestment, volatibilityRate);
          //  System.out.println(stock.toString());
            stockData.add(stock);
        }
        return stockData;        
    }
}
