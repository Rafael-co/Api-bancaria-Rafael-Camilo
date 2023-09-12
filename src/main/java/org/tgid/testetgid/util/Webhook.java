package org.tgid.testetgid.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tgid.testetgid.domain.enums.TipoTransacao;

@Service
public class Webhook {

    @Autowired
    private RestTemplate restTemplate;


    public String enviarWebhook(Transacao transacao)  {
        // URL do webhook
        String webhookUrl = "https://webhook.site/9604e09e-af58-450e-a452-3ca7274b44ee";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "erro ao criar o Json do objeto";
        try {
            json = objectMapper.writeValueAsString(transacao);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("erro ao criar o Json do objeto");
        }

        // Conteúdo do corpo da requisição
        String corpoRequisicao = json;

        // Configurar os cabeçalhos da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        // Configurar a entidade da requisição com os cabeçalhos e o corpo
        HttpEntity<String> requestEntity = new HttpEntity<>(corpoRequisicao, headers);

        // Enviar a requisição POST
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Verificar o código de resposta (opcional)
        HttpStatus statusCode = responseEntity.getStatusCode();
        return "Código de resposta: " + statusCode;
    }
}
