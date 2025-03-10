package com.rafabene.dominio.repositorio;

import java.util.UUID;

import com.rafabene.dominio.entity.Pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepositoryBase<Pessoa, UUID> {

}
