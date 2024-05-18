package br.com.nekkolly.gestao_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nekkolly.gestao_vagas.exceptions.UserFoundException;
import br.com.nekkolly.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.nekkolly.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;
 
  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
          .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
          .ifPresent((user) -> {
            throw new UserFoundException();
          });

    return this.candidateRepository.save(candidateEntity);
  }

}
