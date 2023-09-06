package com.example.SpringStart.tables.trial;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrialService {

    private final TrialRepository trialRepository;

    public void addTrial(Trial trial) {
        trialRepository.save(trial);
    }

    public List<Trial> getTrials() {
        return trialRepository.findAll();
    }
}
