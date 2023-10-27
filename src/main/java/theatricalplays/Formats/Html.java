package theatricalplays.Formats;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import theatricalplays.File;
import theatricalplays.Invoice;
import theatricalplays.PerformanceInvoice;
import theatricalplays.Play;

public class Html {
    private static StringBuffer PerformanceInvoiceHtmlFormat(List<PerformanceInvoice> performanceInvoices){
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        StringBuffer result = new StringBuffer("");
        for (PerformanceInvoice perf_inv : performanceInvoices) {
            result.append(String.format(
            "<tr>\n"
            + "<td>%s</td>\n"
            + "<td>%s</td>\n"
            + "<td>%s</td>\n"
            + "</tr>\n" ,
            perf_inv.playName,
            perf_inv.audience,
            frmt.format(perf_inv.Amount)
            ));
        }
        
        return result;
        }
    public static String toHtml(Invoice invoice,Map<String, Play> plays) {
        String htmlFilePath =  "src/main/resources/template/TemplateHtml.html";
        String htmlTemplate = File.readFileToString(htmlFilePath);
        invoice.CalculateInvoice(plays);
        String performanceInvoiceHtml = PerformanceInvoiceHtmlFormat(invoice.performanceInvoices).toString();
        String[][] replacements = {
            {"{@Customer_Name}", invoice.customer.name},
            {"{@Performance_Invoice}", performanceInvoiceHtml},
            {"{@Invoice_Amount}", String.valueOf(invoice.totalPrice)},
            {"{@Total_Credits}", String.valueOf(invoice.credit)},
        };

        for (String[] replacement : replacements) {
            htmlTemplate = htmlTemplate.replace(replacement[0], replacement[1]);
        }

        return htmlTemplate;
    }




}
