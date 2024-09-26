package ir.technyx.icm_core.security.conf;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.dto.ResUserAuthenticationDto;
import ir.technyx.icm_core.mappers.user.UserMapper;
import ir.technyx.icm_core.repository.user.UserAccessRepository;
import ir.technyx.icm_core.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    private final UserAccessRepository userAccessRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            ResUserAuthenticationDto resUserAuthenticationDto = UserMapper.toAuthenticationDto(user);

            Set<Role> userRoles = userAccessRepository.findAllRoleByUser_Id(user.getId());
            resUserAuthenticationDto.setRoles(userRoles);
            return resUserAuthenticationDto;
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
