package sg.edu.nus.iss.day24_lecture.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24_lecture.models.ResvDetails;

@Repository
public class ResvDetailsRepo {
    
    @Autowired 
    JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "insert into resv_details (book_id, resv_id) values (?, ?)";

    public int createResvDetails(ResvDetails resvDetails) {
        // Create generatedKeyHolder object
        KeyHolder generatKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setInt(1, resvDetails.getBookId());
                ps.setInt(2, resvDetails.getResvId());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatKeyHolder);

        // Get auto-incremented Id
        Integer returnedId = generatKeyHolder.getKey().intValue();

        return returnedId;
    }

}
