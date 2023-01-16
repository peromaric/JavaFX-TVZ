package tvz.entiteti;

import tvz.entiteti.enumeracije.Ocjena;
import tvz.entiteti.enumeracije.TipObrazovneUstanove;
import tvz.entiteti.zapisi.Dvorana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Citac {
    static final String PROFESORI_PATH = "dat/profesori.txt";
    static final String OBRAZOVNE_PATH = "dat/obrazovneUstanove.txt";
    static final String PREDMETI_PATH = "dat/predmeti.txt";
    static final String STUDENTI_PATH = "dat/studenti.txt";
    static final String ISPITI_PATH = "dat/ispiti.txt";

    public static List<Profesor> dodajProfesore() {
        List<Profesor> profesori = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(PROFESORI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            String sifra = "";
            String ime = "";
            String prezime = "";
            String titula = "";
            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> sifra = line;
                    case 3 -> ime = line;
                    case 4 -> prezime = line;
                    case 5 -> {
                        titula = line;
                        profesori.add(new Profesor(id, ime, prezime, sifra, titula));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return profesori;
    }

    public static List<Student> dodajStudente() {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(STUDENTI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            String jmbag = "";
            String ime = "";
            String prezime = "";
            LocalDate datumRodjenja = LocalDate.EPOCH;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> jmbag = line;
                    case 3 -> ime = line;
                    case 4 -> prezime = line;
                    case 5 -> {
                        datumRodjenja = LocalDate.parse(line, dateTimeFormatter);
                        studenti.add(new Student(id, ime, prezime, jmbag, datumRodjenja));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return studenti;
    }

    public static List<Predmet> dodajPredmete(List<Profesor> profesori, List<Student> studenti) {
        List<Predmet> predmeti = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(PREDMETI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            String sifra = "";
            String naziv = "";
            int brojEctsBodova = 0;
            Profesor nositelj = new Profesor(Long.MIN_VALUE, "", "", "", "");
            while ((line = in.readLine()) != null) {
                List<Student> studentiPredmeta = new ArrayList<>();
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> sifra = line;
                    case 3 -> naziv = line;
                    case 4 -> brojEctsBodova = Integer.parseInt(line);
                    case 5 -> {
                        int idNositelja = Integer.parseInt(line);
                        nositelj = profesori.stream()
                                .filter(prof -> prof.getId() == idNositelja)
                                .findFirst().orElseThrow();
                    }
                    case 6 -> {
                        List<Long> studentIdList = Arrays.stream(line.split(" "))
                                .map(Long::parseLong)
                                .toList();

                        studenti.stream()
                                .filter(student -> studentIdList.contains(student.getId()))
                                .forEach(studentiPredmeta::add);
                        predmeti.add(new Predmet(id, sifra, naziv, brojEctsBodova, nositelj, studentiPredmeta));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return predmeti;
    }

    public static List<Ispit> dodajIspite(List<Predmet> predmeti, List<Student> studenti) {
        List<Ispit> ispiti = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(ISPITI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            Predmet predmet = new Predmet((long) -1);
            Student student = new Student((long) -1);
            Ocjena ocjena = Ocjena.JEDAN;
            LocalDateTime datumIVrijeme = LocalDateTime.MIN;
            String dvoranaNaziv = "";
            String dvoranaZgrada = "";

            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> {
                        int idPredmeta = Integer.parseInt(line);
                        predmet = predmeti.stream()
                                .filter(pred -> pred.getId() == idPredmeta)
                                .findFirst().orElseThrow();
                    }
                    case 3 -> {
                        int idStudenta = Integer.parseInt(line);
                        student = studenti.stream()
                                .filter(stud -> stud.getId() == idStudenta)
                                .findFirst().orElseThrow();
                    }
                    case 4 -> {
                        int ocjenaInt = Integer.parseInt(line);
                        ocjena = Ocjena.parseInt(ocjenaInt);
                    }
                    case 5 -> {
                        datumIVrijeme = LocalDateTime.parse(line, DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
                    }
                    case 6 -> dvoranaNaziv = line;
                    case 7 -> {
                        dvoranaZgrada = line;
                        ispiti.add(new Ispit(
                                id,
                                predmet,
                                student,
                                ocjena,
                                datumIVrijeme,
                                new Dvorana(dvoranaNaziv, dvoranaZgrada)
                        ));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return ispiti;
    }

    public static Sveuciliste<ObrazovnaUstanova> dodajObrazovneUstanove() {
        Sveuciliste<ObrazovnaUstanova> sveuciliste = new Sveuciliste<>();

        List<Profesor> sviProfesori = dodajProfesore();
        List<Student> sviStudenti = dodajStudente();
        List<Predmet> sviPredmeti = dodajPredmete(sviProfesori, sviStudenti);
        List<Ispit> sviIspiti = dodajIspite(sviPredmeti, sviStudenti);


        try (BufferedReader in = new BufferedReader(new FileReader(OBRAZOVNE_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            TipObrazovneUstanove tip = TipObrazovneUstanove.FAKULTET;
            String naziv = "";
            List<Profesor> profesoriUstanove = new ArrayList<>();
            List<Student> studentiUstanove = new ArrayList<>();
            List<Predmet> predmetiUstanove = new ArrayList<>();
            List<Ispit> ispitiUstanove = new ArrayList<>();

            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> tip = TipObrazovneUstanove.parseInt(Integer.parseInt(line));
                    case 3 -> naziv = line;
                    case 4 -> {
                        List<Long> profesorIdList = Arrays.stream(line.split(" "))
                                .map(Long::parseLong)
                                .toList();
                        sviProfesori.stream()
                                .filter(profesor -> profesorIdList.contains(profesor.getId()))
                                .forEach(profesoriUstanove::add);
                    }
                    case 5 -> {
                        List<Long> studentiIdList = Arrays.stream(line.split(" "))
                                .map(Long::parseLong)
                                .toList();
                        sviStudenti.stream()
                                .filter(student -> studentiIdList.contains(student.getId()))
                                .forEach(studentiUstanove::add);
                    }
                    case 6 -> {
                        List<Long> predmetIdList = Arrays.stream(line.split(" "))
                                .map(Long::parseLong)
                                .toList();

                        sviPredmeti.stream()
                                .filter(predmet -> predmetIdList.contains(predmet.getId()))
                                .forEach(predmetiUstanove::add);
                    }
                    case 7 -> {
                        List<Long> ispitIdList = Arrays.stream(line.split(" "))
                                .map(Long::parseLong)
                                .toList();

                        sviIspiti.stream()
                                .filter(ispit -> ispitIdList.contains(ispit.getId()))
                                .forEach(ispitiUstanove::add);

                        if (tip.compareTo(TipObrazovneUstanove.FAKULTET) == 0) {
                            sveuciliste.dodajObrazovnuUstanovu(
                                    new FakultetRacunarstva(
                                            id,
                                            naziv,
                                            predmetiUstanove,
                                            profesoriUstanove,
                                            studentiUstanove,
                                            ispitiUstanove)
                            );
                        } else {
                            sveuciliste.dodajObrazovnuUstanovu(
                                    new VeleucilisteJave(
                                            id,
                                            naziv,
                                            predmetiUstanove,
                                            profesoriUstanove,
                                            studentiUstanove,
                                            ispitiUstanove)
                            );
                        }
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return sveuciliste;
    }


}
