package ru.kr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kr.domain.*;
import ru.kr.repos.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final HallRepo hallRepo;
    private final GanreRepo ganreRepo;
    private final SeatRepo seatRepo;
    private final UsersRepo usersRepo;
    private final ShowRepo showRepo;
    private final SessionRepo sessionRepo;

    public DataInitializer(HallRepo hallRepo, GanreRepo ganreRepo, SeatRepo seatRepo, UsersRepo usersRepo, ShowRepo showRepo, SessionRepo sessionRepo) {
        this.hallRepo = hallRepo;
        this.ganreRepo = ganreRepo;
        this.seatRepo = seatRepo;
        this.usersRepo = usersRepo;
        this.showRepo = showRepo;
        this.sessionRepo = sessionRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        InitUsers();
        InitHalls();
        InitGanres();
        InitShows();
        InitSessions();
    }


    private void InitSessions() throws ParseException {
        if (sessionRepo.count() == 0){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            Session session1 = new Session();
            Show show1 = (Show) showRepo.findByTitleAndTitle("Судьба в подарок", "Судьба в подарок");
            Hall hall1 = hallRepo.findByName("Малый");
            String dateString1 = "2024-11-30T14:30";
            Date date1 = formatter.parse(dateString1);
            session1.setShow(show1);
            session1.setDate(date1);
            session1.setHall(hall1);
            session1.setPrice(1000);
            sessionRepo.save(session1);

            Session session2 = new Session();
            Show show2 = (Show) showRepo.findByTitleAndTitle("Судьба в подарок", "Судьба в подарок");
            Hall hall2 = hallRepo.findByName("Большой");
            String dateString2 = "2024-12-30T20:30";
            Date date2 = formatter.parse(dateString2);
            session2.setShow(show2);
            session2.setDate(date2);
            session2.setHall(hall2);
            session2.setPrice(800);
            sessionRepo.save(session2);

            Session session3 = new Session();
            Show show3 = (Show) showRepo.findByTitleAndTitle("Идеальный муж", "Идеальный муж");
            Hall hall3 = hallRepo.findByName("Малый");
            String dateString3 = "2024-12-10T20:00";
            Date date3 = formatter.parse(dateString3);
            session3.setShow(show3);
            session3.setDate(date3);
            session3.setHall(hall3);
            session3.setPrice(1400);
            sessionRepo.save(session3);

            Session session4 = new Session();
            Show show4 = (Show) showRepo.findByTitleAndTitle("Доктор Фауст", "Доктор Фауст");
            Hall hall4 = hallRepo.findByName("Большой");
            String dateString = "2024-12-15T14:30";
            Date date4 = formatter.parse(dateString);
            session4.setShow(show4);
            session4.setDate(date4);
            session4.setHall(hall4);
            session4.setPrice(570);
            sessionRepo.save(session4);


            System.out.println("Таблица sessions инициализирована начальными данными.");
        }else System.out.println("Таблица sessions уже содержит данные.");
    }
    private void InitShows(){
        if (showRepo.count() == 0){
            Show show1 = new Show();
            show1.setFilename("eecdc6b5-5434-4daf-8e2c-fcfc7f775dcf_b618d6e67a874f22b622af254b62.jpg");
            show1.setTitle("Судьба в подарок");
            show1.setDescription("Новогодняя ночь 1975 года приготовила для Роберта Стоуна и Энни Стридж особенный подарок. Случайное знакомство вдруг окажется судьбоносной встречей.\n" +
                    "\n" +
                    "Роберт - сын состоятельных родителей, Энни- девушка из простой семьи. Но имеет ли всё это значение? Они влюбятся друг в друга с первого взгляда, и спрятавшись от посторонних глаз, вдохновившись опытом физика Шрёдингера, проведут небольшой эксперимент … с судьбой. Придумают свой сценарий счастья.\n" +
                    "\n" +
                    "Спонтанная шутка навсегда изменит их историю. Энни и Роберт имеют все шансы стать самой счастливой парой на свете. Но хозяева ли они своей судьбы? Может кто-то (или что-то ) вносит коррективы в их планы?");
            show1.setDuration(100);
            show1.setAgeRating(16);
            show1.setGanre(ganreRepo.findByName("драма"));
            showRepo.save(show1);

            Show show2 = new Show();
            show2.setFilename("a2f5e759-becf-404c-af45-bd496845fa7f_im.jpg");
            show2.setTitle("Идеальный муж");
            show2.setDescription("Новогодняя ночь 1975 года приготовила для Роберта Стоуна и Энни Стридж особенный подарок. Случайное знакомство вдруг окажется судьбоносной встречей.\n" +
                    "\n" +
                    "Роберт - сын состоятельных родителей, Энни- девушка из простой семьи. Но имеет ли всё это значение? Они влюбятся друг в друга с первого взгляда, и спрятавшись от посторонних глаз, вдохновившись опытом физика Шрёдингера, проведут небольшой эксперимент … с судьбой. Придумают свой сценарий счастья.\n" +
                    "\n" +
                    "Спонтанная шутка навсегда изменит их историю. Энни и Роберт имеют все шансы стать самой счастливой парой на свете. Но хозяева ли они своей судьбы? Может кто-то (или что-то ) вносит коррективы в их планы?");
            show2.setDuration(160);
            show2.setAgeRating(18);
            show2.setGanre(ganreRepo.findByName("Комедия"));
            showRepo.save(show2);

            Show show3 = new Show();
            show3.setFilename("653a8c64-5add-419c-94ec-0554d7f81e41_GR.jpg");
            show3.setTitle("Гранд. «История в отеле»");
            show3.setDescription("25 ведущих танцоров, из них 16 чемпионов мира откроют вам двери даже в те номера отеля, на которых обычно висит табличка «Не беспокоить». Самые потаенные секреты и тайны каждой комнаты, как яркий танцевальный калейдоскоп, сменяют друг друга. Что скрывает каждая дверь? О чем известно только доброжелательному портье? Кто собирается по вечерам в баре? Зачем таинственная влюбленная пара поднялась на крышу? На искрящемся юмором и талантом шоу и узнайте все, что скрыто от посторонних глаз. А известные мировые хиты от ABBA и Рики Мартина до «Охотников за привидениями» и Maneskin добавят зрелищу драйва и огня.");
            show3.setDuration(120);
            show3.setAgeRating(6);
            show3.setGanre(ganreRepo.findByName("мюзикл"));
            showRepo.save(show3);

            Show show4 = new Show();
            show4.setFilename("ccf1a0cc-e4b4-4c4f-8b41-f5f9442fa014_DF.jpg");
            show4.setTitle("Доктор Фауст");
            show4.setDescription("по мотивам философской драмы Иоганна Гете «Фауст» История одного известного доктора, который поставил на карту все и проиграл… или все-таки выиграл. Имеет ли право гений переступить морально-этическую черту, дабы сотворить шедевр на все времена? Об этом и не только спектакль Ивана Титова «Доктор Фауст».");
            show4.setDuration(170);
            show4.setAgeRating(16);
            show4.setGanre(ganreRepo.findByName("трагикомедия"));
            showRepo.save(show4);


            System.out.println("Таблица shows инициализирована начальными данными.");
        } else System.out.println("Таблица shows уже содержит данные.");
    }
    private void InitGanres(){
        if (ganreRepo.count() == 0) {
            Ganre ganre1 = new Ganre();
            ganre1.setName("Комедия");
            ganreRepo.save(ganre1);

            Ganre ganre2 = new Ganre();
            ganre2.setName("драма");
            ganreRepo.save(ganre2);

            Ganre ganre3 = new Ganre();
            ganre3.setName("мюзикл");
            ganreRepo.save(ganre3);

            Ganre ganre4 = new Ganre();
            ganre4.setName("мелодрама");
            ganreRepo.save(ganre4);

            Ganre ganre5 = new Ganre();
            ganre5.setName("Опера");
            ganreRepo.save(ganre5);

            Ganre ganre6 = new Ganre();
            ganre6.setName("трагикомедия");
            ganreRepo.save(ganre6);
            System.out.println("Таблица ganres инициализирована начальными данными.");
        } else {
            System.out.println("Таблица ganres уже содержит данные.");
        }
    }
    private void InitHalls(){
        if (hallRepo.count() == 0) {
            Hall hall1 = new Hall();
            hall1.setName("Большой");
            hall1.setRows(15);
            hall1.setSeatsPerRow(25);
            hallRepo.save(hall1);
            for (int row = 1; row <= hall1.getRows(); row++) {
                for (int place = 1; place <= hall1.getSeatsPerRow(); place++) {
                    Seat seat = new Seat();
                    seat.setRow((byte) row);
                    seat.setPlace((byte) place);
                    seat.setHall(hall1);
                    seatRepo.save(seat);
                }
            }

            Hall hall2 = new Hall();
            hall2.setName("Малый");
            hall2.setRows(8);
            hall2.setSeatsPerRow(15);
            hallRepo.save(hall2);
            for (int row = 1; row <= hall2.getRows(); row++) {
                for (int place = 1; place <= hall2.getSeatsPerRow(); place++) {
                    Seat seat = new Seat();
                    seat.setRow((byte) row);
                    seat.setPlace((byte) place);
                    seat.setHall(hall2);
                    seatRepo.save(seat);
                }
            }

            System.out.println("Таблица halls инициализирована начальными данными.");
        } else {
            System.out.println("Таблица halls уже содержит данные.");
        }
    }
    private void InitUsers() throws Exception{
        if (usersRepo.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setActive(true);
            admin.setRoles(Collections.singleton(Role.ADMIN));
            usersRepo.save(admin);
            System.out.println("Пользователь 'admin' создан с паролем 'admin'.");
        } else {
            System.out.println("Пользователь 'admin' уже существует.");
        }

        if (usersRepo.findByUsername("user") == null) {
            User admin = new User();
            admin.setUsername("user");
            admin.setPassword(passwordEncoder.encode("user"));
            admin.setActive(true);
            admin.setRoles(Collections.singleton(Role.USER));
            usersRepo.save(admin);
            System.out.println("Пользователь 'user' создан с паролем 'u'.");
        } else {
            System.out.println("Пользователь 'user' уже существует.");
        }
    }
}
