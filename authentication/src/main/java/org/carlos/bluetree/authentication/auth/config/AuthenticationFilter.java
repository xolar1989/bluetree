package org.carlos.bluetree.authentication.auth.config;

import lombok.AllArgsConstructor;
import org.carlos.bluetree.authentication.auth.service.AuthUserDetailService;
import org.carlos.bluetree.authentication.auth.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    private final TokenService tokenService;

    private final AuthUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith(jwtConfig.getPrefix())){
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.substring(jwtConfig.getPrefix().length());

        if(tokenService.isTokenValid(token)){
            String username = tokenService.getUsernameFromToken(token);

            UserDetails userDetails = userDetailService
                    .loadUserByUsername(username);

            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.getAuthorities()
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
