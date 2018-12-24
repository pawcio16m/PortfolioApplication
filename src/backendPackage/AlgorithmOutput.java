package backendPackage;

import java.util.Arrays;
import java.util.List;

public class AlgorithmOutput {
    public double energy;
    public double control;
    public double relativeEntropy;
    public double relativePerformance;
    
    public AlgorithmOutput() {
        this.energy = 0;
        this.control = 0;
        this.relativeEntropy = 0;
        this.relativePerformance = 0;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(energy)+ "\t| "+
               String.valueOf(control)+ "\t\t| "+
               String.valueOf(relativeEntropy)+ "\t\t\t| "+
               String.valueOf(relativePerformance);
    }
    
    public List<String> convertToStringList(int index) {
        return Arrays.asList(String.valueOf(index), 
                             String.valueOf(energy),
                             String.valueOf(control),
                             String.valueOf(relativeEntropy),
                             String.valueOf(relativePerformance));
    }
}
