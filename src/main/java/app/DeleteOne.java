package app;

import dao.ProductDao;
import entity.Product;

public class DeleteOne {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		Product p = new Product();
		p.setId(23925);
		dao.deleteOne(p);
	}
}
