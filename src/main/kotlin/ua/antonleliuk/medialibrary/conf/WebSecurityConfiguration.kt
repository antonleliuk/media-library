package ua.antonleliuk.medialibrary.conf

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.expression.WebExpressionVoter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import ua.antonleliuk.medialibrary.security.authentication.AjaxAuthenticationFailureHandler
import ua.antonleliuk.medialibrary.security.authentication.AjaxAuthenticationSuccessHandler
import ua.antonleliuk.medialibrary.user.service.UserService

/**
@author Anton Leliuk
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val PRIV_ANONYMOUS : String = "PRIV_ANONYMOUS"
    private val PRIV_SYSTEM_ENTRY = "PRIV_SYSTEM_ENTRY"

    @Autowired
    lateinit var userService: UserService

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .securityContext()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .anonymous().authorities(PRIV_ANONYMOUS)
                .and()
                .authorizeRequests()
                    .antMatchers("/login/*").hasAnyAuthority(PRIV_ANONYMOUS, PRIV_SYSTEM_ENTRY)
                    .antMatchers("/logout").hasAnyAuthority(PRIV_ANONYMOUS, PRIV_SYSTEM_ENTRY)
                    .antMatchers("/*").hasAnyAuthority(PRIV_SYSTEM_ENTRY)
                    .accessDecisionManager(accessDecisionManager())
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login-process")
                    .failureUrl("/login")
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                    .permitAll()
                    .and()
                .exceptionHandling().accessDeniedPage("/denied").authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .clearAuthentication(true)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.eraseCredentials(true)
                .userDetailsService(userService)
                .passwordEncoder(BCryptPasswordEncoder(10))
    }

    override fun userDetailsService(): UserDetailsService {
        return userService
    }

    override fun userDetailsServiceBean(): UserDetailsService {
        return userService
    }

    @Bean
    fun accessDecisionManager(): AffirmativeBased {
        return AffirmativeBased(listOf(AuthenticatedVoter(), WebExpressionVoter()))
    }

    @Bean
    fun authenticationEntryPoint(): LoginUrlAuthenticationEntryPoint {
        return LoginUrlAuthenticationEntryPoint("/login")
    }

    @Bean
    fun authenticationFailureHandler() : AuthenticationFailureHandler {
        return AjaxAuthenticationFailureHandler()
    }

    @Bean
    fun authenticationSuccessHandler() : AuthenticationSuccessHandler {
        return AjaxAuthenticationSuccessHandler("/home")
    }
}