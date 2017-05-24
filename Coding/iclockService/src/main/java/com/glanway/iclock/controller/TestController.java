/**
 * Copyright (c) 2017 glanway.com, Inc. All rights reserved.
 *
 * GLANWAY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.glanway.iclock.controller;



import com.glanway.iclock.common.APIRequestWrapper;
import com.glanway.iclock.common.APIResponseWrapper;
import com.glanway.iclock.util.MapBuilder;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车品牌/车系/车型/年款/排量/汽车级联控制器.
 * 
 * @author yangchanghe
 * @version 1.0-2017.02.23
 * @since 1.0-2017.02.23
 */
@Controller
@RequestMapping("api/test")
public class TestController {

    public class Education{

        public Education(){}
        public Education(int id, String name){
            this.setId(id);
            this.setName(name);
        }



        int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        String name;

    }
    
    public class TestSupModel{
    	public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        int level;
    }

    public class TestModel extends TestSupModel{
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;

        public TestModel getChild() {
            return child;
        }

        public void setChild(TestModel child) {
            this.child = child;
        }

        TestModel child;

        public ArrayList<Education> getEducationList() {
            return EducationList;
        }

        public void setEducationList(ArrayList<Education> educationList) {
            EducationList = educationList;
        }

        ArrayList<Education> EducationList;


    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveModel(@RequestBody Map<String, Object> params) {
        APIRequestWrapper requestWrapper = APIRequestWrapper.CreateInstance(params);

        TestModel testModel = new TestModel();
        testModel.age = requestWrapper.getIntegerParam("age");
        testModel.name = requestWrapper.getStringParam("name");


        //TODO save it into DB.
        System.out.print(testModel);

        return APIResponseWrapper.BuildResponse(true);
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getModel() {
        TestModel testModel = new TestModel();
        testModel.age = 99;
        testModel.name ="Leo Lin";
        testModel.level=100;
        testModel.child =  new TestModel();
        testModel.child.age = 10;
        testModel.child.name = "Leo Lin Jr.";

        testModel.setEducationList(new ArrayList<Education>());

        testModel.getEducationList().add(new Education(1,"大学"));
        testModel.getEducationList().add(new Education(2,"中学"));
        testModel.getEducationList().add(new Education(3,"小学"));
        testModel.getEducationList().add(new Education(4,"家里蹲"));

        // model.put("carHotBrand", Jacksons.serialize(carBrand));
        APIResponseWrapper responseWrapper = APIResponseWrapper.CreateInstance(true,"");

        responseWrapper.putData(MapBuilder.fromObject(testModel, new String[]{"age", "name","level"}));

        Map<String, Object> childMap = new HashMap<String, Object>();
        childMap.put("child",testModel.getChild());
        List<Education> educationList =testModel.getEducationList();
        childMap.put("education", MapBuilder.fromList(educationList, new String[]{"id", "name"}));

        responseWrapper.putData(childMap);

        return responseWrapper.getResponse();
    }
}
