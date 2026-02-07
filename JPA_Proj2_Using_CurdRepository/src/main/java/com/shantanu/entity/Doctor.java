
package com.shantanu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JPA_DOCTOR_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	@Column(name="Doc_ID")
	@Id
	@SequenceGenerator(name="gen1", sequenceName="doc_seq",initialValue=101,allocationSize=1)
	@GeneratedValue(generator="gen1",strategy=GenerationType.SEQUENCE)
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer docId;

	@Column(name="Doc_Name",length=25)
	private String docName;
	
	@Column(name="Specialization",length=25)
	private String specialization;
	
	@Column(name="Income")
	private Double income;
	
	
	 // Custom constructor
	public Doctor(String docName, String specialization, Double income) {
	    this.docName = docName;
	    this.specialization = specialization;
	    this.income = income;
	}

}
	