
public class Vetor {
	private int x, y;
	    
	public Vetor() {
		this.x = 0;
		this.y = 0;
	}
	    
	public Vetor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static int ProdutoVetorial(Vetor v1, Vetor v2) {
		return v1.x*v2.y - v1.y*v2.x;
	}
	
	public static Vetor Diferenca(Vetor v1, Vetor v2){
		Vetor res = new Vetor();
		res.x = v1.x - v2.x;
		res.y = v1.y - v2.y;
		return res;
	}
	    
	public boolean MesmoLado(Vetor p2, Vetor a, Vetor b) {
		int cp1 = ProdutoVetorial(Diferenca(b, a), Diferenca(this, a));
	    int cp2 = ProdutoVetorial(Diferenca(b, a), Diferenca(p2, a));
	    if (cp1*cp2 >= 0)
	    	return true;
	    else return false;
	}

	public boolean PontoNoTriangulo(Vetor a, Vetor b, Vetor c) {
		if (this.MesmoLado(a, b, c) && this.MesmoLado(b, a, c) && this.MesmoLado(c, a, b)) return true;
		else return false;
	}
}
