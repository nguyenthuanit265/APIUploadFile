package com.myclass.controller;

import java.util.Optional;

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

import com.myclass.entity.Employee;
import com.myclass.entity.Product;
import com.myclass.repository.EmployeeRepository;
import com.myclass.repository.RoleRepository;

@Controller
@RequestMapping("admin/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("employees", employeeRepository.myFindAll());
		return "employee/index";
	}
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("roles", roleRepository.findAll());
		return "employee/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("employee") Employee employee, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
			model.addAttribute("roles", roleRepository.findAll());
			return "employee/add";
		}
		
		Employee employee2 = employeeRepository.findByEmail(employee.getEmail());
		if (employee2!=null) {
			model.addAttribute("message", "email da ton tai");
			model.addAttribute("roles", roleRepository.findAll());
			return "employee/add";
		}

		employeeRepository.save(employee);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/employee";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		System.out.println("ID BEFORE: " + id);
		// model.addAttribute("id", id);
		model.addAttribute("employee", employeeRepository.findById(id));
		model.addAttribute("roles", roleRepository.findAll());
		return "employee/edit";
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("employee") Employee employee, BindingResult errors) {
		// Bắt lỗi nhập liệu
		if (errors.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("roles", roleRepository.findAll());
			return "employee/edit";
		}
		System.out.println("ID AFTER: " + employee.getId());
		// Cập nhật User
		employeeRepository.saveAndFlush(employee);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/employee";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		employee.get().setDelete(true);
		employeeRepository.saveAndFlush(employee.get());
		//productRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/employee";
	}
	
	@GetMapping("is-delete")
	public String isDelete(Model model) {
		model.addAttribute("employees", employeeRepository.findAllIsDelete());
		return "employee/is-delete";
	}
	
	@GetMapping("restore/{id}")
	public String Restore(@PathVariable("id") int id, Model model) {
		System.out.println("ID Restore: " + id);
		Optional<Employee> employee=employeeRepository.findById(id);
		employee.get().setDelete(false);
		employeeRepository.saveAndFlush(employee.get());
		//productRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/employee";
		// Chuyển hướng về trang danh sách
	
	}
}
