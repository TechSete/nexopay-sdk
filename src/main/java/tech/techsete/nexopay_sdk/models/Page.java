package tech.techsete.nexopay_sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Representa uma página de resultados retornada pela API NexoPay.
 * <p>
 * Esta classe é um container genérico que encapsula a lista de elementos (conteúdo)
 * e os metadados de paginação, permitindo a navegação entre grandes volumes de dados.
 * </p>
 *
 * @param <T> O tipo de objeto contido na lista de resultados (ex: ChargeResponse, WithdrawResponse).
 * @author Edson Isaac
 * @since 1.0.0
 */
@Data
@Builder
public class Page<T> implements Serializable {

    /**
     * Lista contendo os elementos da página atual.
     */
    @JsonProperty("content")
    private List<T> content;

    /**
     * Metadados detalhando o estado da paginação (tamanho, total de páginas, etc).
     */
    @JsonProperty("page")
    private PageMetadata page;

    /**
     * Contém informações sobre a estrutura da paginação.
     */
    @Data
    @Builder
    public static class PageMetadata implements Serializable {

        /**
         * Quantidade de itens solicitados por página.
         */
        @JsonProperty("size")
        private Integer size;

        /**
         * O índice da página atual (geralmente baseado em zero).
         */
        @JsonProperty("number")
        private Integer number;

        /**
         * A quantidade total de elementos disponíveis no servidor que atendem aos critérios de busca.
         */
        @JsonProperty("totalElements")
        private Integer totalElements;

        /**
         * A quantidade total de páginas calculada com base no total de elementos e no tamanho da página.
         */
        @JsonProperty("totalPages")
        private Integer totalPages;
    }
}