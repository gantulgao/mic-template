package mn.isolvers.temp.api.v1.client;

import mn.isolvers.temp.api.v1.domain.MResponse;
import mn.isolvers.temp.api.v1.domain.ReqSample1;
import mn.isolvers.temp.api.v1.domain.ResSample1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@SuppressWarnings("unused")
@ReactiveFeignClient(name = "template", path = "/template"
)
public interface SamplemainClient {

    @PostMapping(value = "/v1.0/template")
    Mono<MResponse<ResSample1>> postSample(@RequestBody ReqSample1 req);

    @GetMapping(value = "/v1.0/template")
    Mono<MResponse<ResSample1>> getSample(@RequestParam(required = false) String param1);

}
