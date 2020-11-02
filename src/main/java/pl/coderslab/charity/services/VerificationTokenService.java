package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.VerificationToken;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.exceptions.TokenExpiredException;
import pl.coderslab.charity.repositories.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserService userService;

    public void save(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken getToken(String token) {
        return verificationTokenRepository.getByToken(token);
    }

    public void activateAccount(String token) {
        VerificationToken verificationToken = getToken(token);
        if (verificationToken == null) {
            throw new ElementNotFoundException("token.not.found.message");
        }
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token.expired.message");
        }
        User user = userService.getUserById(verificationToken.getUser().getId());
        user.setEnabled(true);
        userService.save(user);
        deleteToken(verificationToken);
    }

    public void deleteToken(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }
}
