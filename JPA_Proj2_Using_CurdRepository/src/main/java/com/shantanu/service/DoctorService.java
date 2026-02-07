package com.shantanu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shantanu.dao.IDoctorRepo;
import com.shantanu.entity.Doctor;

@Service("doctorService")
public class DoctorService implements IDoctorService {

	@Autowired
	private IDoctorRepo doctorRepo;
	
	// To Insert the single doctor Info
	@Override
	public String registerDoctor(Doctor doctor) {
		
		System.out.println("Doctor Id befor saving : "+doctor.getDocId());
		Doctor doc = doctorRepo.save(doctor);
		return "Doctor Object is saved with id value "+doc.getDocId();
	}

	//To Check whether given doctor is available or not
	@Override
	public Boolean isDoctorAvailable(Integer id) {
		Boolean b = doctorRepo.existsById(id);
		return b;
	}
	
	//To insert the multiple doctor information
		@Override
		public String registerMultipleDoctor(List<Doctor> list) {
			Iterable<Doctor> listOfDoctor = doctorRepo.saveAll(list);
			List<Integer> li = StreamSupport.stream(listOfDoctor.spliterator(), false).map(Doctor::getDocId).collect(Collectors.toList());
			li.forEach(id->System.out.println("Doctor Id : "+id));
			return "Total "+li.size()+" Doctor Registred Successfully with the above id";		
		}

	// To See all the doctor select operation
	@Override
	public Iterable<Doctor> getAllDoctor() {
		Iterable<Doctor> list = doctorRepo.findAll();
		return list;
	}
	
	
	//To get all the record count 
    @Override
    public Long getRecordCount() {
    	return doctorRepo.count();
    }
    
    //To get all the doctor by ids
	@Override
	public Iterable<Doctor> getAllDoctorWithId(Iterable<Integer> ids) {
		return doctorRepo.findAllById(ids);
	}

	// To get the single record based on the id
	@Override
	public Doctor getDoctorById(Integer id) {
		 //Doctor doc = doctorRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("NO Doctor with given id is there"));
		 							//OR
		 //Doctor doc = doctorRepo.findByIf(id).orElse(new Doctor(null,"Specialist",null))
		 							//OR
		 Optional<Doctor> doc = doctorRepo.findById(id);
		 if(doc.isPresent()) return doc.get(); else throw new IllegalArgumentException("No Doctor given with id is there");
	}

	//To update the record based on the id
	@Override
	public String updateSpecialization(Integer id, String Spec) {

		Optional<Doctor> doc = doctorRepo.findById(id);
		if(doc.isPresent()) {
			Doctor d = doc.get();
			d.setSpecialization(Spec);
			doctorRepo.save(d);
			return "Doctor with "+id+" is successfully updated.";
		}
		else {
			return "Doctor with "+id+" is not found";
		}
	}

	
	// Delete Operations : 
	// 1. Delete single doctor with given id 
	@Override
	public String deleteDoctorById(Integer id) {
		Optional<Doctor> doc = doctorRepo.findById(id);
		if(doc.isPresent()) {
			doctorRepo.deleteById(id);
			return "Doctor with id "+id+" is deleted Successully";
		}
		else {
			return "Doctor with id "+id+" is not found"; 
		}
	}

	// 2. Delete Single Doctor based on its object
	@Override
	public String deleteDoctorByObj(Doctor d) {
		Optional<Doctor> doc = doctorRepo.findById(d.getDocId());
		if(doc.isPresent()) {
			doctorRepo.delete(d);
			return "Doctor with id "+d.getDocId()+" is deleted Successully";
		}
		return "Doctor with id "+d.getDocId()+" is not found"; 
	}
	
	// 3. Delete All Doctor with the given ids
	@Override
	public String deleteAllDoctorWithID(Iterable<Integer> id) {
		Iterable<Doctor> doctorList = doctorRepo.findAllById(id);
		long count = StreamSupport.stream(doctorList.spliterator(), false).count();
		
		if(count>0) {
			doctorRepo.deleteAllById(id);
			return count+" Doctor Records found, and all are deleted";
		}
		else return "No Record Found to delete";
	}
	
	// 4. Delete All Doctor based on the Doctor's Object given in the List
	@Override
	public String deleteAllDoctorByObj(Iterable<Doctor> doctor) {
		List<Integer> ids = StreamSupport.stream(doctor.spliterator(),false).map(doc->doc.getDocId()).collect(Collectors.toList());
		Iterable<Doctor> doctorList = doctorRepo.findAllById(ids);
		long count = StreamSupport.stream(doctorList.spliterator(), false).count();
		if(count>0) {
			doctorRepo.deleteAll(doctorList);
			return count+" Doctor Records found, and all are deleted";
		}
		return "No Record Found to delete";
	}
			
		
	// 5. Delete All Doctors Record from the db
	@Override
	public String deleteAllDoctor() {
		long count = doctorRepo.count();
		if(count>0) {
			doctorRepo.deleteAll();
			return count+" records was there so, all are deleted";
		}
		return "No Records Found to delete";
	}	
}
