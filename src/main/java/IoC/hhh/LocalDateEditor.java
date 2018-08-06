package IoC.hhh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class LocalDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String s) throws IllegalArgumentException {
        try {
            setValue(new DateFormatter("dd/MM/yyyy").parse(s, Locale.ENGLISH).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
