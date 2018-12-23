package frontendPackage;

import java.util.Vector;

public class ResultCalculator implements IResultCalculator
{

    @Override
    public Vector<ResultData> calculate(Vector<StockData> data, int timeOfInvestment) {
        System.out.println("Algorithm starts...");
        System.out.println("Input data");
        System.out.println("Time of investment "+ String.valueOf(timeOfInvestment));
        System.out.println("Stock data size "+ String.valueOf(data.size()));
        for (StockData stockData : data)
        {
            System.out.println(stockData.toString());
        }
        
        //TODO implement here your algorithm
        
        System.out.println("Results");
        Vector<ResultData> results = new Vector<ResultData>(3);
        //Here is an example of results
        results.add(new ResultData(Strategy.EQUAL, 10.5));
        results.add(new ResultData(Strategy.CONSTANT, 110.5));
        results.add(new ResultData(Strategy.MARKET, 20.8));
        for (ResultData result : results)
        {
            System.out.println(result.toString());
        }     
        System.out.println("Algorithm finished.");  
        return results;
    }

}
