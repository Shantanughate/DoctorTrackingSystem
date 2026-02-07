package com.shantanu.dao;

import org.springframework.data.repository.CrudRepository;

import com.shantanu.entity.Doctor;

public interface IDoctorRepo extends CrudRepository<Doctor,Integer>{

}
