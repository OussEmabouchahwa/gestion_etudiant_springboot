package com.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.exam.demo.model.Client;
import com.exam.demo.model.Promoteur;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllData() {
        String sql = "SELECT c.*, COUNT(co.idcom) AS nombre_de_commandes FROM client c LEFT JOIN commande co ON c.id = co.client_id GROUP BY c.id ORDER BY c.id;";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getDataById(Long id) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
    public boolean getifpromo(Long id) {
        String sql = "SELECT promo FROM Client WHERE id = ?";
        Map<String, Object>  c=jdbcTemplate.queryForMap(sql, id);
        String promot=c.toString().substring(7,c.toString().length()-1);
        if(promot.compareTo("promoteur")==0)
        {
        	return false;
        }
    	return true;
    }
    public Promoteur convertClientToPromoteur(Client client) {
        Promoteur promoteur = new Promoteur(); 
        promoteur.setId(client.getId());
        promoteur.setNom(client.getNom());
        promoteur.setAdresse(client.getAdresse());
    	return promoteur;

        }
}
