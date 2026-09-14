package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayerResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nationalRegistration")
    private String nationalRegistration;
}
