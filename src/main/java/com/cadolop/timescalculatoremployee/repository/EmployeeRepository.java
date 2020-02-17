package com.cadolop.timescalculatoremployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadolop.timescalculatoremployee.model.Employee;

/**
 * The interface repository employee JPA
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}