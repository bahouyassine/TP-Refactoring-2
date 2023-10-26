package theatricalplays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  
  public double totalPrice;
  public int credit;
  public List<PerformanceInvoice> performanceInvoices;

  public Invoice(String customer_name, List<Performance> performances) {
    this.customer = new Customer(customer_name);
    this.performances = performances;
  }


  public void CalculateInvoice(Map<String, Play> plays) {
    double totalPrice = 0;
    int volumeCredits = 0;
    List<PerformanceInvoice> perf_invoices = new ArrayList<>();

    for (Performance perf : this.performances) {
      Play play = plays.get(perf.playID);

      double thisAmount = play.PlayPrice(perf.audience);

      PerformanceInvoice perf_Inv = new PerformanceInvoice(play.name,thisAmount, perf.audience);
      perf_invoices.add( perf_Inv);

      volumeCredits += play.CreditIncrease(perf.audience);
      totalPrice += thisAmount;
    }
    this.performanceInvoices = perf_invoices;
    this.totalPrice=totalPrice;
    this.credit= volumeCredits;

  }

  public static boolean ApplyReduction(double totalPrice,int credit){
    boolean applied = false;
    if (credit>=150 && totalPrice>=15){
      credit -= 150;
      totalPrice -= 15;
      applied = true;
    } 
    return applied;
  } 
}
