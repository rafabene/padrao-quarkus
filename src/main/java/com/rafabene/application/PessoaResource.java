package com.rafabene.application;

import java.io.ObjectInputFilter.Status;
import java.util.List;

import com.rafabene.dominio.entity.Pessoa;
import com.rafabene.dominio.repositorio.PessoaRepository;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/pessoas")
public class PessoaResource {

    @Inject
    PessoaRepository pessoaRepository;

    @POST
    public Response<Pessoa> criarPessoa(Pessoa pessoa) {
        pessoaRepository.persist(pessoa);
        return new Response<Pessoa>(pessoa, "Pessoa criada com sucesso", Response.Status.SUCESS);
    }

    @GET
    public PageResponse<List<Pessoa>> listarPessoas(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("5") int size) {
        long count = pessoaRepository.count();
        pessoaRepository.findAll().page(page, size);
        PageResponse pr = new PageResponse<List<Pessoa>>(pessoaRepository.listAll(), "Pessoas listadas com sucesso",
                Response.Status.SUCESS, page, size, (int) Math.ceil(count / size), count);
        return null;
    }

}
