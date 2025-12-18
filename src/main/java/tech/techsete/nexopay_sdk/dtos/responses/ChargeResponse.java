package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.techsete.nexopay_sdk.enums.ChargeStatus;
import tech.techsete.nexopay_sdk.enums.ChargeType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class ChargeResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private ChargeStatus status;

    @JsonProperty("paidAt")
    private OffsetDateTime paidAt;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private ChargeType type;

    @JsonProperty("endToEndId")
    private String endToEndId;

    @JsonProperty("payer")
    private PayerResponse payer;

    @JsonProperty("splitRules")
    private List<SplitRuleResponse> splitRules;

    @JsonProperty("webhook")
    private WebhookResponse webhook;

    @JsonProperty("pixDetails")
    private PixDetailsResponse pixDetails;

    @JsonProperty("fee")
    private BigDecimal fee;
}