package theatricalplays;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.io.FileReader;



public class StatementPrinter {

public StringBuffer toText(Invoice invoice, Map<String, Play> plays) {

  invoice.CalculateInvoice(plays);

  StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));
  NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

  for (PerformanceInvoice perf_inv : invoice.performanceInvoices) {
    result.append(String.format("  %s: %s (%s seats)\n", perf_inv.playName, frmt.format(perf_inv.Amount), perf_inv.audience)); 
  }

  result.append(String.format("Amount owed is %s\n", frmt.format(invoice.totalAmount)));
  result.append(String.format("You earned %s credits\n", invoice.volumeCredits));

  return result;
}

public static String readFileToString(String filePath){
  StringBuilder contentBuilder = new StringBuilder();
  try {
      BufferedReader in = new BufferedReader(new FileReader(filePath));
      String str;
      while ((str = in.readLine()) != null) {
          contentBuilder.append(str);
      }
      in.close();
  } catch (IOException e) {
    System.out.println("56");

  }
  String content = contentBuilder.toString();
  return content;
}


 public static void main(String[] args) {
    String htmlFilePath = "TP2/src/main/java/theatricalplays/Templates/TemplateHtml.html";
    String test = StatementPrinter.readFileToString(htmlFilePath);
    System.out.println(test);

     }






}
