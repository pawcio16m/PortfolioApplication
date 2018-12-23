package backendPackage;

import java.util.Arrays;
import java.util.Vector;

public class RelativePerformanceOfPortfolioAlgorithm
{
    private double[] actualValue;
    private double[] returnOfInvestment;
    private double[] volatilityRate;
    private int numberOfSteps;
    protected int sizeOfPortfolio;
    private double executionTime;
    private double[][] weightOfStocksInPortfolio = new double[numberOfSteps][sizeOfPortfolio];
    private double[][] marketWeights = new double[numberOfSteps][sizeOfPortfolio];
    
    StockPriceSimulator simulator;
    
    RelativePerformanceOfPortfolioAlgorithm( double[] actualValue, double[] returnOfInvestment, double[] volatilityRate,
            int numberOfSteps, int sizeOfPortfolio, double executionTime) {
        this.actualValue = actualValue;
        this.returnOfInvestment = returnOfInvestment;
        this.volatilityRate = volatilityRate;
        this.numberOfSteps=numberOfSteps;
        this.sizeOfPortfolio=sizeOfPortfolio;
        this.executionTime=executionTime;
    }


    public double[][] returnStockPrices(){
        double[][] portfolioStockPrices=new double[numberOfSteps][sizeOfPortfolio];
        StockPriceSimulator[] simulators=new StockPriceSimulator[sizeOfPortfolio];
        for(int i=1;i<=sizeOfPortfolio;i++){
            StockPriceSimulator sim =new StockPriceSimulator(actualValue[i],returnOfInvestment[i],volatilityRate[i]);
            simulators[i]=sim;
        }

        for(int i=1;i<=numberOfSteps;i++){
          for(int j=1;j<=sizeOfPortfolio;j++){
              portfolioStockPrices[i][j] = simulators[j].getStockPrice(i, numberOfSteps);
          }
        }
        return portfolioStockPrices;
    }
    private double[][] stockPrices=returnStockPrices();

    private void fillMarketWeightOfPortfolio() {
        double[] sumOfStockPrices=new double[sizeOfPortfolio];
        for (int j = 1; j <= sizeOfPortfolio; j++) {
            sumOfStockPrices[j] = 0;
        }
        for (int i = 1; i <= numberOfSteps; i++){
            for (int j = 1; j <= sizeOfPortfolio; j++) {
                sumOfStockPrices[i] = +stockPrices[i][j];
            }
        }
        for(int i=1;i<=numberOfSteps;i++){
            for(int j=1;j<=sizeOfPortfolio;j++){
               marketWeights[i][j] = stockPrices[i][j]/sumOfStockPrices[j];
            }
        }
    }


    private double calculateEnergy(int time){//po czasie i rozmiarze, to time to nie time tylko ktory krok, time=numberofsteps to dostaniemy dla T
        double energy=0;
        double[] sum1=new double[time];
        for(int i=1;i<=time;i++){
            sum1[i]=0;
        }
        for(int i=0;i<=time-1;i++){
            for(int j=1;j<=sizeOfPortfolio;j++){
               sum1[i] = +weightOfStocksInPortfolio[i][j]*(marketWeights[i+1][j]/marketWeights[i][j]);
            }
        }
        for(int i=0;i<=time;i++){
            energy=+(Math.log(sum1[i])-sum1[i]);
        }
        return energy;
    }

    private double calculateControl(int time){
        double control=0;
        double[] relativeEntropy=new double[time];
        double[] relativeEntropySameTime=new double[time];
        for(int i=1;i<=time;i++){
            relativeEntropy[i]=0;
        }
        for(int i=0;i<=time-1;i++){
            for(int j=1;j<=sizeOfPortfolio;j++){
                relativeEntropy[i] = +weightOfStocksInPortfolio[i][j]*Math.log(weightOfStocksInPortfolio[i][j]/marketWeights[i+1][j]);
            }
        }
        for(int i=1;i<=time;i++){
            relativeEntropySameTime[i]=0;
        }
        for(int i=0;i<=time;i++){
            for(int j=1;j<=sizeOfPortfolio;j++){
                relativeEntropySameTime[i] = +weightOfStocksInPortfolio[i][j]*Math.log(weightOfStocksInPortfolio[i][j]/marketWeights[i][j]);
            }
        }
        for(int i=0;i<=time-1;i++){
            control=+(relativeEntropySameTime[i+1]+relativeEntropy[i]);
        }
        return control;
    }

    private double calculateRelativeEntropyOfPortfolio(int time){
        double relativeEntopyOfPortfolio=0;
        for(int j=1;j<=numberOfSteps;j++){
                relativeEntopyOfPortfolio = +(weightOfStocksInPortfolio[time][j]*Math.log(weightOfStocksInPortfolio[time][j]/marketWeights[time][j])-weightOfStocksInPortfolio[0][j]*Math.log(weightOfStocksInPortfolio[0][j]/marketWeights[0][j]));
        }
        return  relativeEntopyOfPortfolio;
    }

    private void fillWeightOfStocksInPortfolio(Strategy strategy){
        switch(strategy){
            case EQUAL:
                double[] equalWeight = new double[sizeOfPortfolio];
                for(double[] row : weightOfStocksInPortfolio) {
                    Arrays.fill(row, 1 / sizeOfPortfolio);
                }
                return;
            case CONSTANT:
                return;
            default:
                break;
        }
    }
//element klasy zeszyt
    public double calulateRetalivePerformaneOfPortfolioForStrategy(Strategy strategy){
        fillWeightOfStocksInPortfolio(strategy);
        fillMarketWeightOfPortfolio();
        Vector<AlgorithmOutput> output = new Vector<AlgorithmOutput>(numberOfSteps);

        int step = 0;
        for (AlgorithmOutput element : output) {
            element.control = calculateControl(step);
            element.energy = calculateEnergy(step);
            element.relativeEntropy = calculateRelativeEntropyOfPortfolio(step);
            element.relativePerformance = element.control + element.energy + element.relativeEntropy;

                    //TODO zapis do pliku

            ++step;
        }


        return output.lastElement().relativePerformance;
    }


    public double doubleGetValue() // mozna zmieic nazwe ta funkcja bd zwracac ta wartosc w relative cos tam cos tam w zalezosci od strategi
    {
        return 0;
    }

    public double getValueToChart() //jakosc trzeba sie zastanowic nad zwracaniem wektora na potrzeby malowania  powino zwracac wektor albo cos takiego
    {
        return 0;
    }

    private double calculate() //ta funkcja bd zwracac wartosc dla chwili t
    {

        return 0;
    };
    //TODO pasuje zrobic prywatne funckje ktore bd liczyc te wszystkie entropie, kontrole itp w zaleznosci od t.

    //TODO pasuje zrobic jakas funkcje export ktora zmieni ten wektor np w plik xls(excel) ale to nie wiem czy w tej klasie

    
    
    public double calculateReleativePerfomanceOfPortfolioForStrategy(Strategy strategy)
    {
        return 0.0;
    }

}
