package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entites.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		System.out.print("Enter how many products will be added: ");
		int n = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter file path: ");
		String path = sc.nextLine();

		File sourceFile = new File(path);
		String sourceFolderStr = sourceFile.getParent();

		boolean success = new File(sourceFolderStr + "\\out\\").mkdir();
		if (success) {
			System.out.println("Directory created successfully.");
		} else {
			System.out.println("Directory already exists or could not be created.");
		}

		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

		for (int i = 0; i < n; i++) {
			System.out.println("Enter the details of product " + (i + 1) + ":");

			System.out.print("Name: ");
			String name = sc.nextLine();

			System.out.print("Price: ");
			double price = sc.nextDouble();

			System.out.print("Quantity: ");
			int quantity = sc.nextInt();
			sc.nextLine(); // Consume the newline character

			Product product = new Product(name, price, quantity);
			list.add(product);
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr, true))) {
			for (Product product : list) {
				bw.write(product.getName() + "," + String.format("%.2f", product.getPrice()) + ","
						+ product.getQuantity() + "," + String.format("%.2f", product.total()));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}
}