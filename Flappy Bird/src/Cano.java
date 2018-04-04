
public class Cano {
	
	static final int alturaTela = 490;
	static final int largura = 52;
	static final int alturaSpriteCanoCima = 270;
	static final int alturaSpriteCanoBaixo = 242;
	
	static final double xCanoInicial = 750;
	static final double velocidade = 77;
	
	static final int xImgCanoCima = 603;
	static final int xImgCanoBaixo = 659;
	static final int yImgCanoBaixo = 0;
	
	static final int espaco = 85;
	
	int alturaCanoCima;
	int yImgCanoCima;
	int yChao;
	
	double xCano;
	double yCanoBaixo;
	double yCanoCima;
	double velx;
	
	Hitbox colisaoCanoCima;
	Hitbox colisaoCanoBaixo;
	
	public Cano(){
		this.xCano = xCanoInicial;
		this.yCanoBaixo = Math.random() * 170 + 190;
		this.yCanoCima = 0;
		this.yChao = 430;
		
		this.velx = velocidade;
				
		this.alturaCanoCima = (int) (alturaTela - espaco - (alturaTela - this.yCanoBaixo));
		this.yImgCanoCima = alturaSpriteCanoCima - this.alturaCanoCima;
		
		this.colisaoCanoCima = new Hitbox(this.xCano, this.yCanoCima, this.xCano + largura, this.yCanoCima + this.alturaCanoCima);
		this.colisaoCanoBaixo = new Hitbox(this.xCano, this.yCanoBaixo, this.xCano + largura, this.yChao);
	}
	
	public double getX(){
		return this.xCano;
	}
	
	public int getLargura(){
		return largura;
	}
	
	public void mover(double dt){
		this.xCano -= this.velx * dt;
		
		this.colisaoCanoCima.mover(this.xCano, this.yCanoCima, this.xCano + largura, this.yCanoCima + this.alturaCanoCima);
		this.colisaoCanoBaixo.mover(this.xCano, this.yCanoBaixo, this.xCano + largura, this.yChao);
	}
	
	public void desenhar(Tela tela){
		tela.imagem("flappy.png", xImgCanoBaixo, yImgCanoBaixo, largura, alturaSpriteCanoBaixo, 0.0, xCano, yCanoBaixo);
		tela.imagem("flappy.png", xImgCanoCima, yImgCanoCima, largura, alturaCanoCima, 0.0, xCano, yCanoCima);
	}
}
