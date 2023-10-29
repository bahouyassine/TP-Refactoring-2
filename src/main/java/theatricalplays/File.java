package theatricalplays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import theatricalplays.Formats.PrintFormat;

import java.util.List;


public class File {
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
                new Performance("hamlet", 50),
                new Performance("as-like", 155),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();


        
        String test = statementPrinter.print(invoice,plays,PrintFormat.HTML);
        String filePath = "/home/local.isima.fr/yabahou/Desktop/TP-Refactoring-2/src/main/resources/output/index.html";
        File.writeToFile(filePath,test);
        System.out.println(test);

        }

}
