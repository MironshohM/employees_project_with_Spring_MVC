package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        service=employeeService;
    }


    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Employee> employees=service.findAll();

        model.addAttribute("employees",employees);

        return "employees/list-employees";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // Create model attribute to bind  form data
        Employee theEmployee=new Employee();


        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){


        service.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String saveEmployee(@RequestParam("employeeId")int id,Model model){

        Employee theEmployee=service.findById(id);

        model.addAttribute("employee",theEmployee);

        return "employees/employee-form";

    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId")int id){

        service.deleteById(id);

        return "redirect:/employees/list";

    }







}
