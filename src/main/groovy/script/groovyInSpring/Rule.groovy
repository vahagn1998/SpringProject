package script.groovyInSpring

class Rule {
    private boolean singlehit = true
    private conditions = new ArrayList()
    private actions = new ArrayList()
    private parameters = new ArrayList()

    boolean getSinglehit() {
        return singlehit
    }

    void setSinglehit(boolean singlehit) {
        this.singlehit = singlehit
    }

    def getConditions() {
        return conditions
    }

    void setConditions(conditions) {
        this.conditions = conditions
    }

    def getActions() {
        return actions
    }

    void setActions(actions) {
        this.actions = actions
    }

    def getParameters() {
        return parameters
    }

    void setParameters(parameters) {
        this.parameters = parameters
    }
}
