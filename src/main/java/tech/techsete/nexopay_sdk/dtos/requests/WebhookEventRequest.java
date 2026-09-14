package tech.techsete.nexopay_sdk.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.techsete.nexopay_sdk.enums.WebhookEventType;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebhookEventRequest implements Serializable {

    @JsonProperty("type")
    private WebhookEventType type;

    @JsonProperty("payload")
    private JsonNode payload;
}
