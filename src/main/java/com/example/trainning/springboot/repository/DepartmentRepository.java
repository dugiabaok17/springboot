package com.example.trainning.springboot.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.trainning.springboot.entity.Department;

@Repository
public class DepartmentRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Department> findAll() {
		return entityManager.createQuery("SELECT u FROM Department u", Department.class).getResultList();
	}

	@Transactional
	public void save(Department department) {
		entityManager.persist(department);
	}

	@Transactional
	public void update(Department department) {
		entityManager.merge(department);
	}


	@Transactional
	public void delete(Department department) {
		entityManager.remove(department);
	}

	public Department findById(Integer id) {
		return entityManager.find(Department.class, id);
	}
}
