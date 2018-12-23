package frontendPackage;

public class StockData
{
    private String stockName;
    private double actualValue;
    private double returnOfInvestment;
    private double volatibilityRate;
    
    public StockData(String stockName, double actualValue, double returnOfInvestment, double volatibilityRate) {
        this.stockName = stockName;
        this.actualValue = actualValue;
        this.returnOfInvestment = returnOfInvestment;
        this.volatibilityRate = volatibilityRate;
    }
    
    @Override
    public String toString()
    {
        return "StockName: " +stockName+ "\n"+
               "Actual Value: " +String.valueOf(actualValue)+ "\n"+
               "Return Of Investment: " +String.valueOf(returnOfInvestment)+ "\n"+
               "Volability Rate: " +String.valueOf(volatibilityRate);
    }
}
