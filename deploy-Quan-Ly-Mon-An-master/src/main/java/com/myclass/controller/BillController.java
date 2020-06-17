package com.myclass.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Bill;
import com.myclass.entity.DetailBill;
import com.myclass.repository.BillRepository;
import com.myclass.repository.DetailBillRepository;
import com.myclass.repository.EmployeeRepository;
import com.myclass.repository.UserRepository;

@Controller
@RequestMapping("admin/bill")
public class BillController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired 
	private DetailBillRepository detailBillRepository;
	
	@GetMapping("")
	public String index(Model model) {
		
		List<Bill> bills = billRepository.findAll();
//		for (Bill bill : bills) {
//			if (bill.getUserId()==0)
//		}
		
		
		model.addAttribute("bills", bills);
		return "bill/index";
		
	}

	@GetMapping("detail/{id}")
	public String getDetail(Model model, @PathVariable("id") int id) {
		System.out.println("ID EDIT: " + id);
		
		List<DetailBill> detailBill = (List<DetailBill>) detailBillRepository.getOneDetailBillById_Bill(id);
		float price=0;
		for (DetailBill item : detailBill) {
			price +=item.getProduct().getPrice();
		}
		
		
		model.addAttribute("details", detailBill);
		return "bill/detail";
		
	}

	@GetMapping("add")
	public String add(Model model) {
		
		
		model.addAttribute("bill", new Bill());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		
		return "bill/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("bill") Bill bill, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			
			model.addAttribute("employees", employeeRepository.findAll());
			model.addAttribute("users", userRepository.findAll());
			return "bill/add";
		}
		
		bill.setDateBill(new Date());

		billRepository.save(bill);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/bill";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		System.out.println("ID BEFORE: " + id);
		// model.addAttribute("id", id);
		model.addAttribute("bill", billRepository.findById(id));
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		return "bill/edit";
	}

	@PostMapping("edit")
	public String edit(Model model,@Valid @ModelAttribute("bill") Bill bill, BindingResult errors) {
		// Bắt lỗi nhập liệu
		if (errors.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("users", userRepository.findAll());
			return "bill/edit";
		}
		System.out.println("ID AFTER: " + bill.getId());
		// Cập nhật 
		bill.setDateBill(new Date());
		billRepository.saveAndFlush(bill);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/employee";
	}
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		// Xóa User theo id
		billRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/bill";
	}
}
