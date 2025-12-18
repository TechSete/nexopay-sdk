package tech.techsete.nexopay_sdk.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.techsete.nexopay_sdk.enums.WebhookEventType;

import java.io.Serializable;

@Data
@Builder
public class WebhookEventRequest implements Serializable {

    @JsonProperty("type")
    private WebhookEventType type;

    @JsonProperty("payload")
    private Object payload;
}
