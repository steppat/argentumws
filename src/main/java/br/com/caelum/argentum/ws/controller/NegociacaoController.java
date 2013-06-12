package br.com.caelum.argentum.ws.controller;

import static br.com.caelum.vraptor.view.Results.xml;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.argentum.ws.modelo.Data;
import br.com.caelum.argentum.ws.modelo.GeradorNegociacoes;
import br.com.caelum.argentum.ws.modelo.Negociacao;
import br.com.caelum.argentum.ws.modelo.Quantidade;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Resource
@RequestScoped
public class NegociacaoController {

	@Get("/negociacoes")
	public void negocios(HttpServletResponse response, Result result)
			throws IOException, ParseException {

		Quantidade dois = Quantidade.DOIS;
		Data hoje = new Data();
		Data passado = hoje.mesesAtras(dois);

		GeradorNegociacoes gerador = new GeradorNegociacoes(passado, hoje);

		List<Negociacao> negociacoes = gerador.getNegociacoes();

		 result.use(xml()).from(negociacoes).serialize();

	}

}
