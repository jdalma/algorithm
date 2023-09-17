package diContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeansFactory {

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) {
        for (BeanDefinition beanDefinition: beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.id, beanDefinition);
        }
        for (BeanDefinition beanDefinition: beanDefinitionList) {
            if (!beanDefinition.lazyInit && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        }
    }

    protected Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.id)) {
            return singletonObjects.get(beanDefinition.id);
        }
        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.className);
            List<BeanDefinition.ConstructorArg> args = beanDefinition.constructorArgs;
            if (args.isEmpty()) {
                bean = beanClass.getDeclaredConstructor().newInstance();
            } else {
                Class[] argClasses = new Class[args.size()];
                Object[] argObjects = new Object[args.size()];
                for (int i = 0 ; i < args.size() ; ++i) {
                    BeanDefinition.ConstructorArg arg = args.get(i);
                    if (!arg.isRef) {
                        argClasses[i] = arg.type;
                        argObjects[i] = arg.arg;
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.arg);
                        if (refBeanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.arg);
                        }
                        argClasses[i] = Class.forName(refBeanDefinition.className);
                        argObjects[i] = createBean(refBeanDefinition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException | NoSuchBeanDefinitionException e) {
            throw new RuntimeException(e);
        }
        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.id, bean);
            return singletonObjects.get(beanDefinition.id);
        }
        return bean;
    }
}
