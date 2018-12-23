package guiPackage;

import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import backendPackage.StockData;

public class StocksTable extends JScrollPane
{
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = {"Stock Name", "Actual Value", "Return Of Investment", "Volatility Rate", "Weight Of Stock"};
    
    private static DefaultTableModel tableModel = new DefaultTableModel();
    private static JTable stockTable = new JTable(tableModel);
    
    public StocksTable()
    {
        super(stockTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);        
        tableModel.setColumnIdentifiers(COLUMN_NAMES);
        stockTable.setFillsViewportHeight(true); 
    }
    
    public void addRowTable(String stockName, double actualValue, double returnOfInvestment, double volatilityRate, double weightOfStock)
    {
        tableModel.addRow(new Object[]{stockName, actualValue, returnOfInvestment, volatilityRate, weightOfStock});
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
    
    public Vector<StockData> getData() throws NumberFormatException
    {
        int stockDataSize = tableModel.getRowCount();
        Vector<StockData> stockData = new Vector<StockData>(stockDataSize);
        
        double sumOfWeight = 0.0;
        for(int rowNumber = 0; rowNumber < stockDataSize; ++rowNumber)
        {
            String stockName = (String) tableModel.getValueAt(rowNumber, 0);
            double actualValue = (double) tableModel.getValueAt(rowNumber, 1);
            double returnOfInvestment = (double) tableModel.getValueAt(rowNumber, 2);
            double volatilityRate = (double) tableModel.getValueAt(rowNumber, 3);
            double weightOfStock = (double) tableModel.getValueAt(rowNumber, 4);
            sumOfWeight += weightOfStock;
            
            StockData stock = new StockData(stockName, actualValue, returnOfInvestment, volatilityRate, weightOfStock);
            stockData.add(stock);
        }
        if (sumOfWeight < 99.0 || sumOfWeight > 100.0) throw new NumberFormatException();
        return stockData;        
    }
}
