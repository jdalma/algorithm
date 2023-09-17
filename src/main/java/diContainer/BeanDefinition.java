package diContainer;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    String id;
    String className;
    List<ConstructorArg> constructorArgs = new ArrayList<>();
    Scope scope = Scope.SINGLETON;
    boolean lazyInit = false;

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }

    public static enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    public static class ConstructorArg {
        boolean isRef;
        Class type;
        Object arg;

        public ConstructorArg(boolean isRef, Class type, Object arg) {
            this.isRef = isRef;
            this.type = type;
            this.arg = arg;
        }
    }
}
