package com.myclass.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclass.entity.Bill;
import com.myclass.entity.DetailBill;
import com.myclass.entity.Employee;
import com.myclass.entity.Product;
import com.myclass.repository.BillRepository;
import com.myclass.repository.DetailBillRepository;
import com.myclass.repository.EmployeeRepository;
import com.myclass.repository.ProductRepository;
import com.myclass.repository.UserRepository;

@Controller
@RequestMapping("admin/product")
@CrossOrigin(origins = "*")
public class ChooseController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private DetailBillRepository detailBillRepository;

	@Autowired
	private ProductRepository productRepository;

	private List<Product> products = new ArrayList<Product>();
	private int userId = 0;

	@RequestMapping(value = "choose/{id}", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
	public void index(@PathVariable("id") int id, Model model) {
		System.out.println("ID PRODUCT: " + id);
		Optional<Product> product = productRepository.findById(id);
		// int quantity = product.get().getQuantity() - 1;
		// product.get().setQuantity(quantity);
		// productRepository.save(new Product(id, product.get().getName(),
		// product.get().getQuantity(), product.get().getPrice(),
		// product.get().getDateProduct(), product.get().isBeverage(),
		// product.get().getStatusId(), product.get().isDelete()));
		System.out.println(product.toString());
		products.add(product.get());
		//System.out.println(products.size());
		return;
		// return "redirect:/admin/product";
	}

	@GetMapping("create-bill")
	public String createBill(Model model) {
//		for (Integer id : idListProducts) {
//			System.out.println(id);
//			Optional<Product> product = productRepository.findById(id);
//			int quantity = product.get().getQuantity() - 1;
//			product.get().setQuantity(quantity);
//			
//			System.out.println(product.toString());
//			products.add(product.get());
//		}
		model.addAttribute("listProduct", products);
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("userId", this.userId);
		float totalPrice = 0;
		if (products!=null) {
			for (Product product : products) {
				totalPrice += product.getPrice();
			}
			model.addAttribute("totalPrice", totalPrice);
			
		}
		
		return "bill/create-bill";
	}

	// test get list id product
	@PostMapping("create-bill")
	public void createPostBill(@RequestBody ArrayList<Integer> idListProducts, Model model) {
		for (Integer integer : idListProducts) {
			System.out.println(integer);
		}
//		model.addAttribute("listProduct", products);
//		model.addAttribute("users", userRepository.findAll());
//		model.addAttribute("userId", this.userId);
//		float totalPrice =0 ;
//		for (Product product : products) {
//			totalPrice+=product.getPrice();
//		}
//		model.addAttribute("totalPrice",totalPrice);
//		return "bill/create-bill";

	}

//	@PostMapping("get-user-id")
//	public String getUserId(Model model, HttpServletRequest req) {
//		int user_id =Integer.parseInt(req.getParameter("userId"));
//		this.userId=user_id;
//		System.out.println("Id User of bill: " + userId);
//		return "redirect:/admin/product/create-bill";
//
//	}
	@PostMapping("submit-create-bill")
	public String submitCreateBill(Model model, HttpServletRequest req) {

		this.userId = Integer.parseInt(req.getParameter("userId"));
		System.out.println("Id User of bill: " + userId);

		float price = 0;

		for (Product product : products) {
			price += product.getPrice();
		}

//		CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		System.out.println("name log in: " + employee.getUsername());
//		HttpSession session = req.getSession();
//		session.setAttribute("USER_LOGIN", employee.getUsername());
//		Employee employee1 = employeeRepository.findByEmail(employee.getUsername());

		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("USER_LOGIN");
		System.out.println("email in choooseController: " + email);
		Employee employee1 = employeeRepository.findByEmail(email);

		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date date = null;
		try {
			date = formatter.parse(formatter.format(now));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (date == null) {
			date = new Date();
		}
		Bill bill = new Bill(date, userId, employee1.getId(), price);

		billRepository.save(bill);

		for (Product product2 : products) {
			Optional<Product> product3 = productRepository.findById(product2.getId());
			DetailBill detailBill = new DetailBill(bill, product3.get(), 1, product3.get().getPrice());
			detailBillRepository.save(detailBill);
		}

		products = null;
		return "redirect:/admin/bill";
	}

	@GetMapping("create-bill/delete/{stt}")
	public String delete(@PathVariable("stt") int stt) {
		// Xóa Bill theo id
		System.out.println("STT DELETE CREATE BILL: " + (stt + 1));

		products.remove(stt);
		for (Product product1 : products) {
			System.out.println(product1.getId());
		}
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/product/create-bill";
	}

}
