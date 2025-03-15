package com.rafabene.application;

import java.util.List;

import com.rafabene.dominio.entity.Pessoa;
import com.rafabene.dominio.repositorio.PessoaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Response<Pessoa> criarPessoa(Pessoa pessoa) {
        pessoaRepository.persist(pessoa);
        return new Response<Pessoa>(pessoa, "Pessoa criada com sucesso", Response.Status.SUCESS);
    }

    @GET
    public PageResponse<List<Pessoa>> listarPessoas(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("5") int size,
            @QueryParam("order") @DefaultValue("nome") String order,
            @QueryParam("orderType") @DefaultValue("ASC") String direction) {

        Direction orderType = Direction.Ascending;
        if (direction.equalsIgnoreCase("DESC")) {
            orderType = Direction.Descending;
        } else {
            orderType = Direction.Ascending;
        }
        long total = pessoaRepository.count();
        PanacheQuery<Pessoa> q = pessoaRepository
                .findAll(Sort
                        .by(order).direction(orderType))
                .page(page - 1, size);
        var listPessoas = q.list();
        var pr = new PageResponse<List<Pessoa>>(listPessoas, "Pessoas obtidas com sucesso",
                Response.Status.SUCESS, page, size, total);
        return pr;
    }

}
