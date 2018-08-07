package jdbc;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;

public class SimpleProc extends StoredProcedure {
    public SimpleProc(DataSource ds) {
        super(ds, "simpleproc");
        declareParameter(new SqlOutParameter("out_param", Types.INTEGER));
        compile();
    }
}
