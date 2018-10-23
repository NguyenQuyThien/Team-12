package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BillDAO;
import model.Bill;

public class ListBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListBill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Bill> bills = new BillDAO().getAll();
		
		request.setAttribute("bills", bills);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/danh-sach-don-hang");
        rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
