package com.exam.demo.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.demo.model.Promoteur;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface PromoteurDAL extends JpaRepository<Promoteur, Long> {

    @Modifying
    @Query("update Client p set p.nom =?1, p.adresse = ?2, p.categorie = ?3, p.codeAccord = ?4 where p.id = ?5")
    void updatePromoteur(String name,String address,Integer categorie,String codeAccord,Long id);
}