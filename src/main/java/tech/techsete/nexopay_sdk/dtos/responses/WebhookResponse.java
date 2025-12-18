package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
public class WebhookResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonProperty("url")
    private String url;

    @JsonProperty("httpStatus")
    private Integer httpStatus;

    @JsonProperty("httpError")
    private String httpError;

    @JsonProperty("attempt")
    private Integer attempt;

    @JsonProperty("completed")
    private boolean completed;
}
