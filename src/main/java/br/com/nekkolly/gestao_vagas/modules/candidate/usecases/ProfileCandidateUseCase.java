package br.com.nekkolly.gestao_vagas.modules.candidate.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nekkolly.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.nekkolly.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("User not found");
      });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
      .description(candidate.getDescription())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .name(candidate.getName())
      .id(candidate.getId())
      .build();
    
    return candidateDTO;
  }
}
