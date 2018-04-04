import java.util.ArrayList;
import java.util.Stack;

public class ModeloEditor {
    ArrayList<Figura> figuras = new ArrayList<Figura>();
    Modo modo = new ModoRetangulo(this);
    Stack<Comando> feitos = new Stack<Comando>();
    Stack<Comando> desfeitos = new Stack<Comando>();
    
    Cor cor;
        
    public void retangulo() {
    	modo.abortar();
        modo = new ModoRetangulo(this);
    }
    
    public void circulo() {
    	modo.abortar();
        modo = new ModoCirculo(this);
    }
    
    public void triangulo(){
    	modo.abortar();
    	modo = new ModoTriangulo1(this);
    }
    
    public void mover() {
    	modo.abortar();
        modo = new ModoMover(this);
    }
    
    public void apagar(){
    	modo.abortar();
    	modo = new ModoApagar(this);
    }
 
    
    public void desfazer() {
        if(!feitos.isEmpty()) {
            Comando cmd = feitos.pop();
            cmd.desfazer(this);
            desfeitos.push(cmd);
        }
    }

    public void refazer() {
        if(!desfeitos.isEmpty()) {
            Comando cmd = desfeitos.pop();
            cmd.refazer(this);
            feitos.push(cmd);
        }
    }

    public void inicio(int x, int y) {
        modo.inicio(x, y);
    }
    
    public void meio(int x, int y) {
        modo.meio(x, y);
    }
    
    public void fim(int x, int y) {
        modo.fim(x, y);
    }
}