package theatricalplays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import theatricalplays.Play.TheaterPlayType;

import java.io.FileReader;
import java.io.FileWriter;



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

public String toText2(Invoice invoice,Map<String, Play> plays)  {
  NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

  String filePath = "/home/yassine/Downloads/GenieLog-M1-TP2-src/TP2/src/main/java/theatricalplays/template/TemplateTxt.txt";
  String template = StatementPrinter.readFileToString(filePath);
  invoice.CalculateInvoice(plays);
  String lineformat = "  %s: %s (%s seats)\n";
  String performanceInvoiceHtml = PerformanceInvoiceFormat(invoice.performanceInvoices,lineformat).toString();
  String[][] replacements = {
    {"{@Customer_Name}", invoice.customer+"\n"},
    {"{@Performance_Invoice}", performanceInvoiceHtml},
    {"{@Invoice_Amount}", frmt.format((invoice.totalAmount))+"\n"},
    {"{@Total_Credits}", String.valueOf(invoice.volumeCredits)}
  };
  for (String[] replacement : replacements) {
      template = template.replace(replacement[0], replacement[1]);
    }
  return template;


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
    System.err.println("Error writing to the file: " + e.getMessage());

  }
  String content = contentBuilder.toString();
  return content;
}

private static StringBuffer PerformanceInvoiceFormat(List<PerformanceInvoice> performanceInvoices,String line_format){
  NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
  StringBuffer result = new StringBuffer("");
  for (PerformanceInvoice perf_inv : performanceInvoices) {
    result.append(String.format(
    line_format ,
    perf_inv.playName,
    frmt.format(perf_inv.Amount),
    perf_inv.audience
    ));
  }
  
  return result;
}


public  String toHtml(Invoice invoice,Map<String, Play> plays) {
  String htmlFilePath =  "/home/yassine/Downloads/GenieLog-M1-TP2-src/TP2/src/main/java/theatricalplays/template/TemplateHtml.html";
  String htmlTemplate = StatementPrinter.readFileToString(htmlFilePath);
  invoice.CalculateInvoice(plays);
  String lineformat = "<tr>\n"
    + "<td>%s</td>\n"
    + "<td>%s</td>\n"
    + "<td>%s</td>\n"
    + "</tr>\n";
  String performanceInvoiceHtml = PerformanceInvoiceFormat(invoice.performanceInvoices,lineformat).toString();
  String[][] replacements = {
    {"{@Customer_Name}", invoice.customer},
    {"{@Performance_Invoice}", performanceInvoiceHtml},
    {"{@Invoice_Amount}", String.valueOf(invoice.totalAmount)},
    {"{@Total_Credits}", String.valueOf(invoice.volumeCredits)}
  };

  for (String[] replacement : replacements) {
    htmlTemplate = htmlTemplate.replace(replacement[0], replacement[1]);
  }

  return htmlTemplate;
}

public static void writeToFile(String filePath, String Content) {
  try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(Content);
    writer.close();
    System.out.println("File generated successfully at: " + filePath);
} catch (IOException e) {
    System.err.println("Error writing to the file: " + e.getMessage());
}
}


 public static void main(String[] args) {
  Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", TheaterPlayType.TRAGEDY),
                "as-like", new Play("As You Like It", TheaterPlayType.COMEDY),
                "othello", new Play("Othello", TheaterPlayType.TRAGEDY));

  Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));
  StatementPrinter statementPrinter = new StatementPrinter();


  
  String test = statementPrinter.toHtml(invoice,plays);
  String filePath = "src/main/java/theatricalplays/output/index.html";
  writeToFile(filePath,test);
  System.out.println(test);



     }






}
