package mn.isolvers.temp.api.v1.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResSample1 {

    private String bic;
    private String bankcode;
    private String branchcode;
    private String accountid;
    private String bankname;

}
