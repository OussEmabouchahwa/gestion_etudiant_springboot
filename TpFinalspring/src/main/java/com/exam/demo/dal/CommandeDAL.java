package com.exam.demo.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exam.demo.model.Commande;

public interface CommandeDAL extends JpaRepository<Commande,Long> {
	List<Commande> findAllByClientId(Long id);
	
	@Modifying
	@Query("DELETE FROM Commande c WHERE c.client.id = ?1")
	void deleteAllByClientId(Long id);
}
