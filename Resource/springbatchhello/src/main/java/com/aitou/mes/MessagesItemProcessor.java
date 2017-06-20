package com.aitou.mes;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
//@Component("messageProcessor")
public class MessagesItemProcessor implements ItemProcessor<User, User> {
 
    public User process(User user) throws Exception {
//        user m = new Message();
//        m.setContent("Hello " + user.getName()
//                + ",please pay promptly at the end of this month.");
//        return m;
    	user.setName(user.getName()+"AA");
    	System.out.println("拿到user了吗:"+user);
    	return user;
    }
 
}