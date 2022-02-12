import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;

/**
 * ****************************************
 *
 * @param Created    in Samsung Ukraine R&D Center (SURC) under a contract between
 * @param LLC        "Samsung Electronics Ukraine Company" (Kiev Ukraine)
 * @param and        "Samsung Electronics Co", Ltd (Seuol, Republic of Korea)
 * @param Copyright: Samsung Electronics Co, Ltd 2015. All rights reserved.
 * @author Igor Kuzmanenko
 * @version 1.0
 * @fileOverview App.java
 * @date 12.02.2022
 * @modified --/--/----
 */

public class App {
    private static final int MAX_STACK_DEPTH = 50;

    private static final String SCRIPT =
        "function f(e){\n" +
            "    return JSON.stringify(e, f);\n" +
            "};\n" +
            "f(undefined);";

    public static class StackDepthContextFactory extends ContextFactory {
        @Override
        protected Context makeContext() {
            Context ctx = super.makeContext();
            ctx.setOptimizationLevel(-1);
            ctx.setMaximumInterpreterStackDepth(MAX_STACK_DEPTH);
            return ctx;
        }
    }

    public static void main(String[] args) {
        ContextFactory factory = new StackDepthContextFactory();
        Context ctx = factory.enterContext();
        Scriptable scope = ctx.initStandardObjects();
        ctx.evaluateString(scope, SCRIPT, "localscope", 1, null);
    }
}
