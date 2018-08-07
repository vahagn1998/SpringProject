package jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdatePerson extends SqlUpdate {
    private static final String SQL_UPDATE = "update person set name=:name, lastName=:lastName, birthday=:birthday " +
            "where id = :person.id";

    public UpdatePerson(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthday", Types.DATE));
        super.declareParameter(new SqlParameter("person.id", Types.INTEGER));
    }
}
