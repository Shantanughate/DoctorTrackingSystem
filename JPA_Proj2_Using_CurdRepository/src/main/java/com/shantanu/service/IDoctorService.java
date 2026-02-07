package com.shantanu.service;

import java.util.List;

import com.shantanu.entity.Doctor;

public interface IDoctorService {
	
	public String registerDoctor(Doctor doctor);

	public String registerMultipleDoctor(List<Doctor> list);
	
	//=================================================================== 2 save method
	
	
	public Iterable<Doctor> getAllDoctor();

	public Iterable<Doctor> getAllDoctorWithId(Iterable<Integer> ids);
	
	public Doctor getDoctorById(Integer id);
	
	//=================================================================== 3 finder methods 
	
	public String deleteDoctorById(Integer id);
	
	public String deleteDoctorByObj(Doctor doc);
	
	public String deleteAllDoctorWithID(Iterable<Integer> id);
	
	public String deleteAllDoctorByObj(Iterable<Doctor> doctor);
	
	public String deleteAllDoctor();
	

	//=================================================================== 5 deletes methods
	
	
	
	public Boolean isDoctorAvailable(Integer id); //===================== 1 check availability method

	public Long getRecordCount(); // ==================================== 1 counting record method
	
	public String updateSpecialization(Integer id,String Spec);	 //===== up-dation using findById
}
