package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Eletronico;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;
import com.leilao.lance.rapido.repository.BidRepository;
import com.leilao.lance.rapido.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//TODO: Implementar padrão Strategy para a regra de cada subclasse.
//Veiculo: Diferenca de lances de no minimo R$500, minimamente 3 imagens, campo document necessario.
//Movel: Diferenca de lances de no minimo R$20, minimamente 1 imagem.
//Eletronico: Diferença de lances de no minimo R$10, minimamente 1 imagem, campo voltagem necessario.
// Pode mudar isso a vontade, o importante é que cada regra tenha algo diferente.

@Component
public class ProductService {
	@Autowired
	private final ProductRepository<Product> productRepository;
	
	@Autowired
    private final ProductRepository<Veiculo> veiculoRepository;
	
	@Autowired
	private final ProductRepository<Movel> movelRepository;
	
	@Autowired
	private final ProductRepository<Eletronico> eletronicoRepository;
	
	@Autowired
    private final BidRepository bidRepository;

	// Da pra retirar os repositorios das subclasses e deixar apenas o productRepository,
	// mas vai ser necessario fazer um cast da subclasse para a classe pai nos metodos.
    public ProductService(ProductRepository<Veiculo> veiculoRepository, BidRepository bidRepository, ProductRepository<Movel> movelRepository,
    		ProductRepository<Eletronico> eletronicoRepository, ProductRepository<Product> productRepository){
        this.veiculoRepository = veiculoRepository;
        this.bidRepository = bidRepository;
        this.movelRepository = movelRepository;
        this.eletronicoRepository = eletronicoRepository;
        this.productRepository = productRepository;
    }
    
    // Destrinchar as regras de cada subclasse contida aqui no padrao strategy
    public Product saveProduct(Product product) {
    	if (!product.equals(productRepository.findByCreationTimeAndUserId(product.getCreationTime(), product.getUser().getId())))
    		return productRepository.save(product);
    	
    	if (product.getImages() == null)
			return null;
    	
    	double bidDifference = 0.00;
    	if (product.getClass().equals(Veiculo.class)) {
    		bidDifference = 500.00;
    		Veiculo veiculo = (Veiculo) product;
    		
    		if (veiculo.getDocumento() == null)
    			return null;
    		
    	} else if (product.getClass().equals(Eletronico.class)) {
    		bidDifference = 10.00;
    		Eletronico eletronico = (Eletronico) product;
    		
    		if (eletronico.getVoltagem() != 110 || eletronico.getVoltagem() != 220)
    			return null;
    		
    	} else if (product.getClass().equals(Movel.class)) {
    		bidDifference = 20.00;
    	} else {
    		return null;
    	}
    	
    	Bid lastBid = product.getBids().iterator().next();
    	if (lastBid.getBidValue() - findHighestBid(product).getBidValue() < bidDifference)
    		return null;
    	
    	return productRepository.save(product);
    }
    
    public List<Veiculo> getVeiculoCatalog(){
    	List<Veiculo> veiculos = veiculoRepository.findByActiveTrue();
    	
    	if (veiculos.isEmpty())
    		return Collections.emptyList();
    	
    	for (Veiculo veiculo : veiculos) {
    		if (!updateProductState(veiculo).isActive())
    			veiculos.remove(veiculo);
    	}
    	
        return veiculos;
    }
    
    public List<Movel> getMovelCatalog(){
    	List<Movel> moveis = movelRepository.findByActiveTrue();
    	
    	if (moveis.isEmpty())
    		return Collections.emptyList();
    	
    	for (Movel movel : moveis) {
    		if (!updateProductState(movel).isActive())
    			moveis.remove(movel);
    	}
    	
        return moveis;
    }
    
    public List<Eletronico> getEletronicoCatalog(){
    	List<Eletronico> eletronicos = eletronicoRepository.findByActiveTrue();
    	
    	if (eletronicos.isEmpty())
    		return Collections.emptyList();
    	
    	for (Eletronico eletronico : eletronicos) {
    		if (!updateProductState(eletronico).isActive())
    			eletronicos.remove(eletronico);
    	}
    	
        return eletronicos;
    }
    
