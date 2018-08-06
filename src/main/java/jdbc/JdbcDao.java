package jdbc;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Service
public class JdbcDao implements PersonDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        MySqlErrorCodesTranslator mySqlErrorCodesTranslator = new MySqlErrorCodesTranslator();
        jdbcTemplate.setExceptionTranslator(mySqlErrorCodesTranslator);

        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void init() {
        if(dataSource == null) {
            throw new BeanCreationException("Must set data sourse on PersonDao");
        }
        if(jdbcTemplate == null) {
            throw new BeanCreationException("Null jdbc template on PersonDao");
        }
    }

    @Override
    public String findLastNameById(Long id) {
        String query = "select lastName from person where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, String.class);
    }
}
