
public class ComandoApagar implements Comando{
	Figura fig, temp;
	
	public ComandoApagar(Figura fig){
		this.fig = fig;
	}
	
	public void desfazer(ModeloEditor modelo){
		modelo.figuras.add(fig);
	}
	
	public void refazer(ModeloEditor modelo){
		modelo.figuras.remove(fig);
	}

}
