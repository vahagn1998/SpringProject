package jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectPersonsByFirstName extends MappingSqlQuery<Person> {
    private static final String QUERY = "select * from person where name = :name";

    public SelectPersonsByFirstName(DataSource ds) {
        super(ds, QUERY);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
    }

    @Override
    protected Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setLastName(rs.getString("lastName"));
        person.setBirthday(rs.getDate("birthday").toLocalDate());
        return person;
    }
}
