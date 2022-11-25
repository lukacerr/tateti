import java.util.Random;

public class TaTeTi {
  private int Jugadas;
  private Boolean MaquinaEsX;
  private Boolean[] Tablero;

  // public TaTeTi() {}
  public void Inicializar() {
    Jugadas = 0;

    /*
     * null = espacio desocupado
     * true = maquina
     * false = human@
     */
    Tablero = new Boolean[]{ 
      null, null, null,
      null, null, null,
      null, null, null,
     };
  }

  public Boolean Turno() { return MaquinaEsX = (new Random()).nextBoolean(); }
  
  public void EmpiezaLaMaquina() { Tablero[4] = true; Jugadas++;  }

  public boolean PosicionYaOcupada(int pos) { return Tablero[pos - 1] != null; }

  public boolean Jugar(int pos) {
    pos--;

    boolean esGanador = Jugadas > 4 && EsJugadaGanadora(pos, false);
    Tablero[pos] = false;
    Jugadas++;

    return esGanador;
  }

  public boolean MaquinaJugar() {
    Jugadas++;
    if (Jugadas > 3) {
      int i = 0;

      do {
        if (Tablero[i] == null && EsJugadaGanadora(i, true)) {
          Tablero[i] = true;
          break;
        }

        i++;
      } while (i < 9);
      if (i < 9) return true;

      i = 0;
      do {
        if (Tablero[i] == null && EsJugadaGanadora(i, false)) {
          Tablero[i] = true;
          break;
        }

        i++;
      } while (i < 9);
      if (i < 9) return false;
    } 
    
    if (Tablero[4] == null) Tablero[4] = true;
    else if (Jugadas < 7) {
      if (Tablero[0] == null) Tablero[0] = true;
      else if (Tablero[2] == null) Tablero[2] = true;
      else if (Tablero[6] == null) Tablero[6] = true;
      else if (Tablero[8] == null) Tablero[8] = true;
      else if (Tablero[1] == null) Tablero[1] = true;
      else if (Tablero[3] == null) Tablero[3] = true;
      else if (Tablero[7] == null) Tablero[7] = true;
      else if (Tablero[5] == null) Tablero[5] = true;
    } 
    else if (Tablero[1] == null) Tablero[1] = true;
    else if (Tablero[3] == null) Tablero[3] = true;
    else if (Tablero[7] == null) Tablero[7] = true;
    else if (Tablero[5] == null) Tablero[5] = true;
    else if (Tablero[2] == null) Tablero[2] = true;
    else if (Tablero[6] == null) Tablero[6] = true;
    else if (Tablero[8] == null) Tablero[8] = true;
    
    return false;
  }

  private boolean EsJugadaGanadora(int pos, Boolean esDeMaquina) {
    if (pos % 2 == 0 && (
      (Tablero[0] == esDeMaquina && Tablero[4] == esDeMaquina && pos == 8) ||
      (Tablero[0] == esDeMaquina && pos == 4 && Tablero[8] == esDeMaquina) ||
      (pos == 0 && Tablero[4] == esDeMaquina && Tablero[8] == esDeMaquina) || 
      (Tablero[2] == esDeMaquina && Tablero[4] == esDeMaquina && pos == 6) ||
      (Tablero[2] == esDeMaquina && pos == 4 && Tablero[6] == esDeMaquina) ||
      (pos == 2 && Tablero[4] == esDeMaquina && Tablero[6] == esDeMaquina)
    )) return true;

    if (pos > 5 && (
      (Tablero[pos - 3] == esDeMaquina && Tablero[pos - 6] == esDeMaquina) ||
      (Tablero[6] == esDeMaquina && Tablero[7] == esDeMaquina && pos == 8) ||
      (Tablero[6] == esDeMaquina && pos == 7 && Tablero[8] == esDeMaquina) ||
      (pos == 6 && Tablero[7] == esDeMaquina && Tablero[8] == esDeMaquina) 
    )) return true;

    else if (pos <= 5 && pos > 2 && (
      (Tablero[pos - 3] == esDeMaquina && Tablero[pos + 3] == esDeMaquina) ||
      (Tablero[3] == esDeMaquina && Tablero[4] == esDeMaquina && pos == 5) ||
      (Tablero[3] == esDeMaquina && pos == 4 && Tablero[5] == esDeMaquina) ||
      (pos == 3 && Tablero[4] == esDeMaquina && Tablero[5] == esDeMaquina) 
    )) return true;
    
    else if (pos <= 2 && (
      (Tablero[pos + 3] == esDeMaquina && Tablero[pos + 6] == esDeMaquina) ||
      (Tablero[0] == esDeMaquina && Tablero[1] == esDeMaquina && pos == 2) ||
      (Tablero[0] == esDeMaquina && pos == 1 && Tablero[2] == esDeMaquina) ||
      (pos == 0 && Tablero[1] == esDeMaquina && Tablero[2] == esDeMaquina) 
    )) return true;

    return false;
  }

  public boolean NoTermino() { return Jugadas < 9; }

  private String[] ObtenerTableroLegible() {
    String[] tableroLegible = new String[9];
    for (int i = 0; i < 9; i++)
      tableroLegible[i] = Tablero[i] == null 
        ? Integer.toString(i+1) : Tablero[i]
          ? MaquinaEsX ? "X" : "O"
          : MaquinaEsX ? "O" : "X";

    return tableroLegible;
  }

  public void MostrarTablero() {
    String[] tablero = ObtenerTableroLegible();
    System.out.println(String.format("%s | %s | %s", tablero[0], tablero[1], tablero[2]));
    System.out.println("- - - - -");
    System.out.println(String.format("%s | %s | %s", tablero[3], tablero[4], tablero[5]));
    System.out.println("- - - - -");
    System.out.println(String.format("%s | %s | %s", tablero[6], tablero[7], tablero[8]));
  }
}
