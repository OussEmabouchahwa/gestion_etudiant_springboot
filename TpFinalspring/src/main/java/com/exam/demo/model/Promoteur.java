package com.exam.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@SuppressWarnings("deprecation")
@Data
@Entity
@DiscriminatorValue("promoteur")
@Table
public class Promoteur extends Client{
	
	@Column
	private int categorie;
	   
	@Column
	private String codeAccord;
	
	
	public Promoteur() {
		
	}
	public int getCategorie() {
		return this.categorie;
	}
	public void setCategorie(int cat) {
		this.categorie=cat;
	}
	
	public void setCodeAccord(String code) {
		this.codeAccord=code;
	}
	public String getCodeAccord() {
		
		return this.codeAccord;
	}
}
