import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kleene {
	public String transform(char[][][] matriz, boolean[] vectorEF) {
		int i, j, k, i0, j0, iN, jN;

		i0 = j0 = 0;
		iN = jN = matriz.length;

		String[][][] matrizXunga = new String[iN + 1][jN + 1][jN + 1];
    String res = "";

		for (int b = 0; b < iN; b++)
			for (int d = 0; d < jN; d++)
				for (int x = 0; x < jN; x++)
					matrizXunga[b][d][x] = "";

    //Cálculo inicial (k = 0)
    System.out.println("\nCalculo inicial:");
		for (i = i0; i < iN; i++) {
			for (j = j0; j < jN; j++) {
				for (int t = 0; t < matriz[i][j].length; t++) {
					if (matriz[i][j][t] != 0) {
						if (t > 0 && !matrizXunga[i][j][0].equals(""))
							matrizXunga[i][j][0] += "|";

						matrizXunga[i][j][0] += Character.toString(matriz[i][j][t]);

					} else if (t > 0 && matrizXunga[i][j][0].equals(""))
						matrizXunga[i][j][0] = "0";
				}
			}
		}

		//Log cálculo inicial
		for (i = i0; i < iN; i++) {
			for (j = j0; j < jN; j++) {
				System.out.println("State (" + i + ", " + j + ") -> " + matrizXunga[i][j][0]);
			}
		}

		//Cálculo paso a paso para k > 0
    for (k = j0; k < jN; k++) {
      for (i = i0; i < iN; i++) {
        for (j = j0; j < jN; j++) {
            matrizXunga[i][j][k + 1] = matrizXunga[i][j][k] + "|" + matrizXunga[i][k + 1][k]
                + matrizXunga[k + 1][k + 1][k] + "*" + matrizXunga[k + 1][j][k];
            }
        }
			}

		//Log cálculo paso a paso
		for (k = j0; k < jN; k++) {
			System.out.println("\n\nPaso " + k + ": ");
			for (i = i0; i < iN; i++) {
				for (j = j0; j < jN; j++) {
					System.out.println("Estado (" + i + ", " + j + ") -> " + matrizXunga[i][j][k]);
				}
      }
		}

    //Cálculo de la ER final
    for(i = i0; i < iN; i++)
      if (vectorEF[i])
        res += matrizXunga[0][i][jN];

    return res;
	}

  public String simplify(String expression) {
    System.out.println("\n\nSimplificando...");
    String output = expression;
    Pattern p = Pattern.compile("(\\|?Ɛ\\*?\\|?)");
    Matcher m = null;

    do {
      m = p.matcher(output);
      output = m.replaceAll("|");
    } while(m.find());
    
    p = Pattern.compile("(\\|?0\\|?)+");
    
    do {
      m = p.matcher(output);
      output = m.replaceAll("|");
    } while(m.find());

    p = Pattern.compile("[a-z]+0[a-z]*\\|");
    
    do {
      m = p.matcher(output);
      output = m.replaceAll("|");
    } while(m.find());

    p = Pattern.compile("\\|([a-z]+)\\|\\1+");

    do {
      m = p.matcher(output);
      output = m.replaceAll("|$1");
    } while(m.find());

    return output;
  }
}