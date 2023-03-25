package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.ProductDao;
import entity.Product;

public class InsertMany {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		
		Product pd = new Product(23924, "Trek", "Mountain Bikes",Arrays.asList("red"), 2016, "Trek 820 - 2016", 379);
		Product pd1 = new Product(23925, "Trek", "Mountain Bikes",Arrays.asList("red"), 2016, "Trek 820 - 2016", 379);
		Product pd2 = new Product(23926, "Trek", "Mountain Bikes",Arrays.asList("red"), 2016, "Trek 820 - 2016", 379);
		
		List<Product> list = new ArrayList<Product>();
		list.add(pd);
		list.add(pd1);
		list.add(pd2);
		
		boolean insert = dao.insertMany(list);
		System.out.println(insert);
	}
}	
