package backendPackage;

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
        return "Energy: " +String.valueOf(energy)+ "\n"+
               "Control: " +String.valueOf(control)+ "\n"+
               "Relative Entropy: " +String.valueOf(relativeEntropy)+ "\n"+
               "Relative Performance: " +String.valueOf(relativePerformance);
    }
}
