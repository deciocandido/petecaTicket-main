package ao.peteca.backend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ao.peteca.backend.config.JwtService;
import ao.peteca.backend.user.Role;
import ao.peteca.backend.user.User;
import ao.peteca.backend.user.UserRepository;
import ao.peteca.backend.token.TokenRepository;
import ao.peteca.backend.token.Token;
import ao.peteca.backend.token.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    System.out.println("----- START CREATING THE TOKEN AND THE USER -----");
    System.out.println("----- START CREATING THE USER -----");
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    System.out.println("----- SAVE THE USER -----");
    var savedUser = repository.save(user);
    System.out.println("----- GENERATE THE TOKEN -----");
    var jwtToken = jwtService.generateToken(user);
    System.out.println("----- SAVE THE TOKEN -----");
    saveUserToken(savedUser, jwtToken);
    System.out.println("----- END SAVING THE THE TOKEN AND THE USER -----");
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    System.out.println("----- START AUTHENTICATE THE TOKEN AND THE USER -----");
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    System.out.println("----- FIND THE USER -----");
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    System.out.println("----- FIND TOKEN OF THE USER -----");
    var jwtToken = jwtService.generateToken(user);
    System.out.println("----- REVOKE ALL TOKEN OF THE USER -----");
    revokeAllUserTokens(user);
    System.out.println("----- SAVE THE TOKEN OF THE USER -----");
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    System.out.println("----- SAVE THE TOKEN -----");
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    System.out.println("----- SAVE ALL THE TOKEN -----");
    tokenRepository.saveAll(validUserTokens);
  }
}