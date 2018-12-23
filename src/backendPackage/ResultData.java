package backendPackage;

public class ResultData
{
    public Strategy strategy;
    public double value;
    public boolean isEnergyEntropyPortfolio;
    
    public ResultData(Strategy strategy, double value, boolean isEnergyEntropyPortfolio)
    {
        this.strategy = strategy;
        this.value = value;
        this.isEnergyEntropyPortfolio = isEnergyEntropyPortfolio;
    }
    
    @Override
    public String toString()
    {
        return "Strategy Name: " +strategy+ " - Calculated Value: " +String.valueOf(value) +" is energy-entropy? " + (isEnergyEntropyPortfolio ? "YES" : "NO");
    }
}
