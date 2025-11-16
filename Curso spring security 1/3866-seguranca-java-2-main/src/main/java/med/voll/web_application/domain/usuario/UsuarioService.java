package med.voll.web_application.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {

        this.usuarioRepository = usuarioRepository;
        this.encoder =  encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado!"));
    }

    public Long salvarUsuario(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Pattern(regexp = "\\d{4,6}", message = "CRM deve ter de 4 a 6 digitos numéricos") String senha, Perfil perfil) {
        String senhaCript = encoder.encode(senha);
        Usuario usuario =  usuarioRepository.save(new Usuario(nome,email,senhaCript, perfil));
         return usuario.getId();
    }

    public void Excluir(Long id){
        usuarioRepository.deleteById(id);
    }
}
