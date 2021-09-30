package mn.isolvers.temp.api.v1.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonPropertyOrder({"code","message"})
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public final class MError {

    private String code;
    private String message;

    public String setParam(final Object... param){
        return String.format(this.message, param);
    }

}
