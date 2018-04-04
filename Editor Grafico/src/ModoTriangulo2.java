public class ModoTriangulo2 extends Modo{
	
	Ponto ponto;
	Ponto p1 = new Ponto(0, 0, Cor.rgb(1.0, 1.0, 1.0));
	
	public ModoTriangulo2(ModeloEditor modelo){
		super(modelo);
	}

	public void inicio(int x, int y){

	}
	
	public void meio(int x, int y){
	
	}
	
	public void fim(int x, int y){
		ponto = new Ponto(x, y, modelo.cor);
		modelo.figuras.add(ponto);
		modelo.feitos.push(new ComandoFigura(ponto));
		modelo.modo = new ModoTriangulo3(modelo);
	}
	
	public String tag(){
		return "triang";
	}
	
	public void abortar(){
		for(Figura fig: modelo.figuras){
			if(fig instanceof Ponto){
				p1 = (Ponto) fig;
				break;
			}
		}
		modelo.figuras.remove(p1);
	}
}
