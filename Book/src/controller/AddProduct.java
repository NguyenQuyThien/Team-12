package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Image;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIRECTORY = "F:\\PTIT\\ki1nam4\\laptrinhweb\\font-end-12\\Book\\WebContent\\admin\\upload";

	public AddProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null == session.getAttribute("email")) {
			// User is not logged in.
			response.sendRedirect("/Book/admin/Login");
		} else {

			ArrayList<Category> listCategory = new CategoryDAO().getAll();
			request.setAttribute("categorys", listCategory);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/them-san-pham");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null == session.getAttribute("email")) {
			// User is not logged in.
			response.sendRedirect("/Book/admin/Login");
		} else {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");

			String userName = request.getParameter("name");
			String idCategory = request.getParameter("idCategory");
			String author = request.getParameter("author");
			String publicAt = request.getParameter("publicAt");
			String count = request.getParameter("count");
			String price = request.getParameter("price");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String type = request.getParameter("type");

			long milliseconds = 0;
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			try {
				Date d = f.parse(publicAt);
				milliseconds = d.getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Product p = new Product();
			p.setName(userName);
			p.setPrice(Double.parseDouble(price));
			p.setAuthor(author);
			p.setCategory(new Category(Long.parseLong(idCategory)));
			p.setCount(Long.parseLong(count));
			p.setPublicAt(new Timestamp(milliseconds));
			p.setTitle(title);
			p.setDescription(description);
			p.setType(type);
			p.setCreateBy(Long.parseLong(session.getAttribute("idAdmin").toString()));

			String nameImg = "image" + new Date().getTime();

			Image thumbnail = new Image();
			thumbnail.setName(nameImg + "1.png");
			thumbnail.setType((long) 1);

			p.setThumbnail(thumbnail);

			ArrayList<Image> listImg = new ArrayList<>();

			Image img1 = new Image();
			img1.setName(nameImg + "2.png");
			img1.setType((long) 2);

			Image img2 = new Image();
			img2.setName(nameImg + "3.png");
			img2.setType((long) 2);

			Image img3 = new Image();
			img3.setName(nameImg + "4.png");
			img3.setType((long) 2);

			listImg.add(img1);
			listImg.add(img2);
			listImg.add(img3);

			p.setListImage(listImg);

			System.out.println(p.toString());

			if (new ProductDAO().insertProduct(p)) {

				Part part1 = request.getPart("thubnail");
				Part part2 = request.getPart("image1");
				Part part3 = request.getPart("image2");
				Part part4 = request.getPart("image3");

				String savePath1 = UPLOAD_DIRECTORY + File.separator + nameImg + "1.png";
				String savePath2 = UPLOAD_DIRECTORY + File.separator + nameImg + "2.png";
				String savePath3 = UPLOAD_DIRECTORY + File.separator + nameImg + "3.png";
				String savePath4 = UPLOAD_DIRECTORY + File.separator + nameImg + "4.png";

				part1.write(savePath1 + File.separator);
				part2.write(savePath2 + File.separator);
				part3.write(savePath3 + File.separator);
				part4.write(savePath4 + File.separator);

			}

			response.sendRedirect("/Book/admin/listProduct");
		}
	}
}
