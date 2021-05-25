package com.Auth2.auth.security;


import com.Auth2.auth.enitity.AppUser;
import com.Auth2.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    public AuthorizationServerConfiguration() {
        super();
    }

    @Bean
    @Primary
    DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore()).userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
//                .accessTokenConverter(accessTokenConverter())
        ;

    }
//
//    @Bean
//    JwtAccessTokenConverter accessTokenConverter(){
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey("1234");
//        return jwtAccessTokenConverter;
//    }

    @Bean
    public TokenStore tokenStore() {
       return new InMemoryTokenStore();
       // return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("live-test")
                .secret(passwordEncoder.encode("abcde"))
                .authorizedGrantTypes("password","refresh_token")
                .refreshTokenValiditySeconds(30 * 24 * 3600)
                .scopes("app")
                .accessTokenValiditySeconds(7*24*60);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
        authorizationServerSecurityConfigurer.checkTokenAccess("permitAll()");
    }
//    @EventListener
//    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent){
//        if(authorizedEvent.getAuthentication().getPrincipal() instanceof AppUser){
//            String username = ((AppUser) authorizedEvent.getAuthentication().getPrincipal()).getUsername();
//            User user = userRepository.findAllByUsernameIgnoreCase(username);
//            if(user != null){
//                user.setAttempts(0);
//                userRepository.save(user);
//            }
//        }
//    }
//    @EventListener
//    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent){
//        String username = (String) oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal();
//        userServices.manageAttempts(username);
//    }
}


