/* Projeto de Computacao II - Flappy Bird
 * 
 * Professor: Fabio Mascarenhas;
 * Aluna: Mayara Martins Poim Fernandes;
 * DRE: 116023936;
 * 
 */

import java.util.HashSet;

public class FlappyBird implements Jogo{
	
	static final int xImgInicia = 292;
	static final int yImgInicia = 442;
	static final int larguraImgInicia = 174;
	static final int alturaImgInicia = 44;
	
	static final int xImgGameOver = 290;
	static final int yImgGameOver = 395;
	static final int larguraImgGameOver = 190;
	static final int alturaImgGameOver = 43;
	
	Passaro passaro = new Passaro();
	
	HashSet<Chao> chao = new HashSet<Chao>();
	HashSet<Cano> canos = new HashSet<Cano>();
	
	HashSet<Chao> lixeiraChao = new HashSet<Chao>();
	HashSet<Cano> lixeiraCano = new HashSet<Cano>();
		
	Timer animacaoChao;
	Timer animacaoCanos;
	Timer pontuacao;
	
	int score;
	
	double limiteChao, limite1;
	
	boolean gameOver;
	boolean inicia;
	
	public FlappyBird() {
		this.score = 0;
		
		this.gameOver = false;
		this.inicia = false;
		
		this.chao.add(new Chao(0));
		this.chao.add(new Chao(308));
		this.chao.add(new Chao(616));
		this.chao.add(new Chao(924));
		this.canos.add(new Cano());
		
		this.limiteChao = Chao.largura/Chao.velocidade;
		this.limite1 = (Cano.xCanoInicial + Cano.largura - this.passaro.x)/Cano.velocidade;
		
		this.animacaoChao = new Timer(limiteChao, true, 
			new Acao() {
				public void executa() {
					chao.add(new Chao(923));
				}
			});
		
		this.animacaoCanos = new Timer(2.5, true, 
			new Acao() {
				public void executa() {
					canos.add(new Cano());
				}
			});
		
		this.pontuacao = new Timer(this.limite1, false, 
			new Acao() {
				public void executa() {
					score++;
					pontuacao = new Timer(2.5, true, 
						new Acao(){
							public void executa() {
								score++;
							}
						});
				}
			});
	}
	
	public String getTitulo() {
		return "Flappy Bird";
	}
	  
	public int getLargura() {
		return 650;
	}
	  
	public int getAltura() {
		return 490;
	}
	  
	public void tique(java.util.Set<String> teclas, double dt) {

		if(this.passaro.vivo) {
			if(inicia) {
				for(Cano cano: this.canos) {
					if(this.passaro.colisao.intersecao(cano.colisaoCanoCima) > 0) {
						this.passaro.vivo = false;
						this.passaro.vely = 0;
					}
					
					if(this.passaro.colisao.intersecao(cano.colisaoCanoBaixo) > 0) {
						this.passaro.vivo = false;
						this.passaro.vely = 0;
					}
	
				}
				
				if(this.passaro.getY() + this.passaro.getAltura() >= this.getAltura() - Chao.altura) {
					this.passaro.vivo = false;
				}
				
				if(this.passaro.getY() <= 0.0) {
					this.passaro.y = 0.0;
				}
					
				this.passaro.mover(dt);
				  
				for(Cano cano: this.canos) {
					cano.mover(dt);
				}
				   
				this.animacaoCanos.tique(dt);
				this.pontuacao.tique(dt);
			}
			
			this.passaro.animacao.tique(dt);
			
			for(Chao pedaco: this.chao) {
				pedaco.mover(dt);
			}
			
			this.animacaoChao.tique(dt);
			this.removeItens();
		}
		
		else {
			if(this.passaro.getY() + this.passaro.getAltura() < this.getAltura() - Chao.altura){
				this.passaro.mover(dt);
			}
			else this.gameOver = true;
		}

	}
	
	public void removeItens() {
		
		for(Cano cano: this.canos) {
			if(cano.xCano < 0 - Cano.largura) {
				this.lixeiraCano.add(cano);
			}
		}
		
		for(Chao pedaco: this.chao) {
			if(pedaco.x < 0 - Chao.largura) {
				this.lixeiraChao.add(pedaco);
			}
		}
		
		this.canos.removeAll(this.lixeiraCano);
		this.chao.removeAll(this.lixeiraChao);
		
		this.lixeiraCano.clear();
		this.lixeiraChao.clear();
		
	}
	
	  
	public void tecla(String tecla) {
		if(tecla.equals(" ")) {
			this.inicia = true;
			
			if(this.passaro.vivo) this.passaro.vely = this.passaro.velSalto;
		}
	}
	  
	public void desenhar(Tela tela) {
		tela.imagem("flappy.png", 0, 0, 276, 490, 0.0, 0.0, 0.0);
		tela.imagem("flappy.png", 0, 0, 276, 490, 0.0, 276, 0.0);
		tela.imagem("flappy.png", 0, 0, 276, 490, 0.0, 552, 0.0);

		if(!inicia) {
			tela.imagem("flappy.png", xImgInicia, yImgInicia, larguraImgInicia, alturaImgInicia, 0.0, this.getLargura()/2 - larguraImgInicia/2, 100);
		}
		
		for(Cano cano: this.canos) {
			cano.desenhar(tela);
		}
		
		for(Chao pedaco: this.chao){
			pedaco.desenhar(tela);
		}	
		
		this.passaro.desenhar(tela);

		tela.texto(String.format("%04d", this.score), 267, 65, 50, Cor.BRANCO);
		
		if(this.gameOver){
            tela.imagem("flappy.png", xImgGameOver, yImgGameOver, larguraImgGameOver, alturaImgInicia, 0.0, this.getLargura()/2 - larguraImgGameOver/2, 75);
		}

	}
	
	public static void main(String[] args){
		new Motor(new FlappyBird());
	}
}
