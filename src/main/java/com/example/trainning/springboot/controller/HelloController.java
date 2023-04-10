package com.example.trainning.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainning.springboot.entity.Department;
import com.example.trainning.springboot.entity.ResponseObject;
import com.example.trainning.springboot.service.IDepartmentRepository;
import com.example.trainning.springboot.service.impl.DepartmentServiceImpl;

@RestController
@RequestMapping("/api/v1/departments")
public class HelloController {

	@Autowired
	private IDepartmentRepository iDepartmentRepository;

	@GetMapping(value = "", produces = "application/json")
	public String hello() {
		return "Welcome to VTI.D2";
	}

	@GetMapping
	public List<Department> getListDepartment() {
		return iDepartmentRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> findByDepartmentId(@PathVariable Integer id) {
		return iDepartmentRepository.findById(id);
	}


	@PostMapping
	public ResponseEntity<ResponseObject> insertDepartment(@RequestBody Department department) {
		return iDepartmentRepository.insert(department);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateDepartment( @PathVariable Integer id,@RequestBody Department department) {
		return iDepartmentRepository.update(id,department);
	}
}
