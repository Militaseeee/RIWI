package com.mobilefix_v2.camila_acosta_mobilefix_v2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Auditoría y trazabilidad:

// Registrar y sigue los cambios que ocurren dentro de la aplicación: quién hizo qué, cuándo y cómo.
// Esto permite tener control y transparencia sobre los datos

@Configuration
@EnableJpaAuditing // Esta anotación activa todo
public class JpaAuditingConfig {



}
