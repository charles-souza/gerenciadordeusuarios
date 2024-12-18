package br.com.charles.gerenciadordeusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}
