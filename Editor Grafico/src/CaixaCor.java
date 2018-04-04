
public class CaixaCor implements CorCaixa, Componente{
	int x, y, larg, alt;
	double r, g, b;
	CorCaixa cc;
	
	public CaixaCor(int x, int y, int larg, int alt, CorCaixa cc){
		this.x = x;
		this.y = y;
		this.larg = larg;
		this.alt = alt;
		this.cc = cc;
		}
	
	public double getR(){
		return cc.getR();
	}
	
	public double getG(){
		return cc.getG();
	}
	
	public double getB(){
		return cc.getB();
	}

	public boolean dentro(int x, int y) {
		return false;
	}

	public void desenhar(Tela t) {
		t.retangulo(this.x, this.y, this.larg, this.alt, Cor.rgb(this.getR(), this.getG(), this.getB()));
	}

	public void clique(int x, int y){ }
	public void aperto(int x, int y){ }
	public void arrasto(int x, int y){ }
	public void solta(int x, int y) { }
}
