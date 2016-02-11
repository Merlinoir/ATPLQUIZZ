package com.atplquiz.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.atplquiz.entity.Question;
import com.atplquiz.entity.QuestionReponse;
import com.atplquiz.entity.Reponse;

public class ResultatService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	List<Reponse> reponseList = new ArrayList<Reponse>();
	int veracityOK = 0;
	int veracityKO = 0;
	//Récupération de la véracité de chaque réponse de l'utilisateur
	
	public boolean checkVeracityByIdQuestion(long id){
		Reponse reponse = new Reponse();
		reponseList = this.jdbcTemplate.query("select * from reponse where id_question =" + id,
				new RowMapper<Reponse>() {
			public Reponse mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				reponse.setVeracite(rs.getBoolean("veracite"));
				
				return reponse;
			}
			
		});
		if(reponse.isVeracite()){
			veracityOK++;
		}else{
			veracityKO++;
		}
		return reponse.isVeracite();
		
	}
	public String recupererResultat(){
		String s = "Nombre de réponses bonnes: "+veracityOK+"\n Nombre de réponses fausses: "+veracityKO;
		return s;
		
	}
		
		
		
}

	
