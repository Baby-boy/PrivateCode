package com.aitou.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoProcessor demoProcessor;  //业务逻辑处理Processor

//    @Autowired
//    private CompositeItemProcessor compositeItemProcessor;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MyBatisBatchItemWriter aWriter;

    @Autowired
    private MyBatisPagingItemReader itemReader;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

//    @Autowired
//    private SecondWriter secondWriter;

    public void calculateFee(Date date) {
        //Set Reader QueryId
        itemReader.setQueryId("org.uugu.core.dao.financing.BDao.findBPaginated");
        //创建findBPaginated这个xml对应ID的sql中使用到的参数
        Map<String, Object> params = new HashMap();
        params.put("verifyStatus", 0);
        params.put("status", "1,2");
        itemReader.setParameterValues(params);

        //Set Writer StatementId
        aWriter.setStatementId("org.uugu.core.dao.daysettle.ADao.saveA");

        //Create Parameter for Processor
        Map<String, Object> param =new HashMap();
        param.put("date", date);
      //  daySettleInfoProcessor.setParameterValues(param);
        //使用符合Writer，实现多Writer，可像多个数据库中写入数据，当然第二个Writer的写入操作要自己代码实现，注意同事务中MyBatis的ExcuteType不可改变
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        List delegates = new ArrayList();
        delegates.add(aWriter);
       // delegates.add(secondWriter);
        compositeItemWriter.setDelegates(delegates);

        //Build Step
        Step step = stepBuilderFactory.get("calculateDaySettleInfoStep1").<B, A>chunk(1000)  //1000次处理提交一次事务
                .reader(itemReader)
                .processor(demoProcessor)
                .writer(compositeItemWriter)
//                .taskExecutor(taskExecutor)            //If you need can be used.
                .faultTolerant()
                .skipLimit(10)                           //可以异常跳过10次，多了就任务失败
                .skip(NullPointerException.class)        //空指针异常可以跳过，继续执行
                .listener(new DemoSkipListener())
                .build();

        //Build Job
        Job job = jobBuilderFactory.get("calculateDaySettleInfoJob1").incrementer(new RunIdIncrementer()).start(step).build();
        JobParameters parameters = new JobParametersBuilder()
                .addString("runDay", DateUtil.formatDate(date, "yyyy-MM-dd"))  //以日期为参数，可保证一天只能执行一次
                .toJobParameters();
        
        try {
            //Start Job
            JobExecution result = jobLauncher.run(job, parameters);
          //  Dateutil
            System.out.println("===" + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}