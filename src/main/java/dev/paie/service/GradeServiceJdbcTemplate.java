package dev.paie.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

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
		String sql = "INSERT INTO GRADE (code,nbHeuresBase,tauxBase) VALUES (?,?,?)";
		this.jdbcTemplate.update(sql,
				nouveauGrade.getCode(),
				nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase()
		);
	}
	
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE GRADE SET code=?,nbHeuresBase=?,tauxBase=? WHERE id=?";
		this.jdbcTemplate.update(sql,  
				grade.getCode(),
				grade.getNbHeuresBase(),
				grade.getTauxBase(),
				grade.getId()
		);
	}

	public List<Grade> lister() {
		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, (rs, rowNum) -> {
			Grade grade = new Grade();
			grade.setCode(rs.getString("code"));
			grade.setNbHeuresBase(rs.getBigDecimal("nbheuresbase"));
			grade.setTauxBase(rs.getBigDecimal("tauxbase"));
			return grade;	
		});
	}
	
}
