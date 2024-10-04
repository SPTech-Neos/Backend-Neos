package school.sptech.neosspringjava.service;
// package school.sptech.neosspringjava.service.user;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
// import school.sptech.neosspringjava.api.configuration.security.jwt.GerenciadorTokenJwt;
// import school.sptech.neosspringjava.api.dtos.usersDto.UserTokenDto;
// import school.sptech.neosspringjava.api.dtos.usersDto.UsersLoginDto;
// import school.sptech.neosspringjava.api.mappers.userMapper.UserMapper;
// import school.sptech.neosspringjava.domain.model.user.User;
// import school.sptech.neosspringjava.domain.repository.UserRepository.UserRepository;

// @Service
// public class UserService {

//     @Autowired
//     private GerenciadorTokenJwt gerenciadorTokenJwt;

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private UserRepository userRepository; // Injeta o UserRepository

//     public UserTokenDto authenticate(UsersLoginDto userLoginDTO) {

//         final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
//                 userLoginDTO.getEmail(), userLoginDTO.getPassword());
//         final Authentication authentication = this.authenticationManager.authenticate(credentials);

//         User userAuthentication = (User) userRepository.findByEmail(userLoginDTO.getEmail())
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email do usuário não cadastrado"));

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         final String token = gerenciadorTokenJwt.generateToken(authentication);

//         return UserMapper.of(userAuthentication, token);
//     }

// }
