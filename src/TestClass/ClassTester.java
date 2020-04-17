package TestClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassTester {
    private static List<Method> methodList = new ArrayList<Method>();

    public static void start(Class testClass) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        runTest(testClass);
    }

    public static void  start (String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        runTest(Class.forName(className));
    }

    private static void runTest(Class testClass) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Method beforeSuiteMethod = null;
                //testClas.getDeclaredAnnotationsByType(Class.forName("BeforeSuite"));
        Method afterSuiteMethod = null;
                //testClas.getDeclaredAnnotationsByType(Class.forName("AfterSuite"));
        for (Method currentMethod : testClass.getDeclaredMethods()){
            //System.out.println(currentMethod.getName());
            for (Annotation currentAnnotation : currentMethod.getDeclaredAnnotations()) {
                if (currentAnnotation.annotationType() == BeforeSuite.class) {
                    if (beforeSuiteMethod == null) {
                        beforeSuiteMethod = currentMethod;
                        continue;
                    } else throw new RuntimeException();
                }
                if (currentAnnotation.annotationType() == AfterSuite.class) {
                    if (afterSuiteMethod == null) {
                        afterSuiteMethod = currentMethod;
                        continue;
                    } else throw new RuntimeException();
                }
                if (currentAnnotation.annotationType() == Test.class) {
                    addTestMethodtoList(currentMethod);
                    continue;
                }
            }
        }

        Object [] args = new Object[]{1,2};
        Object testObject = testClass.getDeclaredConstructor().newInstance();
        if (beforeSuiteMethod != null) { beforeSuiteMethod.invoke(testObject);}
        for (int i = 0; i < methodList.size(); i++) {
            methodList.get(i).invoke(testObject);
        }
        if (beforeSuiteMethod != null) { afterSuiteMethod.invoke(testObject);}
    }

    private static void addTestMethodtoList (Method newMethod){
        //List<Method> tempMethodList = new ArrayList<Method>();
        int newMethodPriority = newMethod.getDeclaredAnnotation(Test.class).priority();
        int listMethodPriority = -1;
        for (int i = 0; i < methodList.size(); i++) {
            listMethodPriority = methodList.get(i).getDeclaredAnnotation(Test.class).priority();
            if (newMethodPriority > listMethodPriority){
                methodList.add(newMethod);
                return;
            } else if (newMethodPriority <= listMethodPriority) {
                methodList.add(i, newMethod);
                return;
            }
        }
        methodList.add(newMethod);
    }

    private static void runTestMethods(Object testObject, Method currentMethod, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

    }

}
