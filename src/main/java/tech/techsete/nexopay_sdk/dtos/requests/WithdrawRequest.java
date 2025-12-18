package tech.techsete.nexopay_sdk.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.techsete.nexopay_sdk.enums.PixKeyType;
import tech.techsete.nexopay_sdk.enums.WithdrawMethod;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class WithdrawRequest implements Serializable {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("method")
    private WithdrawMethod method;

    @JsonProperty("pixKey")
    private String pixKey;

    @JsonProperty("pixKeyType")
    private PixKeyType pixKeyType;

    @JsonProperty("externalRef")
    private String externalRef;
}
