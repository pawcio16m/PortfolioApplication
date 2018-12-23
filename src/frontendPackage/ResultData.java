package frontendPackage;

public class ResultData
{
    public Strategy strategy;
    public double value;
    
    public ResultData(Strategy strategy, double value)
    {
        this.strategy = strategy;
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        return "Strategy Name: " +strategy+ " - Calculated Value: " +String.valueOf(value);
    }
}
