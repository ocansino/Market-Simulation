//Oscar Cansino
//SimulateMarket contains three different methods of simulation: Linear, Distributional, and Monte Carlo

import java.text.DecimalFormat;
import java.util.*;
public class SimulateMarket extends Sample{
   private int startEquity = 100;
   int time = 3582;
   
   public void SimulateMarket(int startEquity){
      this.startEquity = startEquity;
   }
   public int getStartEquity(){
      return this.startEquity;
   }
   public static void main(String[] args) {
      SimulateMarket one = new SimulateMarket();
      
      one.linearAverage(100);
      one.linearMedian(100);
      one.distributional(3582, 1);//find mean
      one.distributional(3582, 2);//find median
      one.monte_carlo(3582, 3);//just compute stats
   }
   
   //linear
   public void linearAverage(int startEquity){
      double equity = startEquity;
      double newEquity = 0;
      for(int index = 0; index < time; index++)
      {
         newEquity = (equity * 0.0017) + equity;
         equity = newEquity;
      }
      DecimalFormat df1 = new DecimalFormat( "#.##" );
      String newEquityRounded = df1.format(newEquity);
      System.out.print("Linear model based on mean: " + newEquityRounded);
   }
   
   public void linearMedian(double startEquity){
      double equityMed = startEquity;
      double newEquityMed = 0;
      for(int index = 0; index < time; index++)
      {
         newEquityMed = (equityMed * 0.0029) + equityMed;
         equityMed = newEquityMed;
      }
      DecimalFormat df1 = new DecimalFormat( "#.##" );
      String newEquityMedRounded = df1.format(newEquityMed);
      System.out.println(" Linear model based on median: " + newEquityMedRounded);
      System.out.println("");
   }
   
   //distributional
   public void distributional(int size, int purpose){
      Sample Equity = new Sample();
      if (purpose == 1){
         System.out.println("Stats for Distributional using mean:");
      }
      else if (purpose == 2){
         System.out.println("Stats for Distributional using median:");
      }
      double x = Equity.computeStats(size, purpose); 
      double equity = startEquity;
      double newEquity = 0;
      if (purpose == 1){
         for (int dat = 0; dat < size; dat++){
            newEquity = (equity * x) + equity;  
            equity = newEquity;
         }
         DecimalFormat df1 = new DecimalFormat( "#.##" );
         String equityRounded = df1.format(equity);
         System.out.println("Equity after distributional mean: " + equityRounded);
         System.out.println("");
      }
      else if (purpose == 2){
         for (int dat = 0; dat < size; dat++){
            newEquity = (equity * x) + equity;  
            equity = newEquity;
         }
         DecimalFormat df1 = new DecimalFormat( "#.##" );
         String equityRounded = df1.format(equity);
         System.out.println("Equity after distributional median: " + equityRounded);
         System.out.println("");
      }
      
   }
   
   //monte carlo
   public void monte_carlo(int size, int purpose){
      Sample two = new Sample();
      ArrayList<Double> List = new ArrayList<Double>();
      ArrayList<Double> List1 = new ArrayList<Double>();
      List = two.read();
      int newMin = 0;
      int newMax = List.size() - 1;
      Random rand = new Random();
      for (int ind = 0; ind < List.size(); ind++){ 
         int randnum = rand.nextInt((newMax - newMin) + 1) + newMin;
         List1.add(List.get(randnum));
      }
      
      //equity
      double equity1 = startEquity;
      double newequity1 = 0;
      Sample Equity = new Sample();
      System.out.println("Stats for Monte carlo using Equity instance:");
      Equity.computeStats(size, purpose);
      for (int index1 = 0; index1 < List1.size(); index1++){
         newequity1 = (equity1 * List1.get(index1)) + equity1;
         equity1 = newequity1;
      }
      DecimalFormat df1 = new DecimalFormat( "#.##" );
      String equity1Rounded = df1.format(equity1);
      System.out.println("Equity after Monte Carlo Equity instance: " + equity1Rounded + " ");
      System.out.println("");
      
      //model
      Sample model = new Sample();
      double equity2 = startEquity;
      double newequity2 = 0;
      System.out.println("Stats for Monte carlo using Model instance:");
      model.computeStats(size, purpose);
      for (int index2 = 0; index2 < List.size(); index2++){
         newequity2 = (equity2 * List.get(index2)) + equity2;
         equity2 = newequity2;
      }
      String equity2Rounded = df1.format(equity2);
      System.out.println("Equity after Monte Carlo Model instance: " + equity2Rounded);
   }
   
   
   
   
}