    public Product updateProductState(Product product) {
    	if (LocalDateTime.now().isAfter(product.getTimeLimit())) {
    		if (product.isActive()) {
    			product.setActive(false);
    			productRepository.save(product);
    		}
    	}
    	
    	return product;
    }
    
    public Product addBid(Bid bid) {
        Optional<Product> productOpt = productRepository.findById(bid.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        } else {
            Product product = productOpt.get();
            if(!updateProductState(product).isActive())
            	return null;
       
            Set<Bid> bids = product.getBids();
            bids.add(bid);
            product.setBids(bids);
            product.setHighestBid(findHighestBid(product));
            productRepository.save(product);
            return product;
        }
    }
    
    public Bid findHighestBid(Product product) {
    	if (product.equals(null))
    		return null;
    	
        Bid highestBid = product.getBids().iterator().next();
    	return highestBid;
    }
    
    public List<Product> findUserBoughtVeiculos(Integer userId) {
    	Set<Bid> bids = bidRepository.findByUserId(userId);
    	List<Product> boughtVeiculos = Collections.emptyList();
    	for (Bid bid : bids) {
    		if (bid.getProduct().getClass().equals(Veiculo.class)) {
	    		if (bid.getProduct().getHighestBid().equals(bid))
	    			boughtVeiculos.add(bid.getProduct());
    		}
    	}
    	
    	if (boughtVeiculos.equals(null))
    		return Collections.emptyList();
    	return boughtVeiculos;
    }
    	
    public List<Product> findUserBoughtEletronicos(Integer userId) {
    	Set<Bid> bids = bidRepository.findByUserId(userId);
    	List<Product> boughtEletronicos = Collections.emptyList();
    	for (Bid bid : bids) {
    		if (bid.getProduct().getClass().equals(Eletronico.class)) {
	    		if (bid.getProduct().getHighestBid().equals(bid))
	    			boughtEletronicos.add(bid.getProduct());
    		}
    	}
    	
    	if (boughtEletronicos.equals(null))
    		return Collections.emptyList();
    	return boughtEletronicos;
    }
        	
    public List<Product> findUserBoughtMoveis(Integer userId) {
    	Set<Bid> bids = bidRepository.findByUserId(userId);
    	List<Product> boughtMoveis = Collections.emptyList();
    	for (Bid bid : bids) {
    		if (bid.getProduct().getClass().equals(Movel.class)) {
	    		if (bid.getProduct().getHighestBid().equals(bid))
	    			boughtMoveis.add(bid.getProduct());
    		}
    	}
    	
    	if (boughtMoveis.equals(null))
    		return Collections.emptyList();
    	return boughtMoveis;
    }

    public Product saveComment(Comment comment){
        Optional<Product> productOpt = productRepository.findById(comment.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        }
        else {
            Product product = productOpt.get();
            if(!updateProductState(product).isActive())
            	return null;
            
            Set<Comment> comments = product.getComments();
            comments.add(comment);
            product.setComments(comments);
            return productRepository.save(product);
        }
    }
    
    public List<Product> findActiveProductsByUserId(Integer userId) {
    	return productRepository.findByUserIdAndActiveTrue(userId);
    }
    
    public List<Product> findSoldProductsByUserId(Integer UsedId) {
    	return productRepository.findByUserIdAndActiveFalseAndBidsNotNull(UsedId);
    }

    public List<Product> findByUserId(Integer userId){
        return productRepository.findByUserId(userId);
    }
    public List<Product> findActiveBidsByUserId(Integer userId){
        return productRepository.findByBidsUserIdAndActiveTrue(userId);
    }
    
    public Product findActiveProductById(Integer productId) {
    	return productRepository.findByIdAndActiveTrue(productId);
    }
}
