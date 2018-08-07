package jdbc;

import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllPersons extends MappingSqlQuery<Person> {
    private static final String SQL_SELECT_ALL_PERSON = "select * from person";

    public SelectAllPersons(DataSource ds) {
        super(ds, SQL_SELECT_ALL_PERSON);
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
