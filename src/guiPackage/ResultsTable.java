package guiPackage;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import frontendPackage.ResultData;

public class ResultsTable extends JScrollPane
{
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = {"Strategy", "Result"};
    private static final int WIDTH = 400;
    private static final int HEIGHT = 90;
    private static final int ROW_HEIGHT = 30;
     
    private static DefaultTableModel tableModel = new DefaultTableModel();
    private static JTable resultTable = new JTable(tableModel);
 
    public ResultsTable()
    {
        super(resultTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tableModel.setColumnIdentifiers(COLUMN_NAMES);
  //      resultTable.setRowHeight(ROW_HEIGHT);
  //      resultTable.setPreferredScrollableViewportSize(new Dimension(WIDTH, HEIGHT));
        resultTable.setFillsViewportHeight(true); 
    }
    
    public void fillResults(Vector<ResultData> results)
    {
        clearAllRows();   
        for(ResultData result : results)
        {
            tableModel.addRow(new Object[]{result.strategy.toString(), result.value});
        }      
    }
    
    private void clearAllRows() {
        if (tableModel.getRowCount() > 0) 
        {
            for (int rowNumber = tableModel.getRowCount() - 1; rowNumber > -1; rowNumber--) 
            {
                tableModel.removeRow(rowNumber);
            }
        }
    }    
}
