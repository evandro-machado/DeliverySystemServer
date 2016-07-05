package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

public class MainTest {
	public static void main(String[] args) throws IOException {
//		String fileName = "C:/robots.jpg";
//		FileInputStream fis = new FileInputStream(fileName);
//		byte[] b = new byte[1];
//		int i;
//		while ((i = fis.read(b)) != -1) {
////			System.out.println(fis.);
//		}
		
		  final String DELIVERIES = "Deliveries";
	      final String DELIVERY_ID = "_id";
	      final String DELIVERY_ADDRESS = "address";
	      final String DELIVERY_DESCRIPTION = "description";
	      final String DELIVERY_STATUS = "status";
	      final String DELIVERY_IMAGE = "image";
	      final String DELIVERY_EMPLOYEE_ID = "image";


	      final String DATABASE_NAME = "Deliveries.db";
	      final int DATABASE_VERSION = 1;

	      final String DATABASE_CREATE = "create table " +
	            DELIVERIES + "(" + DELIVERY_ID + " integer primary key, " +
	            DELIVERY_ADDRESS + " text not null, " + DELIVERY_DESCRIPTION + " text not null, " +
	            DELIVERY_STATUS + " text not null, " + DELIVERY_IMAGE + " blob, " +
	            DELIVERY_EMPLOYEE_ID + " integer);";
	      
	      System.out.println(DATABASE_CREATE);
	}
}
