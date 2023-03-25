package app;

import dao.ProductDao;

public class FindProductbyid {
	public static void main(String[] args) throws InterruptedException {
		ProductDao dao = new ProductDao();
		dao.getproductid(23924);
	}
}
