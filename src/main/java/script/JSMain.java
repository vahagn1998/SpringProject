package script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSMain {
    public static void main(String[] args) {
        ScriptEngineManager mng = new ScriptEngineManager();
        ScriptEngine jsEngine = mng.getEngineByName("JavaScript");

        try {
            jsEngine.eval("print(5+'4'-2)");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
