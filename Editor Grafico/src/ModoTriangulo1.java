
public class ModoTriangulo1 extends Modo{
	
	Ponto ponto;

	public ModoTriangulo1(ModeloEditor modelo){
		super(modelo);
	}

	public void inicio(int x, int y){ }
	
	public void meio(int x, int y){ }
	
	public void fim(int x, int y){
		ponto = new Ponto(x, y, modelo.cor);
		modelo.figuras.add(ponto);
		modelo.feitos.push(new ComandoFigura(ponto));
		modelo.modo = new ModoTriangulo2(modelo);
	}
	
	public String tag(){
		return "triang";
	}
	
	public void abortar(){ }
}
