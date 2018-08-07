package jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

@Repository("personDao")
public class JdbcDao implements PersonDao {
    private Log log = LogFactory.getLog(JdbcDao.class);
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SelectAllPersons selectAllPersons;
    private SelectPersonsByFirstName selectPersonsByFirstName;
    private UpdatePerson updatePerson;
    private InsertPerson insertPerson;
    private InsertContactDetail insertContactDetail;
    private StoredFunctionNameById storedFunctionNameById;
    private SimpleProc simpleProc;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        selectAllPersons = new SelectAllPersons(dataSource);
        selectPersonsByFirstName = new SelectPersonsByFirstName(dataSource);
        updatePerson = new UpdatePerson(dataSource);
        insertPerson = new InsertPerson(dataSource);
        insertContactDetail = new InsertContactDetail(dataSource);
        storedFunctionNameById = new StoredFunctionNameById(dataSource);
        simpleProc = new SimpleProc(dataSource);
//        MySqlErrorCodesTranslator mySqlErrorCodesTranslator = new MySqlErrorCodesTranslator();
//        jdbcTemplate.setExceptionTranslator(mySqlErrorCodesTranslator);

        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @PostConstruct
    private void init() {
        if(dataSource == null) {
            throw new BeanCreationException("Must set data source on PersonDao");
        }
        if(jdbcTemplate == null) {
            throw new BeanCreationException("Null jdbc template on PersonDao");
        }
    }

    @Override
    public String findLastNameById(Long id) {
        String query = "select lastName from person where id = :personId";
        Map<String, Object> params = new HashMap<>();
        params.put("personId", id);
        return jdbcTemplate.queryForObject(query, params, String.class);
    }

    @Override
    public String findNameById(Long id) {
        return storedFunctionNameById.execute(id).get(0);
    }

    @Override
    public List<Person> findAll() {
        return selectAllPersons.execute();
    }

    @Override
    public List<Person> findAllWithDetail() {
        String query = "select * from person "
                + "left join contact on contact.person_id = person.id";
        return jdbcTemplate.query(query, new PersonWithDetailExtractor());
    }

    @Override
    public List<Person> findPersonsByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return selectPersonsByFirstName.executeByNamedParam(params);
    }

//    @Override
//    public void update(Person person) {
//        Map<String, Object> paramsContact = new HashMap<>();
//        Map<String, Object> params = new HashMap<>();
//        params.put("person.id", person.getId());
//        params.put("name", person.getName());
//        params.put("lastName", person.getLastName());
//        params.put("birthday", person.getBirthday());
//        Set<Contact> contacts = person.getContacts();
//
//        @SuppressWarnings("unchecked")
//        Map<String, Object>[] paramsContactArray = (Map<String, Object>[]) new Map[contacts.size()];
//
//        int i = 0;
//        for (Contact contact : contacts) {
//            paramsContact.put("contact.id", contact.getId());
//            paramsContact.put("phone", contact.getPhone());
//            paramsContact.put("person_id", contact.getPersonId());
//            paramsContactArray[i++] = new HashMap<>(paramsContact);
//            paramsContact.clear();
//        }
//        String queryContract = "update contact set phone=:phone, person_id=:person_id " +
//                "where id = :contact.id";
//        jdbcTemplate.batchUpdate(queryContract, paramsContactArray);
//
//        String query = "update person set name=:name, lastName=:lastName, birthday=:birthday " +
////                "where id = :person.id";
//        jdbcTemplate.update(query, params);
//    }

    @Override
    public void update(Person person) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", person.getName());
        params.put("lastName", person.getLastName());
        params.put("birthday", person.getBirthday());
        params.put("person.id", person.getId());
        updatePerson.updateByNamedParam(params);
        log.info(person.getId());
    }

    @Override
    public void insert(Person person) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", person.getName());
        params.put("lastName", person.getLastName());
        params.put("birthday", person.getBirthday());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertPerson.updateByNamedParam(params, keyHolder);
        person.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        log.info(person.getId());
    }

    @Override
    public void insertWithDetail(Person person) {
        insert(person);
        Set<Contact> contacts = person.getContacts();
        Map<String, Object> params = new HashMap<>();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(contacts != null) {
            for (Contact contact : contacts) {
                params.put("person_id", person.getId());
                params.put("phone", contact.getPhone());
                insertContactDetail.updateByNamedParam(params, keyHolder);
                contact.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
                params.clear();
            }
        }
        insertContactDetail.flush();
    }

    public int countOfPersons() {
        Map<String, Object> execute = simpleProc.execute();
        return (int) execute.get("out_param");
    }
}
