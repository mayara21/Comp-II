public class Passaro {
	
	private class TrocaAnimacao implements Acao {
		private int[] coordenadasx;
		private int[] coordenadasy;
		private int numeroFrames, atual;
		
		private int x, y;
		
		public TrocaAnimacao(int[] coordsx, int[] coordsy, int frames) {
			this.coordenadasx = coordsx;
			this.coordenadasy = coordsy;
			this.numeroFrames = frames;
			this.atual = 0;
			this.x = this.coordenadasx[atual];
			this.y = this.coordenadasy[atual];
		}
		
		public void executa(){
			this.atual++;
			this.atual %= this.numeroFrames;
			this.x = this.coordenadasx[atual];
			this.y = this.coordenadasy[atual];
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
	}
	
	static final int altura = 24;
	static final int largura = 34;
	
	double x, y;
	double velx, vely;
	double gravidade;
	double velSalto;
	double angulo;
	
	boolean vivo;
	
	Hitbox colisao;
	TrocaAnimacao acao;
	Timer animacao;
	
	public Passaro(){
		this.x = 110 - largura/2;
		this.y = 260 - altura/2;
		this.angulo = 0.0;
		this.velx = 0.0;
		this.vely = 0.0;
		this.gravidade = 250;
		this.velSalto = -135;
		this.vivo = true;
		this.acao = new TrocaAnimacao(new int[] {528, 528, 446}, new int[] {128, 180, 248}, 3);
		this.animacao = new Timer(.075, true, this.acao);
		this.colisao = new Hitbox(this.x, this.y, this.x + largura, this.y + altura);
	}
	
	public double getY(){
		return this.y;
	}
	
	public int getAltura(){
		return altura;
	}
	
	public void mover(double dt){
		this.vely += this.gravidade*dt;
		this.y += this.vely*dt;
		
		this.angulo = 0.005*this.vely;
		if(this.angulo > Math.PI/2) this.angulo = Math.PI/2;
		
		this.colisao.mover(this.x, this.y, this.x + largura, this.y + altura);

	}
	
	
	public void desenhar(Tela t){
		t.imagem("flappy.png", this.acao.getX(), this.acao.getY(), largura, altura, this.angulo, this.x, this.y);
	}
}
