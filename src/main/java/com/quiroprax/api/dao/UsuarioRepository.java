package com.quiroprax.api.dao;

import com.quiroprax.api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

}
