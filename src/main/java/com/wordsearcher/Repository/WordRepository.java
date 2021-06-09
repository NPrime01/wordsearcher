package com.wordsearcher.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.wordsearcher.Model.WordData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    Maps SQL query results to WordData objects for use sending results to the user
     */
    class WordRowMapper implements RowMapper<WordData> {
		@Override
		public WordData mapRow(ResultSet rs, int rowNum) throws SQLException {
			WordData word = new WordData(rs.getString("word"));
			return word;
		}
	}

	/*
	Gets query results and returns a list of strings containing all results of the query
	 */
    public List<String> getAllPossible(String alike, String mode) {

        // case where the user querys the entire database
        if (alike.equals("")) {
            return jdbcTemplate.query("SELECT * FROM words", new WordRowMapper()).stream()
                .map(word -> word.getWord())
                .collect(Collectors.toList());
        }

        // case where user makes a selective query
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
        return results.stream().map(word -> word.getWord()).collect(Collectors.toList());
        
    }
}
