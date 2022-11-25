import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        TaTeTi Tateti = new TaTeTi();
        Tateti.Inicializar();

        Boolean MaquinaEsX = Tateti.Turno();
        if (MaquinaEsX) {
            System.out.println("La máquina es 'X' (jugador #1). Usted es 'O' (jugador #2).");
            Tateti.EmpiezaLaMaquina();
            System.out.println("La máquina inica jugando posición 5.");    
        } else System.out.println("Usted es 'X' (jugador #1). La máquina es 'O' (jugador #2).");
        
        Tateti.MostrarTablero();
        Boolean ganador = null; // true = maquina, false = humano, null = sigue/empate
        Scanner sc = new Scanner(System.in);
        do {
            int pos = ObtenerJugadaHumana(sc, Tateti);
            boolean ganaPersona = Tateti.Jugar(pos);
            
            if (ganaPersona) ganador = false;
            else if (Tateti.NoTermino()) {
                boolean ganaMaquina = Tateti.MaquinaJugar();
                if (ganaMaquina) ganador = true;
            }

            Tateti.MostrarTablero();
        } while(ganador == null && Tateti.NoTermino());
        sc.close();

        if (ganador == null) System.out.println("¡Ha habido un empate!");
        else if (ganador) System.out.println("Usted ha perdido. La máquina ha ganado.");
        else System.out.println("Usted ha ganado. Felicitaciones.");

        System.out.println("Juego finalizado.");
    }

    private static int ObtenerJugadaHumana(Scanner sc, TaTeTi Tateti) {
        System.out.println("Su turno."); 

        boolean posValida = false;
        int pos;
        do {
            System.out.print("Ingrese el número de la casilla a jugar: "); 
            pos = sc.nextInt();

            if (pos < 1 || pos > 9) {
                System.out.println(String.format("La posición %s no existe.", pos)); 
                continue;
            }
            
            if (Tateti.PosicionYaOcupada(pos)) {
                System.out.println(String.format("La posición %s ya se encuentra ocupada.", pos)); 
                continue;
            }
            
            posValida = true;
        } while(!posValida);

        return pos;
    }
}
