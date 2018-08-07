package jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionNameById extends SqlFunction<String> {
    private static final String SQL = "select getNameByld(?)";

    public StoredFunctionNameById(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
