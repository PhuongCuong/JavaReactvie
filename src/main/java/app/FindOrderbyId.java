package app;

import org.bson.types.ObjectId;

import dao.OrderDao;
import entity.Customer;
import entity.Order;

public class FindOrderbyId {
	public static void main(String[] args) throws InterruptedException {
		OrderDao dao = new OrderDao();
		Order or = new Order();
		or.setId(new ObjectId("615279c4dc90aa2be71fd8f9"));
		//or.getCustomer().setCustomerID("DEBR1");
		dao.getorderbyid(new ObjectId("615279c4dc90aa2be71fd8f9"));
		//dao.getCustomerById("DESH11");
	}
}
