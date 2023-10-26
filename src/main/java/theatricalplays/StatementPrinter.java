package theatricalplays;

import java.util.Map;

import theatricalplays.Formats.Html;
import theatricalplays.Formats.PrintFormat;
import theatricalplays.Formats.Txt;




public class StatementPrinter {


public String print(Invoice invoice,Map<String, Play> plays,PrintFormat format)  {
  
  switch (format) {
    case HTML:
      return Html.toHtml(invoice,plays);
    case TXT:
      return Txt.toText(invoice,plays);
    default:
      return "This format is not supported";
  }

}












}
