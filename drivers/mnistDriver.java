package drivers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class mnistDriver {

	public static void main(String[] args) throws IOException {
		
		String path = "src/drivers/train.csv";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String line = br.readLine();
		System.out.println(line);
		
		
	}

}
