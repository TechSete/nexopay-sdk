package tech.techsete.nexopay_sdk.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import tech.techsete.nexopay_sdk.enums.ChargeType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@Builder
public class ChargeRequest implements Serializable {

    @NotNull(message = "Amount cannot be null")
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotNull(message = "Type cannot be null")
    @JsonProperty("type")
    private ChargeType type;

    @JsonProperty("description")
    private String description;

    @Valid
    @JsonProperty("splitRules")
    private Collection<SplitRuleRequest> splitRules;

    @Valid
    @JsonProperty("webhook")
    private WebhookRequest webhook;
}
