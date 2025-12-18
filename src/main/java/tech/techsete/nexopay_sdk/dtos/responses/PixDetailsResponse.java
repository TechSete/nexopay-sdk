package tech.techsete.nexopay_sdk.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
public class PixDetailsResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonProperty("expirationDate")
    private OffsetDateTime expirationDate;

    @JsonProperty("copyPast")
    private String copyPast;

    @JsonProperty("qrCode")
    private String qrCode;
}