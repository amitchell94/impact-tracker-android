package com.codingnomads.impacttracker.logic.commitment;

import com.codingnomads.impacttracker.data.CommitmentRepository;

import java.util.List;

public class CommitmentService {

    private CommitmentRepository commitmentRepository;

    public CommitmentService(CommitmentRepository commitmentRepository) {
        this.commitmentRepository = commitmentRepository;
    }

    public List<Commitment> getCommitments(){
        return commitmentRepository.getCommitments();
    }

    public Commitment saveCommitment(Commitment commitment){
        return commitmentRepository.saveCommitment(commitment);
    }
}
