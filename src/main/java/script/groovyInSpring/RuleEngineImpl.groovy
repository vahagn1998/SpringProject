package script.groovyInSpring

import org.springframework.stereotype.Component

@Component("ruleEngine")
class RuleEngineImpl implements RuleEngine {
    @Override
    void run(Rule rule, Object object) {
        def exit = false

        rule.parameters.each {ArrayList params ->
            def paramIndex = 0
            def success = true

            if(!exit) {
                rule.conditions.each {
                    success = success && it(object, params[paramIndex])
                    paramIndex++
                }

                if(success && !exit) {
                    rule.actions.each {
                        it(object, params[paramIndex])
                        paramIndex++
                    }
                    if(rule.singlehit) {
                        exit = true
                    }
                }
            }
        }
    }
}
