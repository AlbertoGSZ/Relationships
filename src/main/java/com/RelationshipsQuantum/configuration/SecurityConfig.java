package com.RelationshipsQuantum.configuration;
import com.RelationshipsQuantum.components.AuthenticationTokenFilter;
import com.RelationshipsQuantum.constants.AuthorityName;
import com.RelationshipsQuantum.constants.properties.JwtProperties;
import com.RelationshipsQuantum.services.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final JwtProperties jwtProperties;

	public SecurityConfig(CustomUserDetailsServiceImpl userDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint, JwtProperties jwtProperties) {
		this.userDetailsService = userDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.jwtProperties = jwtProperties;
	}

	@Bean
	@Override
	//con el bean, la primera vez que spring necesite crear un password encoder le indica que utilice este para inyectarlo donde
	//quiera hacerlo. Solo se usa una vez porque una vez cogido ya lo guarda en memoria
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	//encriptar la contraseña
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	//configuracion global de la seguridad
	//con esto seteamos el autentication donde le decimos el authentication manager que usamos y el password encode
	//necesitamos pasar el password encode porque las contraseñas en bbdd están cifradas
	public void configureAuthenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	//son una serie de restrictiones de los navegadores que hay que configurar y con esto se configura solo
	//menos si queremos restringir algún dominio
	//authenticationEntryPoint la añadimos un filtro que es el authentication token filter
	//si metemos authorized request poner un patron como que todo lo que todo lo que tenga auth te lo autentica
	//de esta forma aseguras todo el endpoint
	//también se puede poner a nivel de cada endpoint la seguridad

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilter(new AuthenticationTokenFilter(jwtProperties, authenticationManagerBean()))
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.DELETE).hasAuthority(AuthorityName.ROLE_ADMIN.toString())
			.antMatchers("/auth/login").permitAll()
			.anyRequest().permitAll();
		//.anyRequest().hasAnyRole("ADMIN", "USER");
	}

}
