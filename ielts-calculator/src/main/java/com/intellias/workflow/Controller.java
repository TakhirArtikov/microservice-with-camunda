package com.intellias.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    IeltsCalculator ieltsCalculator;

    @GetMapping("/overall")
    public Double getOverallScore(){
        return ieltsCalculator.getOverallScore();
    }

}
