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
        System.out.println("delta_T = "+delta_T);
        double up = Math.exp(volatilityRate*delta_T);
        System.out.println("up = "+up);
        double down = 1/up;
        System.out.println("down = "+down);
        double probability = ( Math.exp(returnOfInvestment*delta_T)-down ) / (up-down);
        System.out.println("probality = "+probability);
        for(int simulationStep = 0; simulationStep < NUMBER_OF_SIMULATION; ++simulationStep) {
            double price = actualValue;
            Random generator = new Random();
            for (int step = 0; step < numberOfSteps; ++step) {
                if (probability> generator.nextDouble()) {
                    price = price * up;
                } else {
                    price = price * down;
                }
            }
            sumOfPrices += price;
        }
        return sumOfPrices / NUMBER_OF_SIMULATION;
    }
}
