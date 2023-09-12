package org.tgid.testetgid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tgid.testetgid.domain.Cliente;
import org.tgid.testetgid.domain.Empresa;
import org.tgid.testetgid.repositories.ClienteRepository;
import org.tgid.testetgid.repositories.EmpresaRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PopulateService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostConstruct
    public void popularBD() {
    //  inserindo clientes no banco de dados
        Cliente cliente1 = new Cliente("Rafael Camilo","06081983072","rafael@camilo.com");
        Cliente cliente2 = new Cliente("Peterson Camilo","57147804007","peterson@camilo.com");
        Cliente cliente3 = new Cliente("Jaqueline Camilo","94282066016","jaqueline@camilo.com");
        clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3));

        //inserindo empresas no banco de dados
        Empresa empresa1 = new Empresa("ADGyM","86328530000189","admgym@admGym.com");
        Empresa empresa2 = new Empresa("Helper","89852188000100","Helper@Helper.com");
        Empresa empresa3 = new Empresa("e-commerce","00490658000129","Helper@Helper.com");
        empresaRepository.saveAll(List.of(empresa1,empresa2,empresa3));
        //


    }
}
