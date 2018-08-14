package transaction;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
//    private ContactRepository contactRepository;
//    private TransactionTemplate transactionTemplate;

    @PersistenceContext (unitName="emfA")
    private EntityManager emA;

    @PersistenceContext(unitName="emfB")
    private EntityManager emB;

//    @Autowired
//    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
//        this.transactionTemplate = transactionTemplate;
//    }

//    @Autowired
//    public void setContactRepository(ContactRepository contactRepository) {
//        this.contactRepository = contactRepository;
//    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
//        return Lists.newArrayList(contactRepository.findAll());
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
//        return contactRepository.findById(id).orElse(null);
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        Contact contactB = new Contact();
        contactB.setFirsName(contact.getFirsName());
        contactB.setLastName(contact.getLastName());
        if (contact.getId() == null) {
            emA.persist(contact);
            emB.persist(contactB);
//             throw new JpaSystemException(new PersistenceException());
        }
        else{
            emA.merge(contact);
            emB.merge(contact);
        }
        return contact;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public long countAll() {
//        return transactionTemplate.execute(status -> em.createNamedQuery("Contact.countAll", Long.class).getSingleResult());
        return 0;
    }
}
