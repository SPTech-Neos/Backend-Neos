package school.sptech.neosspringjava.api.mappers.userMapper;

import org.springframework.stereotype.Component;
import school.sptech.neosspringjava.api.dtos.usersDto.UserResponse;
import school.sptech.neosspringjava.api.dtos.usersDto.UserTokenDto;
import school.sptech.neosspringjava.api.dtos.usersDto.UsersCreatDto;
import school.sptech.neosspringjava.domain.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static List<UserResponse> toUserResponse(List<User> users) {
        return users.stream().map(UserMapper::toUserResponse).collect(Collectors.toList());
    }

    public static User of(UsersCreatDto userCreatDTO){
        User user = new User();

        user.setEmail(userCreatDTO.getEmail());
        user.setName(userCreatDTO.getName());
        user.setPassword(userCreatDTO.getPassword());

        return user;
    }

    public static UserTokenDto of(User user, String token){
        UserTokenDto userTokenDto = new UserTokenDto();

        userTokenDto.setUserId(user.getId());
        userTokenDto.setEmail(user.getEmail());
        userTokenDto.setName(user.getName());
        userTokenDto.setToken(token);

        return userTokenDto;
    }
}
