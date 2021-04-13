package com.lagou.dynamicproxy;

public class proxyTest {
    public static void main(String[] args) {

        System.out.println("不使用代理类，调用doSomething");
        Person person = new Bob();
        person.doSomething();

        System.out.println("--------------------------");

        System.out.println("使用代理类，调用doSomething");
        Person proxy = (Person) new JDKDynamicProxy(new Bob()).getTarget();
        proxy.doSomething();


        System.out.println("--------------------------");

        System.out.println("使用工厂代理类，调用doSomething");

        Person personProxy = (Person) ProxyFactory.getInstance().getJDKProxy(person);
        personProxy.doSomething();
        Person mary = new Mary();
        Person maryProxy = (Person) ProxyFactory.getInstance().getJDKProxy(mary);
        maryProxy.doSomething();

    }
}
