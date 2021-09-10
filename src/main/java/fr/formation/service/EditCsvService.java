package fr.formation.service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EditCsvService {

	private String filepath = "src/main/resources/static/assets/data/argentsomme2.csv";
	
	// public static void main(String[] args) {
	// 	run2();
	// }
	
	// public static void run() {
	// 	EditCsvService ecs = new EditCsvService();
	// 	List<List<String>> result = ecs.read();
	// 	for(List<String> ligne : result) {
	// 		System.out.println(ligne.toString());
	// 	}
	// }
	
	// public static void run2() {
	// 	EditCsvService ecs = new EditCsvService();
		
	// 	LocalDate uneDate = LocalDate.of(2021, 1, 1);
	// 	BigDecimal[] desValues = new BigDecimal[3];
	// 	desValues[0] = new BigDecimal(40000);
	// 	desValues[1] = new BigDecimal(50000);
	// 	desValues[2] = new BigDecimal(90000);
	// 	ecs.write(uneDate, desValues);
		
	// 	List<List<String>> result = ecs.read();
	// 	for(List<String> ligne : result) {
	// 		System.out.println(ligne.toString());
	// 	}
	// }
	
	public String read() {
		FileReader myFileReader = null;
		BufferedReader myBufferedReader = null;
		//List<List<String>> records = new ArrayList<>();
		StringBuilder records = new StringBuilder();

		try {
			String ligne = "";
			myFileReader = new FileReader(filepath);
			myBufferedReader = new BufferedReader(myFileReader);
			while ((ligne = myBufferedReader.readLine()) != null) {
				// System.out.println(ligne);
				// on a la ligne brute, il faut en extraire les valeurs
				//List<String> record = new ArrayList<String>(Arrays.asList(ligne.split("\\s*,\\s*")));
				//records.add(record);
				records.append(ligne);
				records.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				myBufferedReader.close();
				myFileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return records.toString();
	}

	public void write(LocalDate date, BigDecimal[] values) {
		FileWriter myFileWriter = null;
		BufferedWriter myBufferedWriter = null;
		try {
			myFileWriter = new FileWriter(filepath, true);
			myBufferedWriter = new BufferedWriter(myFileWriter);
			String ligne = date.toString();
			for(BigDecimal d : values) {
				ligne += "," + d.toString();
			}
			myBufferedWriter.write(ligne);
			myBufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				myBufferedWriter.close();
				myFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
