package frontendPackage;

import java.util.Vector;

public interface IResultCalculator
{
    Vector<ResultData> calculate(Vector<StockData> data, int timeOfInvestment);
}
