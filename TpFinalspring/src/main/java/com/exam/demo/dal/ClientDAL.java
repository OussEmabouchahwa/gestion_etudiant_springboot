package com.exam.demo.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.demo.model.Client;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClientDAL extends JpaRepository<Client, Long> {

    @Modifying
    @Query("update Client c set c.nom = :name, c.adresse = :address where c.id = :id")
    void updateClient(@Param("name") String name, @Param("address") String address, @Param("id") Long id);


}

