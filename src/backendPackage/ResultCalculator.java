package backendPackage;

import java.util.Vector;

public class ResultCalculator implements IResultCalculator
{
    private static final int WEEKS_IN_YEAR = 52;
    
    RelativePerformanceOfPortfolioAlgorithm algorithm;

    @Override
    public Vector<ResultData> calculate(Vector<StockData> data, int timeOfInvestment) {
        System.out.println("Algorithm starts...");
        System.out.println("Input data");
        System.out.println("Time of investment "+ String.valueOf(timeOfInvestment));
        System.out.println("Stock data size "+ String.valueOf(data.size()));
        
        int stockDataSize = data.size();
        //Define sampling frequency for algorithm - assume one week
        int numberOfSteps = WEEKS_IN_YEAR * timeOfInvestment;
        double[] actualValue = new double[stockDataSize];
        double[] returnOfInvestment  = new double[stockDataSize];
        double[] volatilityRate = new double[stockDataSize];
        double[] weightOfStocks = new double[stockDataSize];
        
        int stockDataIndex = 0;
        for (StockData stockData : data)
        {
            actualValue[stockDataIndex] = stockData.actualValue;
            returnOfInvestment[stockDataIndex] = stockData.returnOfInvestment;
            volatilityRate[stockDataIndex] = stockData.volatilityRate;
            weightOfStocks[stockDataIndex] = stockData.weightOfStock;
            ++stockDataIndex;
            System.out.println(stockData.toString());
        }
        
        algorithm = new RelativePerformanceOfPortfolioAlgorithm(actualValue, returnOfInvestment, volatilityRate, weightOfStocks, numberOfSteps , stockDataSize);
                
        Vector<ResultData> results = new Vector<ResultData>(2);
        results.add(new ResultData(Strategy.EQUAL, algorithm.calculateRetalivePerformaneOfPortfolioForStrategy(Strategy.EQUAL), true));
     //   results.add(new ResultData(Strategy.CONSTANT, algorithm.calculateRetalivePerformaneOfPortfolioForStrategy(Strategy.CONSTANT), false));
        
        System.out.println("Results");
        for (ResultData result : results)
        {
            System.out.println(result.toString());
        }     
        System.out.println("Algorithm finished.");  
        return results;
    }

}
