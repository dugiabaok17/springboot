package com.example.trainning.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.trainning.springboot.entity.Department;
import com.example.trainning.springboot.entity.ResponseObject;
import com.example.trainning.springboot.repository.DepartmentRepository;
import com.example.trainning.springboot.service.IDepartmentRepository;

@Service
public class DepartmentServiceImpl implements IDepartmentRepository {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> findAll() {
		departmentRepository.findAll().forEach(data -> System.out.println(data));
		return departmentRepository.findAll();
	}

	@Override
	public ResponseEntity<ResponseObject> findById(Integer id) {
		Department department = departmentRepository.findById(id);
		if (department != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Query department successfully", 0, department));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("ok", "Cannot find department with id = " + id, 0, ""));

	}

	@Override
	public ResponseEntity<ResponseObject> insert(Department department) {
		for (Department departments : departmentRepository.findAll()) {
			if (departments.getName().equals(department.getName())) {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("failed", "Department name already taken", 1, ""));
			}
		}
		departmentRepository.save(department);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Insert Department successfully", 0, department));
	}

	@Override
	public ResponseEntity<ResponseObject> update(Integer id, Department department) {
		Department d = departmentRepository.findById(id);
		if (d != null) {
			for (Department departments : departmentRepository.findAll()) {
				if (departments.getName().equals(department.getName())) {
					return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
							.body(new ResponseObject("failed", "Department name already taken", 1, ""));
				}
			}
			d.setName(department.getName());
			departmentRepository.update(d);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Update Department successfully", 0, d));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("failed", "Cannot find department with id = " + id, 0, ""));
	}


	@Override
	public ResponseEntity<ResponseObject> delete(Integer id) {
		Department department = departmentRepository.findById(id);
		if (department != null) {
			departmentRepository.delete(department);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Delete department successfully", 0, department));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("ok", "Cannot find department with id = " + id, 0, ""));

	}

}
