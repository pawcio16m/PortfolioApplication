package guiPackage;

import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import backendPackage.ResultData;

public class ResultsTable extends JScrollPane
{
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = {"Strategy", "Result"};
     
    private static DefaultTableModel tableModel = new DefaultTableModel();
    private static JTable resultTable = new JTable(tableModel);
 
    public ResultsTable()
    {
        super(resultTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tableModel.setColumnIdentifiers(COLUMN_NAMES);
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
