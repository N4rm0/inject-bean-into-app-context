package com.mvong.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
public class MyMain {


    @Bean(name = "myBeanInsideContext")
    public MyBean myBeanInsideContext(){
        MyBean insideBean = new MyBean();
        insideBean.setMyString("this bean is defined inside the application context");
        return insideBean;
    }

    public static void main(String[] args) {
        MyBean outsideBean = new MyBean();
        outsideBean.setMyString("this bean is defined outside the application context");

        GenericApplicationContext context = new AnnotationConfigApplicationContext(MyMain.class);

        MyBean myBean = context.getBean("myBeanInsideContext", MyBean.class);

        context.getDefaultListableBeanFactory().registerSingleton("outsideBean",outsideBean);

        MyBean myOutsideBean = context.getBean("outsideBean", MyBean.class);

        MyBean myBeanInsideContext = context.getBean("myBeanInsideContext", MyBean.class);

        assert myBeanInsideContext.getMyString().equals(myBean.getMyString());
        assert myOutsideBean.getMyString().equals(outsideBean.getMyString());


    }
}
