
public class ModoTriangulo3 extends Modo{
	
	Triangulo triang;
	Ponto p1 = new Ponto(0, 0, Cor.rgb(1.0, 1.0, 1.0));
	Ponto p2 = new Ponto(0, 0, Cor.rgb(1.0, 1.0, 1.0));
	boolean primeiro = true;
	boolean pronto = false;
	
	public ModoTriangulo3(ModeloEditor modelo){
		super(modelo);
	}

	public void inicio(int x, int y){

	}
	
	public void meio(int x, int y){
	
	}
	
	public void fim(int x, int y){
		
		for(Figura fig: modelo.figuras){
			if(fig instanceof Ponto){
				if(this.primeiro){
					p1 = (Ponto) fig;
					this.primeiro = false;
				}
					
				else{
					p2 = (Ponto) fig;
					this.pronto = true;
				}
			}
		}
	
		if(this.pronto){
			triang = new Triangulo(p1.x, p1.y, p2.x, p2.y, x, y, modelo.cor);
			modelo.feitos.push(new ComandoFigura(triang));

			modelo.figuras.add(triang);
			modelo.figuras.remove(p1);
			modelo.figuras.remove(p2);
		}
		
		modelo.modo = new ModoTriangulo1(modelo);
		
	}
	
	public String tag(){
		return "triang";
	}
	
	public void abortar(){
		
		for(Figura fig: modelo.figuras){
			if(fig instanceof Ponto){
				if(this.primeiro){
					p1 = (Ponto) fig;
					this.primeiro = false;
				}
				else{
					p2 = (Ponto) fig;
				}
			}
		}
		
		modelo.figuras.remove(p1);
		modelo.figuras.remove(p2);
	
	}
}