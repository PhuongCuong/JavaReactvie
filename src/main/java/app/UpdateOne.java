package app;

import dao.ProductDao;
import entity.Product;

public class UpdateOne {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		
		Product p = new Product();
		
		p.setId(23926);
		p.setPrice(100);
		
		boolean rs = dao.updateOne(p);
		System.out.println(rs);
	}
}
