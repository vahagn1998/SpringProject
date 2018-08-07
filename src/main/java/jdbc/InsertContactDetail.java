package jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertContactDetail extends BatchSqlUpdate {
    private static final String SQL_INSERT = "insert into contact (phone, person_id) values(:phone, :person_id)";
    private static final int BATCH_SIZE = 10;

    public InsertContactDetail(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("phone", Types.VARCHAR));
        super.declareParameter(new SqlParameter("person_id", Types.INTEGER));
        super.setBatchSize(BATCH_SIZE);
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
