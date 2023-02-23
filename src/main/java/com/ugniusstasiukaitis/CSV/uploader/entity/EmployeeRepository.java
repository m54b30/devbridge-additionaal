package com.ugniusstasiukaitis.CSV.uploader.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeData, Integer> {
}
