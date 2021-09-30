package mn.isolvers.temp.service.rest;

import mn.isolvers.temp.api.v1.domain.MResponse;
import mn.isolvers.temp.api.v1.domain.UserDepartmentDTO;
import mn.isolvers.temp.service.internal.model.User;
import mn.isolvers.temp.service.internal.model.dbInfo;
import mn.isolvers.temp.service.internal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("info")
    public Flux<dbInfo> getAllInfo(){
        return userService.getAllInfo();
    }

    @GetMapping("/{userId}")
    public Mono<MResponse<User>> getUserById(@PathVariable Integer userId){
        Mono<User> user = userService.findById(userId);
        return user.map(res->{
                    final MResponse<User> fiRes =MResponse.of();
                    fiRes.setBody(res);
                    return fiRes;
                });
    }

    @PutMapping("/{userId}")
    public Mono<MResponse<User>> updateUserById(@PathVariable Integer userId, @RequestBody User user){
        return userService.updateUser(userId,user)
                .map(res-> {
                    final MResponse<User> fiRes = MResponse.of();
                    fiRes.setBody(res);
                    return fiRes;
                });
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable Integer userId){
        return userService.deleteUser(userId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/age/{age}")
    public Flux<User> getUsersByAge(@PathVariable int age) {
        return userService.findUsersByAge(age);
    }

    @PostMapping("/search/id")
    public Flux<User> fetchUsersByIds(@RequestBody List<Integer> ids) {
        return userService.fetchUsers(ids);
    }

    @GetMapping("/{userId}/department")
    public Mono<UserDepartmentDTO> fetchUserAndDepartment(@PathVariable Integer userId){
        return userService.fetchUserAndDepartment(userId);
    }

}
