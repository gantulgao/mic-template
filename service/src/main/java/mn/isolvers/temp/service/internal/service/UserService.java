package mn.isolvers.temp.service.internal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.isolvers.temp.api.v1.domain.MResponse;
import mn.isolvers.temp.api.v1.domain.UserDepartmentDTO;
import mn.isolvers.temp.service.internal.model.Department;
import mn.isolvers.temp.service.internal.model.User;
import mn.isolvers.temp.service.internal.model.dbInfo;
import mn.isolvers.temp.service.internal.repository.DepartmentRepository;
import mn.isolvers.temp.service.internal.repository.InfoRepository;
import mn.isolvers.temp.service.internal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.function.BiFunction;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final InfoRepository infoRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Flux<dbInfo> getAllInfo(){
        return infoRepository.findAll();
    }

    public Mono<User> findById(Integer userId){
        return //userRepository.findById(userId)
                userRepository.getUser(userId)
                ;
    }

    public Mono<User> updateUser(Integer userId, User user){
        log.info("In updateUser ...");

        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                })
                .doOnError(e->log.error("Cannot update!",e))
                .doFinally(e->log.info("Out updateUser with {}",e));

    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                .then(Mono.just(existingUser)));
    }

    public Flux<User> findUsersByAge(int age){
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::findById)
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Department> getDepartmentByUserId(Integer userId){
        return departmentRepository.findByUserId(userId);
    }

    public Mono<UserDepartmentDTO> fetchUserAndDepartment(Integer userId){
        Mono<User> user = findById(userId).subscribeOn(Schedulers.boundedElastic());
        Mono<Department> department = getDepartmentByUserId(userId).subscribeOn(Schedulers.boundedElastic());
        return Mono.zip(user, department, userDepartmentDTOBiFunction);
    }

    private final BiFunction<User, Department, UserDepartmentDTO> userDepartmentDTOBiFunction = (x1, x2) -> UserDepartmentDTO.builder()
            .age(x1.getAge())
            .departmentId(x2.getId())
            .departmentName(x2.getName())
            .userName(x1.getName())
            .userId(x1.getId())
            .loc(x2.getLoc())
            .salary(x1.getSalary()).build();

}
