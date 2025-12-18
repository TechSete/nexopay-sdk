package tech.techsete.nexopay_sdk.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Configuração do cliente HTTP reativo para o SDK NexoPay.
 * <p>
 * Esta classe fornece a configuração necessária para a comunicação com a API da NexoPay,
 * criando e configurando uma instância de {@link WebClient} do Spring WebFlux.
 * </p>
 *
 * O WebClient é configurado com:
 * <ul>
 *   <li>URL base da API NexoPay</li>
 *   <li>Cabeçalhos HTTP padrão para comunicação JSON</li>
 *   <li>Filtro global para interceptar respostas HTTP</li>
 * </ul>
 *
 * <p>
 * Este componente é fundamental para os serviços do SDK que precisam se comunicar
 * com os endpoints da API NexoPay, fornecendo uma interface reativa para
 * requisições HTTP.
 * </p>
 */

@Slf4j
@Configuration("NexoPaySDKWebClientConfiguration")
public class WebClientConfiguration {

    /**
     * Cria e configura um {@link WebClient} para comunicação com a API NexoPay.
     * <p>
     * Este bean cria uma instância de WebClient configurada especificamente para
     * interagir com a API da NexoPay. A configuração inclui:
     * </p>
     * <ul>
     *   <li>URL base da API: https://api.nexopaybr.com</li>
     *   <li>Cabeçalho padrão Accept: application/json</li>
     *   <li>Cabeçalho padrão Content-Type: application/json</li>
     *   <li>Filtro global para interceptar respostas HTTP</li>
     * </ul>
     * <p>
     * O filtro global verifica os códigos de status da resposta HTTP:
     * se o status indicar erro cliente (4xx) ou servidor (5xx),
     * lê o corpo da resposta para log detalhado e lança uma exceção
     * com a mensagem do erro. Caso contrário, permite que a resposta
     * continue normalmente.
     * </p>
     * <p>
     * Isso centraliza o tratamento de erros HTTP no WebClient, evitando
     * a necessidade de múltiplos tratamentos em cada serviço que o utiliza.
     * </p>
     * <p>
     * O WebClient resultante é utilizado pelos serviços do SDK para realizar
     * requisições HTTP reativas à API da NexoPay.
     * </p>
     *
     * @return uma instância configurada de {@link WebClient}
     */
    @Bean(name = "NexoPaySDKWebClient")
    public WebClient nexoPayWebClient() {

        return WebClient.builder()
                .baseUrl("https://api.nexopaybr.com")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .filter(WebClientFilter.addHeadersFromAttribute())
                .filter((request, next) -> next.exchange(request)
                        .flatMap(this::handleErrors)
                )
                .build();
    }

    /**
     * Intercepta a resposta HTTP para tratamento centralizado de erros.
     * <p>
     * Se o código HTTP da resposta indicar erro cliente (4xx) ou servidor (5xx),
     * este método lê o corpo da resposta, registra o erro no log e retorna
     * um {@link Mono} com exceção. Caso contrário, retorna a resposta normalmente.
     * </p>
     *
     * @param response a resposta HTTP recebida
     * @return um {@link Mono} contendo a resposta ou erro tratado
     */
    private Mono<ClientResponse> handleErrors(ClientResponse response) {
        if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> {
                        log.error("Erro HTTP {} na chamada API NexoPay: {}", response.statusCode().value(), errorBody);
                        return Mono.error(new RuntimeException("Erro ao chamar API NexoPay: " + errorBody));
                    });
        }
        return Mono.just(response);
    }
}