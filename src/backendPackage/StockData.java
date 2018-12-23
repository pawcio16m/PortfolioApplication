package backendPackage;

public class StockData
{
    private String stockName;
    double actualValue;
    double returnOfInvestment;
    double volatilityRate;
    double weightOfStock;
    
    public StockData(String stockName, double actualValue, double returnOfInvestment, double volatilityRate, double weightOfStock) {
        this.stockName = stockName;
        this.actualValue = actualValue;
        this.returnOfInvestment = returnOfInvestment;
        this.volatilityRate = volatilityRate;
        this.weightOfStock = weightOfStock;
    }
    
    @Override
    public String toString()
    {
        return "StockName: " +stockName+ "\n"+
               "Actual Value: " +String.valueOf(actualValue)+ "\n"+
               "Return Of Investment: " +String.valueOf(returnOfInvestment)+ "\n"+
               "Volatility Rate: " +String.valueOf(volatilityRate)+ "\n"+
               "Weight Of Stock: " +String.valueOf(weightOfStock);
    }
}
