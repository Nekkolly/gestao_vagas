package br.com.nekkolly.gestao_vagas.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nekkolly.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.nekkolly.gestao_vagas.modules.company.usecases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
  
  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/company")
  public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    return this.authCompanyUseCase.execute(authCompanyDTO);
  }
}
