package app;

import dao.ProductDao;
import entity.Product;

public class Addafield {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		
		Product p = new Product();
		p.setId(23924);
		dao.addfield(p);
	}
}
