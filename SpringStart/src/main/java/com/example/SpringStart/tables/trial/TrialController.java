package com.example.SpringStart.tables.trial;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trial")
public class TrialController {

    private final TrialService trialService;

    @PostMapping("/add")
    private void addTrial(@RequestBody Trial trial) {
        trialService.addTrial(trial);
    }

    @GetMapping("/get")
    private List<Trial> getTrial() {
        return trialService.getTrials();
    }

}
