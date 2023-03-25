package app;

import dao.StaffDao;

public class FindStaffbyid {
	public static void main(String[] args) throws InterruptedException {
		StaffDao dao = new StaffDao();
		dao.getstaffbyID(10);
	}
}
