package org.example;

import data.Book;
import data.Element;
import data.Magazine;
import data.Periodicity;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static int isbn = 6;

    static File fileArchivio = new File("archive.txt");

    static Scanner scanner = new Scanner(System.in);

    static Logger logger = LoggerFactory.getLogger(Main.class);

    static List<Element> archive = new ArrayList<Element>();
    static List<Book>bookList = new ArrayList<Book>();
    static List<Magazine>magazineList = new ArrayList<Magazine>();

    public static void main(String[] args) {
        /*
         TODO Auto-generated method stub
        creo un file dell'Archivio
        */

        try {
            fileArchivio.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //Aggiungo qualche elemento alle liste libri/riviste
        Book libro0 = new Book("000", "Il vecchio che leggeva romanzi d'amore", LocalDate.parse("1989-03-04"), 230, "Luis Sepúlveda", "Narrativa");
        Book libro1 = new Book("001", "Storia di una gabbianella e del gatto che le insegnò a volare", LocalDate.parse("1988-10-01"), 100, "Luis Sepúlveda", "Narrativa");
        Book libro2 = new Book("002", "Il maestro e Margherita", LocalDate.parse("1967-10-12"), 120, "Michail Afanas'evič Bulgakov", "Narrativa fantasy");

        Magazine riv1 = new Magazine("004", "Vogue", LocalDate.parse("1892-08-10"), 90, Periodicity.MONTHLY);
        Magazine riv2 = new Magazine("005", "Art-attack", LocalDate.parse("2000-12-10"), 50, Periodicity.WEEKLY);
        Magazine riv3 = new Magazine("006", "Rivista dei gatti", LocalDate.parse("2020-11-05"), 80, Periodicity.SIX_MONTHLY);

        bookList.add(libro0);
        bookList.add(libro1);
        bookList.add(libro2);

        magazineList.add(riv1);
        magazineList.add(riv2);
        magazineList.add(riv3);

        //Archivio sarà una lista formata dall'unione di lista libri e lista riviste
        archive = Stream.concat(bookList.stream(), magazineList.stream()).collect(Collectors.toList());
        System.out.println(archive);

        //MENU
        int start = 1;

        do {
            System.out.printf("Premi un numero : %n"
                    + "1 - per aggiungere un elemento al catalogo %n"
                    + "2 - per rimuovere un elemento tramite ISBN %n"
                    + "3 - per trovare un articolo con ISBN %n"
                    + "4 - per trovare un articolo tramite anno %n"
                    + "5 - per trovare un libro tramite autore %n"
                    + "6 - per scrivere il file sul disco %n"
                    + "7 - per leggere il file dal disco %n"
                    + "8 - per cancellare il file %n"
                    + "0 - per terminare il programma %n");
            try {
                start = Integer.parseInt(scanner.nextLine());

                switch (start) {
                    case 1:
                        boolean choose = true;
                        while(choose) {
                            System.out.printf("Scegli tra 1-Rivista  2-Libro: " );
                            int res1 = Integer.parseInt(scanner.nextLine());

                            if(res1 == 1) {
                                addNewElement(magazineList);
                                choose = false;
                            }else if(res1 == 2) {
                                addNewElement(bookList);
                                choose = false;
                            }else {
                                System.out.println("numero digitato incorretto!");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Digita ISBN: " );
                        String res2 = scanner.nextLine();
                        removeByIsbn(res2);
                        break;
                    case 3:
                        System.out.println("Digita ISBN: " );
                        String res3 = scanner.nextLine();
                        searchByIsbn(res3);
                        break;
                    case 4:
                        System.out.println("Digita anno, mese e giorno(Y-M-D): " );
                        String res4 = scanner.nextLine();
                        searchByYear(LocalDate.parse(res4));
                        break;
                    case 5:
                        System.out.println("Digita autore: " );
                        String res5 = scanner.nextLine();
                        searchByAuthor(res5);
                        break;
                    case 6:
                        try {
                            writeFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            readFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case 8:
                        deleteFile();
                }

            }catch(NumberFormatException e) {
                logger.info("Puoi digitare solo numeri");
            }
        } while (start != 0);

        logger.info("Hai chiuso il programma");
    }


    //METODI
    //Features richieste

    //Creo il metodo per aggiungere un nuovo elemento
    public static void addNewElement(@org.jetbrains.annotations.NotNull List<?> unknown) {

        System.out.println("Inserisci le informazioni richieste ");

        if( unknown.get(0).getClass() == Book.class) {
            isbn++;
            String newIsbn = createIsbn();

            System.out.println("Titolo: ");
            String title = scanner.nextLine();

            System.out.println("Anno: ");
            LocalDate year = (LocalDate.ofYearDay(Integer.parseInt(scanner.nextLine()), 1));

            System.out.println("Numero di pagine: ");
            int numberOfPages = Integer.parseInt(scanner.nextLine());

            System.out.println("Autore: ");
            String author = scanner.nextLine();

            System.out.println("Genere: ");
            String genre = scanner.nextLine();

            archive.add(new Book(newIsbn, title, year, numberOfPages, author, genre));
            logger.info("Libro aggiunto");
            logger.info("Dettagli ibro aggiunto: " + archive.get((archive.size())-1).toString());


        }else if(unknown.get(0).getClass() == Magazine.class) {
            String isbn = createIsbn();

            System.out.println("Titolo: ");
            String title = scanner.nextLine();

            System.out.println("Anno: ");
            LocalDate year = (LocalDate.ofYearDay(Integer.parseInt(scanner.nextLine()), 1));

            System.out.println("Numero di pagine: ");
            int numberOfYear = Integer.parseInt(scanner.nextLine());

            System.out.println(
                    "Periodicita della rivista:" + " 1 - Settimanale" + " 2 - Mensile" + " 3 - Semestrale");
            int res = Integer.parseInt(scanner.nextLine());
            Periodicity periodicity = null;

            switch (res) {
                case 1:
                    periodicity = Periodicity.WEEKLY;
                    break;
                case 2:
                    periodicity = Periodicity.MONTHLY;
                    break;
                case 3:
                    periodicity = Periodicity.SIX_MONTHLY;
                    break;
            }

            archive.add(new Magazine(isbn, title, year, numberOfYear, periodicity));
            logger.info("Rivista aggiunta");
        }else {
            logger.info("Errore");
        }
    }

    //Rimozione di un elemento da ISBN
    public static void removeByIsbn(String isbn) {
        archive.removeIf(el-> el.getIsbn().equals(isbn));
        logger.info("Elemento rimosso tramite ISBN: " + isbn);
    }

    //Ricerca per ISBN
    public static void searchByIsbn(String isbn) {
        Stream<Element> libro  = archive.stream().filter(el-> el.getIsbn().equals(isbn));
        libro.forEach(el-> logger.info("Libro con ISBN " + isbn + ": " + el.toString()));
    }

    //Ricerca x anno di pubblicazione
    public static void searchByYear(LocalDate anno) {
        Stream<Element> libro  = archive.stream().filter(el-> el.getYearOfPublication().equals(anno));
        libro.forEach(el-> logger.info("Libro pubblicato nel " + anno + ": " + el.toString()));
    }

    //Ricerca x autore
    public static void searchByAuthor(String autore) {
        Stream<Book> libroAutore =
                archive
                        .stream()
                        .filter(el -> el instanceof Book).map(e -> (Book) e)
                        .filter(el -> el.getAuthor().equals(autore));
        libroAutore
                .forEach(el -> logger.info("Libri dell'autore " + autore + ": " + el.toString()));
    }

    //Salvataggio su disco(scrivi)
    public static void writeFile() throws IOException {
        archive.forEach(el-> {
            try {
                FileUtils.writeStringToFile(fileArchivio, el.toString(), "UTF-8",true);
                logger.info("Elemento salvato nel file con successo");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

    //Caricamento dal disco(leggi)
    public static void readFile() throws IOException {
        String txtFile = FileUtils.readFileToString(fileArchivio, "UTF-8");
        String[] obj = txtFile.split("#");
        for (int i = 0; i < obj.length; i++) {
            logger.info(obj[i]);
        }
    }

    //Features extra

    //crea isbn
    public static @NotNull String createIsbn(){
        double d = isbn + Math.random()*Double.MAX_VALUE ;
        String s =String.valueOf(d);
        return s;
    }

    //elimina file
    public static void deleteFile() {
        FileUtils.deleteQuietly(fileArchivio);
        System.out.println("File eliminato");
    }
}