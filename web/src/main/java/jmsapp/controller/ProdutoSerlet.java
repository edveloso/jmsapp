package jmsapp.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmsapp.ejb.ProdutoEjb;
import jmsapp.modelo.Produto;


@WebServlet(name = "prod" , urlPatterns = "/prod")
public class ProdutoSerlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoEjb produtoEjb;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nome = req.getParameter("nome");
		Produto produto = produtoEjb.add(new Produto(nome));
		
		req.setAttribute("produto", produto);
		req.getRequestDispatcher("home.jsp").forward(req, resp);
		
	}
	
	
}
