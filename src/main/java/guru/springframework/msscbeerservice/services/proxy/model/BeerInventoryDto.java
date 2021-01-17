package guru.springframework.msscbeerservice.services.proxy.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {

	private Integer id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private Integer beerId;
    private Integer quantityOnHand;
}
