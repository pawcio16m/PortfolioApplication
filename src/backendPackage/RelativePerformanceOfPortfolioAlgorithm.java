package backendPackage;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.DoubleStream;

public class RelativePerformanceOfPortfolioAlgorithm
{
    private double[] actualValue;
    private double[] returnOfInvestment;
    private double[] volatilityRate;
    private double[] weightOfStock;
    
    private int numberOfSteps;
    private int sizeOfPortfolio;

    private Vector<StockPriceSimulator> stockPriceSimulators;
    private double[][] weightOfStocksInPortfolio;
    private double[][] marketWeights;
        
    RelativePerformanceOfPortfolioAlgorithm( double[] actualValue, double[] returnOfInvestment, double[] volatilityRate, double[] weightOfStock,
            int numberOfSteps, int sizeOfPortfolio) {
        this.actualValue = actualValue;
        this.returnOfInvestment = returnOfInvestment;
        this.volatilityRate = volatilityRate;
        this.weightOfStock = weightOfStock;
        this.numberOfSteps=numberOfSteps;
        this.sizeOfPortfolio=sizeOfPortfolio;
        
        stockPriceSimulators = new Vector<StockPriceSimulator>(sizeOfPortfolio); 
        weightOfStocksInPortfolio = new double[numberOfSteps][sizeOfPortfolio];
        marketWeights = new double[numberOfSteps][sizeOfPortfolio];
    }
    
    public double calculateRetalivePerformaneOfPortfolioForStrategy(Strategy strategy) {
        fillWeightOfStocksInPortfolio(strategy);
        fillMarketWeightOfPortfolio();
        Vector<AlgorithmOutput> output = new Vector<AlgorithmOutput>(numberOfSteps);

        //TODO make something with last element add this if to prevent exception
        System.out.println("time\t| energy\t| control\t| relative entropy\t| relative performance");
        for (int step = 0; step < numberOfSteps - 1; ++step) {
            AlgorithmOutput element = new AlgorithmOutput();
            element.control = calculateControl(step);
            element.energy = calculateEnergy(step);
            element.relativeEntropy = calculateRelativeEntropyOfPortfolio(step);
            element.relativePerformance = element.control + element.energy + element.relativeEntropy;
            output.add(element);
            //TODO save to File
            System.out.println(step+"\t|"+element.toString());
        }
        //return output.lastElement().relativePerformance; //TODO make something with last element to make it work again!
        return 0.0;
    }
    
    private void fillWeightOfStocksInPortfolio(Strategy strategy){
        switch(strategy){
            case EQUAL:
                for(double[] row : weightOfStocksInPortfolio) {
                    Arrays.fill(row, 1 / sizeOfPortfolio);
                }
                break;
            case CONSTANT:
                for(double[] row : weightOfStocksInPortfolio) {
                    for(int stockIndex = 0; stockIndex < sizeOfPortfolio; ++ stockIndex) {
                        row[stockIndex] = weightOfStock[stockIndex] / 100; //convert from percent to factor
                        ++stockIndex;                    
                    }
                }
                break;
            default:
                System.err.println("Unkonwn strategy!");
                break;
        }
    }

    private void fillMarketWeightOfPortfolio() {
        preapareStockPriceSimulators();
        for (int step = 0; step < numberOfSteps; ++step){
            double[] stockPricesForPortfolio = new double[sizeOfPortfolio];
            for(int stockIndex = 0; stockIndex < sizeOfPortfolio; ++stockIndex) {
                double simulatedStockPrice = stockPriceSimulators.get(stockIndex).getStockPrice(step, numberOfSteps);
                System.out.println("Simulated stock price for stock index["+stockIndex+"] in time("+step+") = "+simulatedStockPrice);
                stockPricesForPortfolio[stockIndex] = simulatedStockPrice;
            }
            double sumOfStockPrices = DoubleStream.of(stockPricesForPortfolio).sum();
            for(int stockIndex = 0; stockIndex < sizeOfPortfolio; ++stockIndex) {
                double weight = stockPricesForPortfolio[stockIndex] / sumOfStockPrices;
                marketWeights[step][stockIndex] = weight;
                System.out.println("Weight for stock index[" +stockIndex+"] in time("+step+") = "+weight);
             }
        }
    }

    private void preapareStockPriceSimulators() {
        for(int stockIndex = 0 ; stockIndex< sizeOfPortfolio; ++stockIndex){
            stockPriceSimulators.add(new StockPriceSimulator(actualValue[stockIndex], returnOfInvestment[stockIndex], volatilityRate[stockIndex]));
        }
    }

    private double calculateEnergy(int time){//po czasie i rozmiarze, to time to nie time tylko ktory krok, time=numberofsteps to dostaniemy dla T
        double energy = 0;
        double sumOfWeight = 0;

        for(int stockIndex = 0 ; stockIndex< sizeOfPortfolio; ++stockIndex){
            //TODO time+1 may get element not from array!!!!!
            sumOfWeight += weightOfStocksInPortfolio[time][stockIndex]*(marketWeights[time+1][stockIndex]/marketWeights[time][stockIndex]);
        }
        energy += Math.log(sumOfWeight) - sumOfWeight;        
        return energy;
    }

    private double calculateControl(int time){
        double control = 0;
        double relativeEntropy = 0;
        //what does it mean same time?
        double relativeEntropySameTime = 0;

        for(int stockIndex = 0 ; stockIndex< sizeOfPortfolio; ++stockIndex){
            //TODO time+1 may get element not from array!!!!!
            relativeEntropy += weightOfStocksInPortfolio[time][stockIndex]*Math.log(weightOfStocksInPortfolio[time][stockIndex]/marketWeights[time+1][stockIndex]);
            relativeEntropySameTime += weightOfStocksInPortfolio[time+1][stockIndex]*Math.log(weightOfStocksInPortfolio[time+1][stockIndex]/marketWeights[time+1][stockIndex]);
            control += relativeEntropySameTime + relativeEntropy;
        }
        return control;
    }

    private double calculateRelativeEntropyOfPortfolio(int time){
        double relativeEntopyOfPortfolio = 0;
        for (int stockIndex = 0 ; stockIndex< sizeOfPortfolio; ++stockIndex) {
            relativeEntopyOfPortfolio += (weightOfStocksInPortfolio[time][stockIndex]*Math.log(weightOfStocksInPortfolio[time][stockIndex]/marketWeights[time][stockIndex])-weightOfStocksInPortfolio[0][stockIndex]*Math.log(weightOfStocksInPortfolio[0][stockIndex]/marketWeights[0][stockIndex]));
        }
        return  relativeEntopyOfPortfolio;
    }
}
