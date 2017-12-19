package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO GRADE (CODE,NBHEURESBASE,TAUXBASE) VALUES (?,?,?)";
		this.jdbcTemplate.update(sql,
				nouveauGrade.getCode(),
				nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase()
		);
	}
	
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE GRADE SET CODE=?,NBHEURESBASE=?,TAUXBASE=? WHERE ID=?";
		this.jdbcTemplate.update(sql,  
				grade.getCode(),
				grade.getNbHeuresBase(),
				grade.getTauxBase(),
				grade.getId()
		);
	}

	public List<Grade> lister() {
		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}
	
	public Grade getGradeByCode(String code) {
		String sql = "SELECT * FROM GRADE where CODE = ?";
		return this.jdbcTemplate.queryForObject(sql, new GradeMapper(), code);
	}
	
	
}
