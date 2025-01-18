package com.example.OnlineJobPortal.Config;

import com.example.OnlineJobPortal.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//declare it as config class
@Configuration
//to set own configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //first disable csrf
        return httpSecurity
                .csrf(Customizer -> Customizer.disable())
                .cors(Customizer.withDefaults())

                //for login restriction
                .authorizeHttpRequests(request -> request
                        //this two link will not require authentication
                        .requestMatchers("/api/login","/api/register").permitAll()
//                        .requestMatchers("/addJob").hasAnyRole("USER", "ADMIN", "EMPLOYEER")

                        //jobs api
                        .requestMatchers(HttpMethod.POST, "/api/job").hasRole("EMPLOYEER")
                        .requestMatchers(HttpMethod.GET, "/api/jobs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/searchJobs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jobs/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/{id}").hasRole("EMPLOYEER")
                        .requestMatchers(HttpMethod.DELETE, "/api/jobs/{id}").hasRole("EMPLOYEER")

                        //users api
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()//fix
                        .requestMatchers(HttpMethod.PUT, "/api/users/{id}").permitAll()

                        //employers api
                        .requestMatchers(HttpMethod.GET, "/api/employers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/postedJobs/{id}").permitAll() //posted jobs by employer

                        //job seekers api
                        .requestMatchers(HttpMethod.GET, "/api/jobseekers").permitAll()

                        //job application api
                        .requestMatchers(HttpMethod.POST,"/api/application/{id}").permitAll()

                        .anyRequest().authenticated())
                //to enable login with rest client
                .httpBasic(Customizer.withDefaults())
                //make session stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // implement jwtfilter before upaf
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //dao authentication provider is the authentication provider for database
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        //use Bycrypt encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        //user details service to send name password from database for authentication and authorization
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
