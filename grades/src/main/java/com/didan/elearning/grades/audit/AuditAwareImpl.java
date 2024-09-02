package com.didan.elearning.grades.audit;

import jakarta.persistence.Column;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAware")
public class AuditAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.empty();
  }
}
