package dev.paie.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.paie.entite.Grade;

public class GradeMapper implements RowMapper<Grade> {

	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Grade grade = new Grade();
		grade.setId(rs.getInt("id"));
		grade.setCode(rs.getString("code"));
		grade.setNbHeuresBase(rs.getBigDecimal("nbheuresbase"));
		grade.setTauxBase(rs.getBigDecimal("tauxbase"));
		return grade;
		
	}
	
}
