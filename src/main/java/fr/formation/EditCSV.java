package fr.formation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditCSV {
    public static void main(String args[]){
    	// Simulation-Ile/src/main/webapp/assets/data/argentsomme2.csv
    	// Simulation-Ile/src/main/java
        try (PrintWriter writer = new PrintWriter(new File("src/main/webapp/assets/data/argentsomme2.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("date");
            sb.append(',');
            sb.append("value");
            sb.append('\n');

            sb.append("01-01-2021");
            sb.append(',');
            sb.append("20000.00");
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        EditCSV editCSV = new EditCSV();
        editCSV.readCSVFile();
    }

    public void readCSVFile(){
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/webapp/assets/data/argentsomme2.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(records.toString());
    }
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}
