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

    private static StringBuffer PerformanceInvoiceTxtFormat(List<PerformanceInvoice> performanceInvoices){
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        StringBuffer result = new StringBuffer("");
        for (PerformanceInvoice perf_inv : performanceInvoices) {
            result.append(String.format(
            "  %s: %s (%s seats)\n" ,
            perf_inv.playName,
            frmt.format(perf_inv.Amount),
            perf_inv.audience
            ));
        }
        
        return result;
    }

    public static String toText(Invoice invoice,Map<String, Play> plays)  {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        String filePath = "/home/local.isima.fr/yabahou/Desktop/TP-Refactoring-2/src/main/resources/template/TemplateTxt.txt";
        String template = File.readFileToString(filePath);
        invoice.CalculateInvoice(plays);
        String performanceInvoiceTxt = PerformanceInvoiceTxtFormat(invoice.performanceInvoices).toString();
        String[][] replacements = {
            {"{@Customer_Name}", invoice.customer.name+"\n"},
            {"{@Performance_Invoice}", performanceInvoiceTxt},
            {"{@Invoice_Amount}", frmt.format((invoice.totalPrice))+"\n"},
            {"{@Total_Credits}", String.valueOf(invoice.credit)}
        };
        for (String[] replacement : replacements) {
            template = template.replace(replacement[0], replacement[1]);
            }
        template = template +"\n" +DiscountToText(invoice.totalPrice,invoice.credit);
        return template;
    }

    public static String DiscountToText(double totalPrice,int credit)  {
        String template = "";
        if (Invoice.ApplyReduction(totalPrice,credit)){
            NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
            String filePath = "/home/local.isima.fr/yabahou/Desktop/TP-Refactoring-2/src/main/resources/template/TemplateTxtDiscount.txt";
            template = File.readFileToString(filePath);
            String[][] replacements = {
                {"{@enter}","\n"}, 
                {"{@Invoice_Amount_after_discount}", frmt.format(totalPrice)},
                {"{@Total_Credits_after_discount}", String.valueOf(credit)}
            };
            for (String[] replacement : replacements) {
                template = template.replace(replacement[0], replacement[1]);
            }

        } 

        return template;
    }

}
