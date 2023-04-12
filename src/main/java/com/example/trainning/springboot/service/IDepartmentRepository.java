package com.example.trainning.springboot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.trainning.springboot.entity.Department;
import com.example.trainning.springboot.entity.ResponseObject;

public interface IDepartmentRepository {
	
	List<Department> findAll();
	
	ResponseEntity<ResponseObject> insert(Department department);
	
	ResponseEntity<ResponseObject> update(Integer id,Department department);
	
	ResponseEntity<ResponseObject> findById(Integer id);

	ResponseEntity<ResponseObject> delete(Integer id);
}
