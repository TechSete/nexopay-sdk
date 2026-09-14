package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SplitRuleResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("accountId")
    private String accountId;
}
