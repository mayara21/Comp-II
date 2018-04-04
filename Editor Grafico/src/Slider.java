
public class Slider implements Componente {
	
	int x, y, larg, altslider;
	double yret;
	static int altret = 30;
	Cor frente, fundo;
	double valor;
	
	ObservadorSlider obsSlider;
	Acao acao;
	
	
	public Slider(int x, int y, int larg, int altslider, Cor frente, Cor fundo, double valor, ObservadorSlider obsSlider){
		this.x = x;
		this.y = y;
		this.larg = larg;
		this.altslider = altslider;
		this.frente = frente;
		this.fundo = fundo;
		this.valor = valor;
		
		
		this.yret = this.y + this.valor*(this.altslider-altret);
		this.obsSlider = obsSlider;
	}

	public boolean dentro(int x, int y) {
		return x >= this.x && x < this.x + larg && y >= this.y && y < this.y + altslider;
	}

	
	public void desenhar(Tela t) {
        t.retangulo(this.x, this.y, this.larg, this.altslider, this.frente);
		t.retangulo(this.x + 5, this.y + 5, this.larg - 10, this.altslider - 10, fundo);
        t.retangulo(this.x, this.yret, this.larg, altret, this.frente);

	}

	
	public void clique(int x, int y) {
		acao.executa();
	}

	
	public void aperto(int x, int y) {
		
	}

	
	public void arrasto(int x, int y) {
		if(y>this.y && y<(this.y + this.altslider - altret)){
			this.yret = y;
			this.valor = (double) ((this.yret - this.y)/(this.altslider-altret));
		}
		else{
			if(y<this.y){
				this.yret = this.y;
				this.valor = 0.0;
			}
			else{
				this.yret = this.y + this.altslider - altret;
				this.valor = 1.0;
			}
		}

		this.obsSlider.mudou(this.valor);
	}

	
	public void solta(int x, int y) {
		
	}

}
