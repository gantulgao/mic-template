package mn.isolvers.temp.api.v1.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Data
@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MResponse<T> {
    private String code = "200";
    private String message = "Амжилттай";
    @JsonProperty("data")
    private T body;
    @JsonProperty("additionalProperties")
    private Map<String,Object> addj;

    public MResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

    public MResponse<T> error(Throwable err){
        if(err!=null) {
            if (addj==null) this.addj = new HashMap<>();
            this.addj.put("err", ExceptionUtils.getStackTrace(err));
        }
        return this;
    }

    public MResponse<T> error(String err){
        if(err!=null) {
            if (addj==null) addj = new HashMap<>();
            this.addj.put("err", err);
        }
        return this;
    }

    public MResponse<T> stat(MError err){
        this.code = err.getCode();
        this.message = err.getMessage();
        return this;
    }

    public MResponse<T> body(T body){
        this.body = body;
        return this;
    }

    public MResponse<T> addj(Map<String,Object> addj){
        this.addj = addj;
        return this;
    }

}
