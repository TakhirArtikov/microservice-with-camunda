package com.intellias.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class IeltsCalculator implements JavaDelegate {

    public Double overallScore;

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("execute");

        String listeningS = "0.0";
        listeningS = (String) delegateExecution.getVariable("listening");

        double listening = Double.parseDouble(listeningS);

        String readingS = "0.0";
        readingS = (String) delegateExecution.getVariable("reading");

        double reading = Double.parseDouble(readingS);

        String writingS = "0.0";
        writingS = (String) delegateExecution.getVariable("writing");

        double writing = Double.parseDouble(writingS);

        String speakingS = "0.0";
        speakingS = (String) delegateExecution.getVariable("speaking");

        double speaking = Double.parseDouble(speakingS);

        double overall = (listening + reading + writing + speaking)/4;
        if(overall - (int) overall == 0.25 || overall - (int) overall == 0.75)
            overall += 0.25;

        delegateExecution.setVariable("overall", overall);

        System.out.println(overall);
        this.setOverallScore(overall);

    }
}
