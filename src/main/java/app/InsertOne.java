package app;

import java.util.Arrays;

import dao.ProductDao;
import entity.Product;

public class InsertOne {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		Product pd = new Product(23923, "Trek", "Mountain Bikes",Arrays.asList("red"), 2016, "Trek 820 - 2016", 379);
		boolean rs = dao.insertOnes(pd);
		System.out.println(rs);
	}
}
