package com.wordsearcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class WordRowMapper implements RowMapper<WordData> {
		@Override
		public WordData mapRow(ResultSet rs, int rowNum) throws SQLException {
			WordData word = new WordData(rs.getString("word"));
			return word;
		}
	}

    public List<WordData> getAllPossible(String alike) {
        return jdbcTemplate.query("SELECT * FROM words WHERE word LIKE '" + alike + "%'", new WordRowMapper());
    }
}
