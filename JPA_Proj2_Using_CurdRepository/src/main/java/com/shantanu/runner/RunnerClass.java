package com.shantanu.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shantanu.DataJpa1ProjApplication;
import com.shantanu.entity.Doctor;
import com.shantanu.service.IDoctorService;

@Component
public class RunnerClass implements CommandLineRunner {

   

 
	@Autowired
	private IDoctorService doctorService;
	
	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			/*
			To Insert the single object record 
			
			Doctor doc = new Doctor();
			doc.setDocName("Dr.Shantanu");
			doc.setSpecialization("Cardiologist");
			doc.setIncome(74000.0);
			String resultMessage = doctorService.registerDoctor(doc);
			System.out.println(resultMessage);
			
			 */
			
			//=> To chcek whether doctor is available or not
			Boolean flag = doctorService.isDoctorAvailable(102);
			if(flag) {
				System.out.println("Doctor is availabel");
			}
			else {
				System.out.println("Doctor is not availabel");
			}
			
			System.out.println("=============================================");
			
			//==> To Insert Multiple Doctor Information in the table
			/*
			List<Doctor> list = new ArrayList<>();
			list.add(new Doctor("Shantanu","Ortho",85000D));
			list.add(new Doctor("Vipul","Physio",94000D));
			list.add(new Doctor("Prafull","Pediastrician",74000D));
			list.add(new Doctor("Sagar","Ophthalmologist",88000D));
			String resultMess = doctorService.registerMultipleDoctor(list);
			System.out.println(resultMess);
			
			System.out.println("=============================================");*/
			
			
			//==> To View the all records in the table
			Iterable<Doctor> listOfDoctor = doctorService.getAllDoctor();
			System.out.println("List of all the Doctors Available : ");
			listOfDoctor.forEach(doctor->System.out.println(doctor));
			
			System.out.println("=============================================");
			
			//==> To get the total count of the record in the table
			Long count = doctorService.getRecordCount();
			System.out.println("Total No of Doctor is : "+count);
			
			System.out.println("=============================================");
			
			//==> To find the all doctor by id
			Iterable<Doctor> li = doctorService.getAllDoctorWithId(Arrays.asList(102,103));
			System.out.println("List of all the doctor with given id : ");
			li.forEach(doctor->System.out.println(doctor));
			
			System.out.println("=============================================");
			
			//==> To update the record based on the id
			String mess = doctorService.updateSpecialization(104,"Cardiac");
			System.out.println(mess);
			
			System.out.println("=============================================");
			
			//==> Find the Doctor with given id
			Doctor doctor = doctorService.getDoctorById(101);
			System.out.println(doctor);
			
//			Doctor d1 = doctorService.getDoctorById(113);
//			System.out.println(d1); <==this will throws an exception
			
			System.out.println("=============================================");
			
			
			//==> Delete the single doctor with given id 
			String del1 = doctorService.deleteDoctorById(103);
			System.out.println(del1);

			System.out.println("=============================================");
			
			
			//==> Delete the Single doctor based on Doctor Object
			Doctor doctor1 = new Doctor();
			doctor1.setDocId(102);
			String del2 = doctorService.deleteDoctorByObj(doctor1);
			System.out.println(del2);


			System.out.println("=============================================");

			//==> Delete Multiple Doctor based on id
			String del3 = doctorService.deleteAllDoctorWithID(Arrays.asList(109,111));
			System.out.println(del3);
			
			System.out.println("=============================================");
			
			Doctor d1 = new Doctor(); d1.setDocId(104);
			Doctor d2 = new Doctor(); d2.setDocId(107);
			Doctor d3 = new Doctor(); d3.setDocId(103);
			String del4 = doctorService.deleteAllDoctorByObj(Arrays.asList(d1,d2,d3));
			System.out.println(del4);
			
			System.out.println("=============================================");
			
			//==> If record are there in the db then delete all record
			String del5 = doctorService.deleteAllDoctor();
			System.out.println(del5);
			
			System.out.println("=============================================");
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
