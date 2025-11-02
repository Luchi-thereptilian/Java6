package Bai2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe) {
        String password = pe.encode("123");
        UserDetails user1
                = User.withUsername("user@gmail.com").password(password).roles("USER").build();
        UserDetails user2
                = User.withUsername("admin@gmail.com").password(password).roles("ADMIN").build();
        UserDetails user3
                = User.withUsername("guest@gmail.com").password(password).roles("GUEST").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Bỏ cấu hình mặc định CSRF và CORS
        http.csrf(config -> config.disable()).cors(config -> config.disable());
// Phân quyền sử dụng
        http.authorizeHttpRequests(config -> {
            config.anyRequest().permitAll();
        });
// Form đăng nhập mặc định
        http.formLogin(config -> config.permitAll());
// Form đăng nhập
        http.formLogin(config -> {
            config.permitAll();
        });
// Ghi nhớ tài khoản
        http.rememberMe(config -> {
            config.tokenValiditySeconds(3 * 24 * 60 * 60);
        });
// Đăng xuất
        http.logout(Customizer.withDefaults());
        return http.build();
    }
}
