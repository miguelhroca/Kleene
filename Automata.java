import java.util.Scanner;

public class Automata {
  private char[][][] matrix0;
  private char[][][] matrix1;
  private char[][][] matrix2;
  private boolean[] final0;
  private boolean[] final1;
  private boolean[] final2;

 public Automata() {
    initExamples();
  }

  public void initMatrix0() {
    //Matriz de ejemplo 0
    this.matrix0 = new char[3][3][2];
    this.matrix0[0][0][0] = 'a';
    this.matrix0[0][0][1] = 'Ɛ';
    this.matrix0[0][1][0] = 'b';
    this.matrix0[1][1][0] = 'b';
    this.matrix0[1][1][1] = 'Ɛ';
    this.matrix0[1][2][0] = 'a';
    this.matrix0[2][1][0] = 'a';
    this.matrix0[2][1][1] = 'b';
    this.matrix0[2][2][0] = 'Ɛ';
    this.final0 = new boolean[3];
    this.final0[1] = true;

  }

  public void initMatrix1() {
    //Matriz de ejemplo 1
    this.matrix1 = new char[7][7][2];
    this.matrix1[0][1][0] = 'a';
    this.matrix1[0][4][0] = 'c';
    this.matrix1[0][3][0] = 'b';
    this.matrix1[0][6][0] = 'd';
    this.matrix1[1][2][0] = 'b';
    this.matrix1[2][2][0] = 'b';
    this.matrix1[2][6][0] = 'c';
    this.matrix1[2][6][1] = 'd';
    this.matrix1[3][4][0] = 'a';
    this.matrix1[4][0][0] = 'a';
    this.matrix1[4][5][0] = 'c';
    this.matrix1[5][6][0] = 'b';

    this.final1 = new boolean[7];
    this.final1[6] = true;
  }

  public void initMatrix2() {
      this.matrix2 = new char[8][8][2];
      this.matrix2[0][0][0] = 'Ɛ';
      this.matrix2[0][0][1] = 'b';
      this.matrix2[0][1][0] = 'a';
      this.matrix2[1][0][0] = 'b';
      this.matrix2[1][2][0] = 'a';
      this.matrix2[2][2][0] = 'a';
      this.matrix2[2][3][0] = 'b';
      this.matrix2[3][0][0] = 'b';
      this.matrix2[3][4][0] = 'a';
      this.matrix2[4][4][0] = 'Ɛ';
      this.matrix2[4][4][1] = 'a';
      this.matrix2[4][5][0] = 'b';
      this.matrix2[5][5][0] = 'Ɛ';
      this.matrix2[5][4][0] = 'a';
      this.matrix2[5][6][0] = 'b';
      this.matrix2[6][6][0] = 'Ɛ';
      this.matrix2[6][6][1] = 'b';
      this.matrix2[6][7][0] = 'a';
      this.matrix2[7][7][0] = 'Ɛ';
      this.matrix2[7][6][0] = 'b';
      this.matrix2[7][4][0] = 'a';
      
      this.final2 = new boolean[8];
      this.final2[4] = true;
      this.final2[5] = true;
      this.final2[6] = true;
      this.final2[7] = true;
  }

  public void initExamples() {
    initMatrix0();
    initMatrix1();
    initMatrix2();
  }

  public char[][][] getExample0() {
    return this.matrix0;
  }

  public char[][][] getExample1() {
    return this.matrix1;
  }

  public char[][][] getExample2() {
    return this.matrix2;
  }
  public boolean[] getExample0f() { 
    return this.final0;
  }
  public boolean[] getExample1f() {
    return this.final1;
  }
  public boolean[] getExample2f() {
    return this.final2;
  }

  //To-do: testear
  public char[][][] inputAutomata() {
	  Scanner xx = new Scanner (System.in);
  	int numEstados, simb,k,estadosFinales;
  	String respuesta = "";
    char caracter = '0';
  	
  	System.out.print("\n¿Cuántos estados tiene el autómata?: ");
  	numEstados = xx.nextInt();
  	System.out.print("¿Cuántas palabras tiene el lenguaje del autómata?: ");
  	simb = xx.nextInt();
    System.out.print("¿Cuanto estados finales tiene?");
    estadosFinales = xx.nextInt();
    
    
    boolean[] finales = new boolean[estadosFinales];
  	char[][][] matriz = new char[numEstados][numEstados][++simb];

  	for(int i = 0 ; i < numEstados; i++) {
      k = 0;
      System.out.print("¿El estado " + i + " es inicial/final? (s/n): ");
      respuesta = xx.next();
      if(respuesta.equals("s")) {
          matriz[i][i][k] = 'Ɛ'; 
          k++;
      }
      
      System.out.print("¿El estado " + i + "es final?");
      respuesta = xx.next();
        if(respuesta.equals("s")) {
          finales[i]=true;
      }
      
  		for(int j = 0 ; j < numEstados; j++) {
  			System.out.print("¿Transita del estado "+ i +" al estado " + j + " ? (s/n): ");    

      	respuesta = xx.next();

  			if(respuesta.equals("s")) {
          while(k < simb && respuesta.equals("s")) {
            System.out.print("¿Con que palabra transita?: ");
            caracter = xx.next().charAt(0);
            matriz[i][j][k] = caracter; 

            k++;

            if (k < simb) {
              System.out.print("¿Puede transitar con más? (s/n): ");
              respuesta = xx.next();
            }       
          }		
  			}

  		}
  	}
	  return matriz;
  }
}