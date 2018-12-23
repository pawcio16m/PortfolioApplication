package backendPackage;

import java.util.Vector;

public interface IResultCalculator
{
    //timeOfInvestment unit is year
    Vector<ResultData> calculate(Vector<StockData> data, int timeOfInvestment);
}
