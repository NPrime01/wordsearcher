package com.wordsearcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
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
        return jdbcTemplate.query("SELECT * FROM words WHERE word LIKE '" + alike + "%'",
            new WordRowMapper());
    }

    public List<WordData> getAllPossible(String alike, String mode) {
        List<WordData> results;
        switch(mode) {
            case "Starts With": // starts with case
                results = jdbcTemplate.query("SELECT * FROM words WHERE word LIKE '" + alike + "%'",
                    new WordRowMapper());
                break;
            case "Ends With": // ends with case
                results = jdbcTemplate.query("SELECT * FROM words WHERE word LIKE '%" + alike + "'",
                    new WordRowMapper());
                break;
            case "Contains": // contains case
                results = jdbcTemplate.query("SELECT * FROM words WHERE word LIKE '%" + alike + "%'",
                    new WordRowMapper());
                break;
            default: // mode incorrectly set
                results = Arrays.asList(new WordData("Error with mode change"));
                break;
        }

        return results;
        
    }
}
