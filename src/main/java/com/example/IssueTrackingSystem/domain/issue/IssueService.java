package com.example.IssueTrackingSystem.domain.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Beanの登録
@Service
@RequiredArgsConstructor
public class IssueService{

    private final IssueRepository issueRepository;
    public List<IssueEntity> findAll(){
        return issueRepository.findAll();
    }

    @Transactional
    public void create(String summary, String description) {
        issueRepository.insert(summary, description);
    }

    public IssueEntity findById(long issueId) {
        return issueRepository.findById(issueId);
    }
}
