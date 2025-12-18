package tech.techsete.nexopay_sdk.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class WebhookRequest implements Serializable {

    @NotEmpty(message = "URL cannot be empty")
    @JsonProperty("url")
    private String url;
}
