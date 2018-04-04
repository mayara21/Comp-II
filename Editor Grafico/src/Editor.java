/* Laboratorios 7 e 8 de Computacao II - Editor Grafico
 * 
 * 
 * Professor: Fabio Mascarenhas;
 * 
 * Aluna: Mayara Martins Poim Fernandes;
 * DRE: 116023936;
 * 
 * */


public class Editor extends GuiApp {
	
    double valorR, valorG, valorB;
    
    ModeloEditor modelo;  
    
    private Acao acaoRetangulo = new Acao() { public void executa() { modelo.retangulo(); } };
    private Acao acaoCirculo = new Acao() { public void executa() { modelo.circulo(); } };
    
    private Acao acaoTriangulo = new Acao() { public void executa() { modelo.triangulo(); } };
    
    private Acao acaoMover = new Acao() { public void executa() { modelo.mover(); } };
    private Acao acaoApagar = new Acao() { public void executa() { modelo.apagar(); } };
    private Acao acaoDesfazer = new Acao() { public void executa() { modelo.desfazer(); } };
    private Acao acaoRefazer = new Acao() { public void executa() { modelo.refazer(); } };
    
    private ObservadorCanvas obsCanvas = new ObservadorCanvas() {
        public void desenhar(final Canvas c) {
            modelo.cor = Cor.rgb(valorR, valorG, valorB);
            for(Figura f: modelo.figuras) {
            
                f.desenhar(new DesenhoFigura() {
                    public void desenhar(Retangulo ret) {
                        c.retangulo(ret.x, ret.y, ret.larg, ret.alt, ret.cor);
                    }
                    public void desenhar(Circulo circ) {
                        c.circulo(circ.x, circ.y, circ.raio, circ.cor);
                    }
                   public void desenhar(Triangulo triang){
                    	c.triangulo(triang.x, triang.y, triang.x2, triang.y2, triang.x3, triang.y3, triang.cor);
                    }
                });
            }
        }
        
        public void aperto(int x, int y) {
            modelo.inicio(x, y);
        }
    
        public void arrasto(int x, int y) {
            modelo.meio(x, y);
        }
    
        public void solta(int x, int y) {
            modelo.fim(x, y);
        }
    };
    
    
    public Editor() {
        super("Editor", 1000, 700);
        modelo = new ModeloEditor();
        componentes.add(new Canvas(300, 0, 500, 700, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), obsCanvas));
        
        componentes.add(new Slider(850, 50, 100, 100, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(1.0, 0.0, 0.0), 0.0, 
        		new ObservadorSlider(){
        			public void mudou(double valor){
        				valorR = valor;
        			}
        		}));
        componentes.add(new Slider(850, 180, 100, 100, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 1.0, 0.0), 0.0, 
        		new ObservadorSlider(){
        			public void mudou(double valor){
        				valorG = valor;
        			}
        		}));
        
        componentes.add(new Slider(850, 310, 100, 100, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 1.0), 0.0, 
        		new ObservadorSlider(){
        			public void mudou(double valor){
        				valorB = valor;
        			}
        		}));
        
        componentes.add(new CaixaCor(880, 500, 40, 40,
        		new CorCaixa(){
        			public double getR(){
        				return valorR;
        			}
        			
        			public double getG(){
        				return valorG;
        			}
        			public double getB(){
        				return valorB;
        			}
        		
        		}));
        
        componentes.add(new BotaoOnOff(20, 50, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Retangulo", acaoRetangulo, testaModo("ret")));
        componentes.add(new BotaoOnOff(20, 130, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Circulo", acaoCirculo, testaModo("circ")));
        componentes.add(new BotaoOnOff(20, 210, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Triangulo", acaoTriangulo, testaModo("triang")));
        
        componentes.add(new BotaoOnOff(20, 380, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Mover", acaoMover, testaModo("mov")));
        componentes.add(new BotaoOnOff(20, 460, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Apagar", acaoApagar, testaModo("apagar")));
        componentes.add(new Botao(20, 540, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Desfazer", acaoDesfazer));
        componentes.add(new Botao(20, 620, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Refazer", acaoRefazer));
   
    }

    private OnOff testaModo(String modo) {
        return new OnOff() {
            public boolean valor() {
                return modelo.modo.tag().equals(modo);
            }
        };
    }
    
    public void sair() {}
    
    public static void main(String[] args) {
        (new Editor()).run();
    }
}