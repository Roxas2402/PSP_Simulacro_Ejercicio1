import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String frase;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la frase: ");
        frase = sc.nextLine();

        ProcessBuilder proceso = new ProcessBuilder();

        //proceso = new ProcessBuilder()

        try {


            proceso = new ProcessBuilder("java", "-jar", "src/Separacion.jar");
            pasarFrase(proceso, frase);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void pasarFrase(ProcessBuilder proceso, String frase) throws IOException {
        proceso.redirectErrorStream(true);
        Process hijo = proceso.start();

        OutputStream outputStream = hijo.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = hijo.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);

        printStream.println(frase);
        printStream.flush();

        String palabra;
        while (!(palabra = br.readLine()).equalsIgnoreCase("fin")) {
            System.out.println(palabra);
        }
    }
}