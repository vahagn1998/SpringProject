package script.groovyInSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    final ApplicationContext context;

    private final RuleFactory ruleFactory;

    private final RuleEngine ruleEngine;

    @Autowired
    public ContactServiceImpl(ApplicationContext context, RuleFactory ruleFactory, RuleEngine ruleEngine) {
        this.context = context;
        this.ruleFactory = ruleFactory;
        this.ruleEngine = ruleEngine;
    }

    @Override
    public void applyRule(Contact contact) {
        Rule ageRule = ruleFactory.getAgeCategoryRule();
        ruleEngine.run(ageRule, contact);
    }
}
