package script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * Created by GongWenBo on 2018/3/2.
 */
public class ScriptDemo {

    public static void main(String[] args)throws Exception{

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
        System.out.println(engine.eval("9*8"));
    }
}
