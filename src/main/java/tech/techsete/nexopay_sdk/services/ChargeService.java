package tech.techsete.nexopay_sdk.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.techsete.nexopay_sdk.models.Page;
import tech.techsete.nexopay_sdk.dtos.requests.ChargeRequest;
import tech.techsete.nexopay_sdk.dtos.responses.ChargeResponse;

import java.util.Map;

/**
 * Serviço responsável pela gestão de cobranças (Charges) na API NexoPay.
 * <p>
 * Esta classe fornece métodos para criação, consulta e busca paginada de cobranças,
 * suportando operações síncronas (bloqueantes) e assíncronas (reativas via WebClient).
 * </p>
 * @author EdsonI saac
 * @since 1.0.0
 */
@Service("NexoPayChargeService")
public class ChargeService {

    private final WebClient webClient;

    /**
     * Construtor para injeção do WebClient configurado para o SDK NexoPay.
     * * @param webClient Instância qualificada do {@link WebClient} para chamadas externas.
     */
    public ChargeService(@Qualifier("NexoPaySDKWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Cria uma nova cobrança de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados para a requisição.
     * @param request Objeto contendo os dados da cobrança a ser criada.
     * @return {@link ChargeResponse} contendo os detalhes da cobrança criada.
     * @throws RuntimeException se ocorrer um erro durante a chamada à API.
     */
    public ChargeResponse create(Map<String, ?> headers,
                                 @Valid ChargeRequest request) {
        return createdAsync(headers, request).block();
    }

    /**
     * Cria uma nova cobrança de forma assíncrona (Reativa).
     * * @param headers Mapa de cabeçalhos HTTP customizados para a requisição.
     * @param request Objeto contendo os dados da cobrança a ser criada.
     * @return Um {@link Mono} emitindo o {@link ChargeResponse} após a conclusão.
     */
    public Mono<ChargeResponse> createdAsync(Map<String, ?> headers,
                                             @Valid ChargeRequest request) {
        return webClient.post()
                .uri("/charges")
                .attribute("headers", headers)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChargeResponse.class);
    }

    /**
     * Recupera os detalhes de uma cobrança específica pelo seu identificador de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param id Identificador único da cobrança.
     * @return {@link ChargeResponse} com os dados encontrados.
     */
    public ChargeResponse findById(Map<String, ?> headers, String id) {
        return findByIdAsync(headers, id).block();
    }

    /**
     * Recupera os detalhes de uma cobrança específica pelo seu identificador de forma assíncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param id Identificador único da cobrança.
     * @return Um {@link Mono} emitindo o {@link ChargeResponse}.
     */
    public Mono<ChargeResponse> findByIdAsync(Map<String, ?> headers, String id) {
        return webClient.get()
                .uri(String.format("/charges/%s", id))
                .attribute("headers", headers)
                .retrieve()
                .bodyToMono(ChargeResponse.class);
    }

    /**
     * Realiza uma busca paginada de cobranças com filtros de forma síncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param queryParams Mapa de parâmetros de consulta (ex: status, data, paginação).
     * @return {@link Page} contendo a lista paginada de {@link ChargeResponse}.
     */
    public Page<ChargeResponse> search(Map<String, ?> headers, Map<String, ?> queryParams) {
        return searchAsync(headers, queryParams).block();
    }

    /**
     * Realiza uma busca paginada de cobranças com filtros de forma assíncrona.
     * * @param headers Mapa de cabeçalhos HTTP customizados.
     * @param queryParams Mapa de parâmetros de consulta para filtrar a busca.
     * @return Um {@link Mono} emitindo o {@link Page} de resultados.
     */
    public Mono<Page<ChargeResponse>> searchAsync(Map<String, ?> headers, Map<String, ?> queryParams) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/charges/search");
                    if (queryParams != null) {
                        queryParams.forEach(uriBuilder::queryParam);
                    }
                    return uriBuilder.build();
                })
                .attribute("headers", headers)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Page<ChargeResponse>>() {});
    }
}