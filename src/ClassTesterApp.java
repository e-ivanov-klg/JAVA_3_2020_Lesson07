import TestClass.AfterSuite;
import TestClass.BeforeSuite;
import TestClass.ClassTester;
import TestClass.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ClassTesterApp {
    private static Calculator testCalc;

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ClassTester.start(ClassTesterApp.class);
    }

    @BeforeSuite
    public static void before(){
        testCalc = new Calculator();
    }

    @Test(priority = 4)
    public static void testCaclSum () {
        System.out.println(testCalc.sum(4, 5) == 9);
    }

    @Test(priority = 3)
    public static void testCalcMulti () {
        System.out.println(testCalc.multiply(2, 3) == 5);
    }

    @AfterSuite
    public static void after () {
        System.out.println("Test complite !");
    }
}
