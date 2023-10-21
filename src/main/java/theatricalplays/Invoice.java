package theatricalplays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Invoice {

  public String customer;
  public List<Performance> performances;
  public double totalAmount;
  public int volumeCredits;
  public List<PerformanceInvoice> performanceInvoices;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }


  public void CalculateInvoice(Map<String, Play> plays) {
    double totalAmount = 0;
    int volumeCredits = 0;
    List<PerformanceInvoice> perf_invoices = new ArrayList<>();

    for (Performance perf : this.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = play.CaluclatePlayAmount(perf.audience);
      PerformanceInvoice perf_Inv = new PerformanceInvoice(play.name,thisAmount, perf.audience);
      perf_invoices.add( perf_Inv);
      volumeCredits += play.CaluclateVolumeCreditsIncrease(perf.audience);
      totalAmount += thisAmount;
    }
    
    this.performanceInvoices = perf_invoices;
    this.totalAmount=totalAmount;
    this.volumeCredits= volumeCredits;

  }





}
