package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.jnlp.IntegrationService;
import java.io.Serializable;

/**
 * Created by evan.qi on 6/8/2017.
 */
@Component
@ConfigurationProperties(prefix="girl")
public class Girl implements Serializable{


    private static final long serialVersionUID = -6783851564882254919L;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    private Integer grade;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.age+"  "+this.grade;
    }

}
