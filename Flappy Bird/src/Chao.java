
public class Chao {
	
	static final int alturaTela = 490;
	static final int altura = 60;
	static final int largura = 308;
	
	static final double velocidade = 77;
	
	double x, y;
	double velx;
	
	Timer animacao;
	
	public Chao(double x){
		this.velx = velocidade;
		this.x = x;
		this.y = alturaTela - altura;
	}

	public double getX(){
		return this.x;
	}
	
	public int getLargura(){
		return largura;
	}
	
	public void mover(double dt){
		this.x -= this.velx*dt;
	}
	
	public void desenhar(Tela tela){
		tela.imagem("flappy.png", 292, 0, largura, altura, 0.0, this.x, this.y);
	}
}
