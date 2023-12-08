package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
    private final String message;
    private final Admin publisher;

    public Report(String message, Admin publisher) {
        this.message = message;
        this.publisher = publisher;
    }
    public Admin getPublisher() {
        return publisher;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message + "//" +
                publisher +
                '\n';
    }

    public static void saveReportToFile(Report report) {
        try {
            FileWriter writer = new FileWriter("DataBase//Reports.txt", true);
            writer.write(report.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList loadReportsFromFile() {
        LinkedList Reports = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("DataBase//Reports.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("//");
                String[] publisherParts = parts[1].split("/");
                Admin admin = new Admin(publisherParts[0], publisherParts[1], publisherParts[2]);
                Report report = new Report(parts[0], admin);
                Reports.insert(report);
            }
            br.close();
            FileWriter writer = new FileWriter("DataBase//Reports.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return Reports;
    }
}
