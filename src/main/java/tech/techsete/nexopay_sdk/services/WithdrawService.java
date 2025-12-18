package tech.techsete.nexopay_sdk.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.techsete.nexopay_sdk.models.Page;
import tech.techsete.nexopay_sdk.dtos.requests.WithdrawRequest;
import tech.techsete.nexopay_sdk.dtos.responses.WithdrawResponse;

import java.util.Map;

/**
 * Serviço responsável pela gestão de saques (Withdrawals) na API NexoPay.
 * <p>
 * Esta classe fornece capacidades para solicitar novos saques, consultar saques existentes
 * e realizar buscas filtradas, suportando operações síncronas (bloqueantes) e assíncronas (reativas via WebClient).
 * </p>
 *
 * @author Edson Isaac
 * @since 1.0.0
 */
@Service("NexoPayWithdrawService")
public class WithdrawService {

    private final WebClient webClient;

    /**
     * Construtor para injeção do WebClient configurado para o SDK NexoPay.
     * * @param webClient Instância qualificada do {@link WebClient}.
     */
    public WithdrawService(@Qualifier("NexoPaySDKWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Solicita um novo saque de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param request Dados da solicitação de saque.
     * @return {@link WithdrawResponse} com os detalhes do saque processado.
     */
    public WithdrawResponse create(Map<String, ?> headers, @Valid WithdrawRequest request) {
        return createdAsync(headers, request).block();
    }

    /**
     * Solicita um novo saque de forma assíncrona (Reativa).
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param request Dados da solicitação de saque.
     * @return Um {@link Mono} emitindo o {@link WithdrawResponse}.
     */
    public Mono<WithdrawResponse> createdAsync(Map<String, ?> headers, @Valid WithdrawRequest request) {
        return webClient.post()
                .uri("/withdrawals")
                .attribute("headers", headers)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WithdrawResponse.class);
    }

    /**
     * Consulta um saque específico pelo ID de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param id Identificador único do saque.
     * @return {@link WithdrawResponse} com os detalhes encontrados.
     */
    public WithdrawResponse findById(Map<String, ?> headers, String id) {
        return findByIdAsync(headers, id).block();
    }

    /**
     * Consulta um saque específico pelo ID de forma assíncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param id Identificador único do saque.
     * @return Um {@link Mono} emitindo o {@link WithdrawResponse}.
     */
    public Mono<WithdrawResponse> findByIdAsync(Map<String, ?> headers, String id) {
        return webClient.get()
                .uri(String.format("/withdrawals/%s", id))
                .attribute("headers", headers)
                .retrieve()
                .bodyToMono(WithdrawResponse.class);
    }

    /**
     * Realiza uma busca paginada de saques de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param queryParams Parâmetros de filtro e paginação.
     * @return {@link Page} contendo os resultados da busca.
     */
    public Page<WithdrawResponse> search(Map<String, ?> headers, Map<String, ?> queryParams) {
        return searchAsync(headers, queryParams).block();
    }

    /**
     * Realiza uma busca paginada de saques de forma assíncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param queryParams Parâmetros de filtro e paginação.
     * @return Um {@link Mono} emitindo a {@link Page} de {@link WithdrawResponse}.
     */
    public Mono<Page<WithdrawResponse>> searchAsync(Map<String, ?> headers, Map<String, ?> queryParams) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/withdrawals/search");
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .attribute("headers", headers)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Page<WithdrawResponse>>() {});
    }
}