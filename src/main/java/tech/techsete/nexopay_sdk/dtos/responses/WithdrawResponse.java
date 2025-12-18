package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.techsete.nexopay_sdk.enums.PixKeyType;
import tech.techsete.nexopay_sdk.enums.WithdrawMethod;
import tech.techsete.nexopay_sdk.enums.WithdrawStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class WithdrawResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("method")
    private WithdrawMethod method;

    @JsonProperty("pixKey")
    private String pixKey;

    @JsonProperty("pixKeyType")
    private PixKeyType pixKeyType;

    @JsonProperty("status")
    private WithdrawStatus status;

    @JsonProperty("approvedAt")
    private OffsetDateTime approvedAt;

    @JsonProperty("processedAt")
    private OffsetDateTime processedAt;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("externalRef")
    private String externalRef;
}
