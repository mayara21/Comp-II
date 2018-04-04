
public class Triangulo extends Figura{
	int x2, y2, x3, y3;
	
	Vetor v1, v2, v3, ponto;
	
	public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3, Cor cor){
		super(x1, y1);
		
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.cor = cor;
	}
	
	public void desenhar(DesenhoFigura df){
		df.desenhar(this);
	}
	
	public boolean dentro(int x, int y){
		ponto = new Vetor(x, y);
		v1 = new Vetor(this.x, this.y);
		v2 = new Vetor(this.x2, this.y2);
		v3 = new Vetor(this.x3, this.y3);
		
		return ponto.PontoNoTriangulo(this.v1, this.v2, this.v3);
	}
	
	public void mover(int dx, int dy){
		this.x += dx;
		this.y += dy;
		this.x2 += dx;
		this.y2 += dy;
		this.x3 += dx;
		this.y3 += dy;
	}
}
