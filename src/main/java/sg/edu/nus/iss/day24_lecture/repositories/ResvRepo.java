package sg.edu.nus.iss.day24_lecture.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24_lecture.models.Resv;

@Repository
public class ResvRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECT_SQL = "select * from resv";
    private final String INSERT_SQL = "insert into resv (resv_date, full_name) values (?, ?)";

    public List<Resv> findAll() {
        return jdbcTemplate.query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Resv.class));
    }

    public int createResv(Resv resv) {
        // Create generatedKeyHolder object
        KeyHolder generatKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setDate(1, resv.getResvDate());
                ps.setString(2, resv.getFullName());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatKeyHolder);

        // Get auto-incremented Id
        Integer returnedId = generatKeyHolder.getKey().intValue();

        return returnedId;
    }
}
