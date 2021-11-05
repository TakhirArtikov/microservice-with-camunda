package com.intellias.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named
public class StudentApplicationResults implements JavaDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String firstName = (String) delegateExecution.getVariable("firstName");
        String lastName = (String) delegateExecution.getVariable("lastName");

        System.out.println(firstName + " " + lastName);

        String response = restTemplate.exchange("http://localhost:8090/overall",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println(response);

        double ieltsScore = Double.parseDouble(response);

        delegateExecution.setVariable("ieltsScore", ieltsScore);

        System.out.println(ieltsScore);

//        if(ieltsScore >= 6.5){
//
//            String examSubjects = (String) delegateExecution.getVariable("subject");
//
//            System.out.println(examSubjects);
//
//            int examScore = (Integer) delegateExecution.getVariable("score");
//            if(examScore >= 86){
//
//                delegateExecution.setVariable("applicationResult", "Candidate accepted");
//
//            }
//
//            else{ // examScore < 86
//                delegateExecution.setVariable("applicationResult", "Candidate rejected due to low Exam score");
//            }
//        }
//        else{ // ieltsScore < 6.5
//            System.out.println("Candidate rejected due to low IELTS score");
//            delegateExecution.setVariable("applicationResult", "Candidate rejected due to low IELTS score");
//        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
