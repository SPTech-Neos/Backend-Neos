package school.sptech.neosspringjava.api.controllers;
// package school.sptech.neosspringjava.api.controllers.UserController;

// import jakarta.validation.Valid;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import school.sptech.neosspringjava.api.dtos.usersDto.UserTokenDto;
// import school.sptech.neosspringjava.api.dtos.usersDto.UsersLoginDto;
// import school.sptech.neosspringjava.service.user.UserService;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     @Autowired
//     UserService userService;

//     @PostMapping("/login")
//     public ResponseEntity<UserTokenDto> login(@RequestBody UsersLoginDto usersLoginDto){
//         UserTokenDto usersToken = this.userService.authenticate(usersLoginDto);
//         return ResponseEntity.status(200).body(usersToken);
//     }
// }
