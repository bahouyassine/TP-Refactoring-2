package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public StringBuffer print(Invoice invoice, Map<String, Play> plays) {
    double totalAmount = 0;
    int volumeCredits = 0;
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = play.CaluclatePlayAmount(perf.audience);

      // add volume credits
      volumeCredits += play.CaluclateVolumeCreditsIncrease(perf.audience);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience)); 
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result;
  }
  

}
