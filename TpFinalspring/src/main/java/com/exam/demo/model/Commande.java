package com.exam.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcom;
    
    
    @Column
    private int annee;
    
    @Column
    private int mois;
    
    @Column
    private int jour;

    @ManyToOne
    private Client client;
    
    public Commande() {
    	
    }
    
	public Long getIdCom() {
		return this.idcom;
	}
	
	public void setAnnee(int annee) {
		this.annee=annee;
	}
	public int getAnnee() {
		
		return this.annee;
	}

	public void setMois(int mois) {
		this.mois=mois;
	}
	public int getMois() {
		
		return this.mois;
	}
	
	public void setJour(int jour) {
		this.jour=jour;
	}
	public int getJour() {
		
		return this.jour;
	}
	
}
