package com.zy.learning.strategypattern.processor;

import com.zy.learning.strategypattern.ClassScaner;
import com.zy.learning.strategypattern.annotation.HandlerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class HandlerProcessor implements BeanFactoryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(HandlerProcessor.class);
    private static final String HANDLE_PACKAGE = "com.zy.learning.strategypattern.handler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public static void main(String[] args) {
        Map<String, Class> handleMap = new ConcurrentHashMap<>(16);
        Set<Class> classSet = ClassScaner.scan(HANDLE_PACKAGE, HandlerType.class);
        log.info("class info :{}", classSet);
        classSet.stream().forEach(clazz -> {
            String value = ((HandlerType) clazz.getAnnotation(HandlerType.class)).value();
            handleMap.put(value, clazz);
        });
        log.info("handleMap :{}", handleMap);
    }
}
