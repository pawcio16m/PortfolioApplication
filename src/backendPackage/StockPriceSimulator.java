package backendPackage;

import java.lang.Math;
import java.util.Random;

public class StockPriceSimulator {
    private static final int NUMBER_OF_SIMULATION = 200;
    private double actualValue;
    private double returnOfInvestment;
    private double volatilityRate;

    StockPriceSimulator(double actualValue, double returnOfInvestment, double volatilityRate) {
        this.actualValue = actualValue;
        this.returnOfInvestment = returnOfInvestment;
        this.volatilityRate = volatilityRate;
    }

    public double getStockPrice(double executionTime, int numberOfSteps ){
        double sumOfPrices = 0;
        double delta_T = executionTime/numberOfSteps;///trzeva zaokr!!!!!!!!!!!!!!
        double up = Math.exp(volatilityRate*delta_T);
        double down = 1/up;
        double probability = ( Math.exp(returnOfInvestment*delta_T)-down ) / (up-down);
        for(int j=1; j<=NUMBER_OF_SIMULATION; j++) {
            double price = actualValue;
            Random generator = new Random();
            for (int i = 1; i <= numberOfSteps; i++) {
                if (probability> generator.nextDouble()) {
                    price = price * up;
                } else {
                    price = price * down;
                }
            }
            sumOfPrices=sumOfPrices+price;
        }
        double stockPrice =sumOfPrices/NUMBER_OF_SIMULATION;
        return stockPrice;
    }
}
