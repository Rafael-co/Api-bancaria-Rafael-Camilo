package org.tgid.testetgid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tgid.testetgid.domain.Empresa;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findByCnpj(String cnpj);
}
