
public class ModoApagar extends Modo{
	
	Figura foco;
	
	public ModoApagar(ModeloEditor modelo){
		super(modelo);
	}
	
	public void inicio(int x, int y){
		for(Figura f: modelo.figuras){
			if(f.dentro(x, y)) this.foco = f;
		}
	}
	
	public void meio(int x, int y){
		if(foco != null) modelo.figuras.remove(foco);
	}
	
	public void fim(int x, int y){
		super.fim(x, y);
		if(foco != null){
			modelo.feitos.push(new ComandoApagar(foco));
		}
		
	}
	
	public String tag(){
		return "apagar";
	}
	
	public void abortar() { }
	
}
