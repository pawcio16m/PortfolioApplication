package backendPackage;

public interface IFileHandler
{
    boolean createFile(String filename);
    boolean addRecord(AlgorithmOutput data, int index);
    boolean closeFile();

}
