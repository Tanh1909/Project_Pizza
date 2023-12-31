package com.example.projectbase;

import com.example.projectbase.config.properties.AdminInfoProperties;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.domain.entity.RoleEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
//@RequiredArgsConstructor
@EnableConfigurationProperties({AdminInfoProperties.class})
@SpringBootApplication
public class ProjectBaseApplication {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  public ProjectBaseApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public static void main(String[] args) {
    Environment env = SpringApplication.run(ProjectBaseApplication.class, args).getEnvironment();
    String appName = env.getProperty("spring.application.name");
    if (appName != null) {
      appName = appName.toUpperCase();
    }
    String port = env.getProperty("server.port");
    log.info("-------------------------START " + appName
        + " Application------------------------------");
    log.info("   Application         : " + appName);
    log.info("   Url swagger-ui      : http://localhost:" + port + "/swagger-ui.html");
    log.info("-------------------------START SUCCESS " + appName
        + " Application------------------------------");
  }

//  @Bean
//  CommandLineRunner init(AdminInfoProperties userInfo) {
//    return args -> {
//      //init role
//      if (roleRepository.count() == 0) {
//        roleRepository.save(new RoleEntity(null, RoleConstant.ADMIN, null));
//        roleRepository.save(new RoleEntity(null, RoleConstant.USER, null));
//      }
//      //init admin
//      if (userRepository.count() == 0) {
//        UserEntity admin = UserEntity.builder().username(userInfo.getUsername())
//            .password(passwordEncoder.encode(userInfo.getPassword()))
//            .firstName(userInfo.getFirstName()).lastName(userInfo.getLastName())
//            .roleEntity(roleRepository.findByRoleName(RoleConstant.ADMIN)).build();
//        userRepository.save(admin);
//      }
//    };
//  }

}
