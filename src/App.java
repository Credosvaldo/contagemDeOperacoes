import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static int size = 500;

    public static void main(String[] args) throws Exception {
        System.out.println("Calculando  de execução: ");

        File file = new File("result.txt");
        
        if(file.exists()) {
            file.delete();
        }

        long[] ta = tempoMedio(A);
        long[] tb = tempoMedio(B);

        

    }

    public static Funcao A = (n) -> {
        int b = 1;

        // n+1/2 cima
        for (int i = 0; i <= n; i += 2) {
            b *= 3;
        }
    };

    public static Funcao B = (n) -> {
        int a = 1;

        // log(n + 1) + 1 pra baixo
        for (int i = n + 1; i > 0; i /= 2) {
            a *= 2;
        }
    };

    public static long contadorA(int n) {
        long contador = 0;
        int b = 1;

        // n+1/2 cima
        for (int i = 0; i <= n; i += 2) {
            b *= 3;
            contador += 2;
        }

        return contador;
    }

    public static long contadorB(int n) {
        long contador = 0;
        int a = 1;

        // log(n + 1) + 1 pra baixo
        for (int i = n + 1; i > 0; i /= 2) {
            a *= 2;
            contador += 2;
        }

        return contador;

    }

    public static long[] tempoMedio(Funcao func) {
        long[] tempoMedio = new long[size];

        for (int i = 0; i < tempoMedio.length; i++) {
            long[] aux = new long[5];
            tempoMedio[i] = 0;

            for (int j = 0; j < aux.length; j++) {
                long start = System.nanoTime();
                func.executar(i);
                aux[j] = System.nanoTime() - start;
            }

            //imprimirVetor(aux);

            sort(aux);

            for (int j = 1; j < aux.length - 1; j++) {
                tempoMedio[i] += aux[j];
            }

            tempoMedio[i] /= aux.length;

        }

        escreverVetor(tempoMedio);

        return tempoMedio;
    }

    public static long[] contador(Contador cont) {
        long[] result = new long[size];

        for(int i = 0; i<size; i++) {
            result[i] = cont.executar(i * 1000);
        }

        return result;
    }

    public static void sort(long[] v) {
        int posMenor;
        boolean trocar;

        for (int i = 0; i < v.length - 1; i++) {
            posMenor = i;
            trocar = false;

            for (int j = i + 1; j < v.length; j++) {
                if (v[posMenor] > v[j]) {
                    posMenor = j;
                    trocar = true;
                }
            }

            if (trocar) {
                long axu = v[i];
                v[i] = v[posMenor];
                v[posMenor] = axu;
            }

        }
    }

    public static void imprimirVetor(long[] v) {
        for(int i = 0; i<v.length; i++ ) {
            System.out.print(v[i] + ", ");
        }
        System.out.println("");
    }

    public static void escreverVetor(long [] v) {
        File file = new File("result.txt");
        String result = "";
System.out.println(v.length);
        for(int i = 0; i<v.length; i++) {
            result += v[i] + ", ";
        }

        System.out.println("O vetor que recebi foi: ");
        imprimirVetor(v);

        try {
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("Criando arquivo");
            }
            FileWriter writer = new FileWriter(file, true);
            System.out.println("Escrevendo");
            writer.append(result + "\n");
            writer.close();

        }
        catch(IOException e) {
            System.out.println(e);
        }

    }
}

interface Funcao {
    void executar(int n);

}

interface Contador {
    long executar(int n);
    
}