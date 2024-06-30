package br.com.nekkolly.gestao_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nekkolly.gestao_vagas.exceptions.UserFoundException;
import br.com.nekkolly.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.nekkolly.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;
 
  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
          .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
          .ifPresent((user) -> {
            throw new UserFoundException();
          });
    var password = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }

}
