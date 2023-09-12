package org.tgid.testetgid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tgid.testetgid.domain.Cliente;
import org.tgid.testetgid.domain.Empresa;

import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCpf(String cpf);
}
