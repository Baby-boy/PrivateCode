package com.aitou.mybatis;

import java.util.Date;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DemoProcessor implements ItemProcessor<B, A> {
 
    private Map<String, Object> parameterValues;
 
    public A process(B item) throws Exception {
        A a = new A();
        a.setDay((Date) parameterValues.get("date"));
        return a;
    }
 
    public Map<String, Object> getParameterValues() {
        return parameterValues;
    }
 
    public void setParameterValues(Map<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }
}