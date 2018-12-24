package backendPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvFileHandler implements IFileHandler
{
    private static final char DEFAULT_SEPARATOR = ';';
    private static final char NEW_LINE_SEPARATOR = '\n';
    
    private static final List<String> FILE_HEADER = Arrays.asList("Time", "Energy", "Control", "Relative Entropy", "Relative Performance");
    private FileWriter writer = null;

    @Override
    public boolean createFile(String filename) {
        String csvFilename = "./out/results_"+filename.toLowerCase()+".csv";
        try {
            writer = new FileWriter(csvFilename);
            writer.append(createCsvLine(FILE_HEADER));
        }
        catch (IOException e) {
            System.err.println("File "+csvFilename+ " not created!");   
        }
        return (null != writer);
    }

    @Override
    public boolean addRecord(AlgorithmOutput data, int index)
    {
        boolean recordAdded = false;
        try {
            writer.append(createCsvLine(data.convertToStringList(index)));
            recordAdded = true;
        }
        catch (IOException e) {
            System.err.println("Record for index["+index+"] not added!");            
        }
        return recordAdded;  
    }
    
    public boolean closeFile() { 
        boolean isFileClosed = false;
        try {
            writer.flush();
            writer.close();
            isFileClosed = true;
        } catch (IOException e) {
            System.out.println("Error while flushing/closing file!");
        }
        return isFileClosed;
    }

    private String createCsvLine(List<String> values) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) sb.append(DEFAULT_SEPARATOR);
            
            sb.append(followCVSformat(value));
            first = false;
        }
        sb.append(NEW_LINE_SEPARATOR);
        return sb.toString();        
    }
    
    private String followCVSformat(String value) {
        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }
}