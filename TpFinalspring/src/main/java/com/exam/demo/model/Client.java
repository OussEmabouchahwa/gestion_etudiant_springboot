package com.exam.demo.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@SuppressWarnings("deprecation")
@Data
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="promo",discriminatorType=DiscriminatorType.STRING,length=12)
@DiscriminatorValue("nonpromoteur")
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @Size(max=40,min=3,message = "{nom_errorl}")
    private String nom;
    
    @Column
    @NotBlank(message = "{adr_error}")
    private String adresse;
    
    @OneToMany
    private Set<Commande> commandes;
    
	public Client() {
		
	}   
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public String getNom() {
		
		return this.nom;
	}

	public void setAdresse(String adresse) {
		this.adresse=adresse;
	}
	public String getAdresse() {
		
		return this.adresse;
	}

	
}
