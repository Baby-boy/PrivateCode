package com.aitou.mes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aitou.db.CustomerCreditRowMapper;

public class MesMain {

	public static void main(String[] args) {
		
		        ClassPathXmlApplicationContext c = 
		                 new ClassPathXmlApplicationContext("classpath:batch.xml");
		        SimpleJobLauncher launcher = new SimpleJobLauncher();
		       // Map<K, V> map=new HashMap<K, V>
		        launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
		        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		        try {
		             JobExecution result = launcher.run((Job) c.getBean("messageJob"), new JobParameters());
		        } catch (Exception e) {
		        e.printStackTrace();
		        }
		        
//		      //For simplicity sake, assume a dataSource has already been obtained
//		        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		        List customerCredits = jdbcTemplate.query("SELECT ID, NAME, CREDIT from CUSTOMER",
//		        new CustomerCreditRowMapper());

	}

}
