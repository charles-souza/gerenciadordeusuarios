package br.com.charles.gerenciadordeusuarios.repository;

import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
