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

import sg.edu.nus.iss.day24_lecture.models.Book;

@Repository
public class BookRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECT_SQL = "select * from book";
    private final String UPDATE_SQL = "update book set quantity = quantity - 1 where id = ?";
    private final String INSERT_SQL = "insert into book (title, quantity) values (?, ?)";

    // Test
    // public List<Book> findAll() {
    //     return jdbcTemplate.queryForList(SELECT_SQL, Book.class);
    // }

    public List<Book> findAll() {
        return jdbcTemplate.query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Book.class));
    }

    public int update(Integer id) {
        return jdbcTemplate.update(UPDATE_SQL, id);
    }

    public int createBook(Book book) {

        // Create generatedKeyHolder object
        KeyHolder generatKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setString(1, book.getTitle());
                ps.setInt(2, book.getQuantity());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatKeyHolder);

        // Get auto-incremented Id
        Integer returnedId = generatKeyHolder.getKey().intValue();

        return returnedId;
    }
}
