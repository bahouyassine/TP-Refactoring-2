package theatricalplays.Formats;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import theatricalplays.File;
import theatricalplays.Invoice;
import theatricalplays.PerformanceInvoice;
import theatricalplays.Play;

public class Txt {

  private static String txtTemplatePath =
    "src/main/resources/template/TemplateTxt.txt";
  private static String txtDiscountTemplatePath =
    "src/main/resources/template/TemplateTxtDiscount.txt";

  private static StringBuffer PerformanceInvoiceTxtFormat(
    List<PerformanceInvoice> performanceInvoices
  ) {
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer result = new StringBuffer("");
    for (PerformanceInvoice perf_inv : performanceInvoices) {
      result.append(
        String.format(
          "  %s: %s (%s seats)\n",
          perf_inv.playName,
          frmt.format(perf_inv.Amount),
          perf_inv.audience
        )
      );
    }

    return result;
  }

  public static String toText(Invoice invoice, Map<String, Play> plays) {
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    String template = File.readFileToString(txtTemplatePath);
    invoice.CalculateInvoice(plays);
    String performanceInvoiceTxt = PerformanceInvoiceTxtFormat(
      invoice.performanceInvoices
    )
      .toString();
    String[][] replacements = {
      { "{@Customer_Name}", invoice.customer.name + "\n" },
      { "{@Performance_Invoice}", performanceInvoiceTxt },
      { "{@Invoice_Amount}", frmt.format((invoice.totalPrice)) + "\n" },
      { "{@Total_Credits}", String.valueOf(invoice.customer.credit) },
    };
    for (String[] replacement : replacements) {
      template = template.replace(replacement[0], replacement[1]);
    }
    template =
      template + "\n" + DiscountToText(invoice);
    return template;
  }

  public static String DiscountToText(Invoice invoice) {
    String template = "";
    if (invoice.ApplyReduction()) {
      NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
      template = File.readFileToString(txtDiscountTemplatePath);
      String[][] replacements = {
        { "{@enter}", "\n" },
        { "{@Invoice_Amount_after_discount}", frmt.format(invoice.totalPrice) },
        { "{@Total_Credits_after_discount}", String.valueOf(invoice.customer.credit) },
      };
      for (String[] replacement : replacements) {
        template = template.replace(replacement[0], replacement[1]);
      }
    }

    return template;
  }
}
