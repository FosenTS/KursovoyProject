package ru.raffalda.kursovoy_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.raffalda.kursovoy_project.model.Faculty;
import ru.raffalda.kursovoy_project.model.Chair;
import ru.raffalda.kursovoy_project.model.Post;
import ru.raffalda.kursovoy_project.model.Teacher;
import ru.raffalda.kursovoy_project.repository.FacultyRepository;
import ru.raffalda.kursovoy_project.repository.ChairRepository;
import ru.raffalda.kursovoy_project.repository.PostRepository;
import ru.raffalda.kursovoy_project.repository.TeacherRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FacultyRepository facultyRepository;
    private final ChairRepository chairRepository;
    private final PostRepository postRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public DataInitializer(
            FacultyRepository facultyRepository,
            ChairRepository chairRepository,
            PostRepository postRepository,
            TeacherRepository teacherRepository) {
        this.facultyRepository = facultyRepository;
        this.chairRepository = chairRepository;
        this.postRepository = postRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) {
        Post professor = new Post();
        professor.setNamePost("Профессор");
        professor = postRepository.save(professor);

        Post associateProfessor = new Post();
        associateProfessor.setNamePost("Старший преподаватель");
        associateProfessor = postRepository.save(associateProfessor);

        Post assistantProfessor = new Post();
        assistantProfessor.setNamePost("Ассистент");
        assistantProfessor = postRepository.save(assistantProfessor);


        Faculty computerScience = new Faculty();
        computerScience.setNameFaculty("Факультет компьютерных наук");
        computerScience.setShortNameFaculty("ФКН");
        computerScience = facultyRepository.save(computerScience);

        Faculty mathematics = new Faculty();
        mathematics.setNameFaculty("Факультет математики");
        mathematics.setShortNameFaculty("ФМ");
        mathematics = facultyRepository.save(mathematics);

        Chair softwareEngineering = new Chair();
        softwareEngineering.setNameChair("Кафедра программирования");
        softwareEngineering.setShortNameChair("КП");
        softwareEngineering.setFaculty(computerScience);
        softwareEngineering = chairRepository.save(softwareEngineering);

        Chair computerSystems = new Chair();
        computerSystems.setNameChair("Кафедра компьютерных систем");
        computerSystems.setShortNameChair("ККС");
        computerSystems.setFaculty(computerScience);
        computerSystems = chairRepository.save(computerSystems);

        Chair appliedMath = new Chair();
        appliedMath.setNameChair("Прикладная математика");
        appliedMath.setShortNameChair("ПМ");
        appliedMath.setFaculty(mathematics);
        appliedMath = chairRepository.save(appliedMath);


        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Иван");
        teacher1.setSecondName("Иванович");
        teacher1.setLastName("Иванов");
        teacher1.setPost(professor);
        teacher1.setChair(softwareEngineering);
        teacher1.setEmail("ivan.ivanov@university.edu");
        teacher1.setPhone("+79999999997");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Мария");
        teacher2.setSecondName("Александровна");
        teacher2.setLastName("Смирнова");
        teacher2.setPost(associateProfessor);
        teacher2.setChair(computerSystems);
        teacher2.setEmail("maria.garcia@university.edu");
        teacher2.setPhone("+79999999998");
        teacherRepository.save(teacher2);

        Teacher teacher3 = new Teacher();
        teacher3.setFirstName("Александр");
        teacher3.setSecondName("Сергеевич");
        teacher3.setLastName("Петров");
        teacher3.setPost(assistantProfessor);
        teacher3.setChair(appliedMath);
        teacher3.setEmail("alexander.petrov@university.edu");
        teacher3.setPhone("+79999999999");
        teacherRepository.save(teacher3);

        Teacher teacher4 = new Teacher();
        teacher4.setFirstName("Елена");
        teacher4.setSecondName("Дмитриевна");
        teacher4.setLastName("Козлова");
        teacher4.setPost(professor);
        teacher4.setChair(softwareEngineering);
        teacher4.setEmail("elena.kozlova@university.edu");
        teacher4.setPhone("+79999999996");
        teacherRepository.save(teacher4);

        Teacher teacher5 = new Teacher();
        teacher5.setFirstName("Дмитрий");
        teacher5.setSecondName("Андреевич");
        teacher5.setLastName("Соколов");
        teacher5.setPost(associateProfessor);
        teacher5.setChair(computerSystems);
        teacher5.setEmail("dmitry.sokolov@university.edu");
        teacher5.setPhone("+79999999995");
        teacherRepository.save(teacher5);

        Teacher teacher6 = new Teacher();
        teacher6.setFirstName("Анна");
        teacher6.setSecondName("Павловна");
        teacher6.setLastName("Новикова");
        teacher6.setPost(assistantProfessor);
        teacher6.setChair(appliedMath);
        teacher6.setEmail("anna.novikova@university.edu");
        teacher6.setPhone("+79999999994");
        teacherRepository.save(teacher6);
    

        Teacher teacher7 = new Teacher();
        teacher7.setFirstName("Сергей");
        teacher7.setSecondName("Николаевич");
        teacher7.setLastName("Морозов");
        teacher7.setPost(professor);
        teacher7.setChair(softwareEngineering);
        teacher7.setEmail("sergey.morozov@university.edu");
        teacher7.setPhone("+79999999993");
        teacherRepository.save(teacher7);

        Teacher teacher8 = new Teacher();
        teacher8.setFirstName("Ольга");
        teacher8.setSecondName("Ивановна");
        teacher8.setLastName("Волкова");
        teacher8.setPost(associateProfessor);
        teacher8.setChair(computerSystems);
        teacher8.setEmail("olga.volkova@university.edu");
        teacher8.setPhone("+79999999992");
        teacherRepository.save(teacher8);

        Teacher teacher9 = new Teacher();
        teacher9.setFirstName("Андрей");
        teacher9.setSecondName("Михайлович");
        teacher9.setLastName("Кузнецов");
        teacher9.setPost(assistantProfessor);
        teacher9.setChair(appliedMath);
        teacher9.setEmail("andrey.kuznetsov@university.edu");
        teacher9.setPhone("+79999999991");
        teacherRepository.save(teacher9);

        Teacher teacher10 = new Teacher();
        teacher10.setFirstName("Татьяна");
        teacher10.setSecondName("Сергеевна");
        teacher10.setLastName("Лебедева");
        teacher10.setPost(professor);
        teacher10.setChair(softwareEngineering);
        teacher10.setEmail("tatiana.lebedeva@university.edu");
        teacher10.setPhone("+79999999990");
        teacherRepository.save(teacher10);

        Teacher teacher11 = new Teacher();
        teacher11.setFirstName("Михаил");
        teacher11.setSecondName("Александрович");
        teacher11.setLastName("Соловьев");
        teacher11.setPost(associateProfessor);
        teacher11.setChair(computerSystems);
        teacher11.setEmail("mikhail.soloviev@university.edu");
        teacher11.setPhone("+79999999989");
        teacherRepository.save(teacher11);

        Teacher teacher12 = new Teacher();
        teacher12.setFirstName("Екатерина");
        teacher12.setSecondName("Дмитриевна");
        teacher12.setLastName("Крылова");
        teacher12.setPost(assistantProfessor);
        teacher12.setChair(appliedMath);
        teacher12.setEmail("ekaterina.krylova@university.edu");
        teacher12.setPhone("+79999999988");
        teacherRepository.save(teacher12);

        Teacher teacher13 = new Teacher();
        teacher13.setFirstName("Павел");
        teacher13.setSecondName("Иванович");
        teacher13.setLastName("Громов");
        teacher13.setPost(professor);
        teacher13.setChair(softwareEngineering);
        teacher13.setEmail("pavel.gromov@university.edu");
        teacher13.setPhone("+79999999987");
        teacherRepository.save(teacher13);

        Teacher teacher14 = new Teacher();
        teacher14.setFirstName("Юлия");
        teacher14.setSecondName("Андреевна");
        teacher14.setLastName("Орлова");
        teacher14.setPost(associateProfessor);
        teacher14.setChair(computerSystems);
        teacher14.setEmail("yulia.orlova@university.edu");
        teacher14.setPhone("+79999999986");
        teacherRepository.save(teacher14);

        Teacher teacher15 = new Teacher();
        teacher15.setFirstName("Николай");
        teacher15.setSecondName("Сергеевич");
        teacher15.setLastName("Воробьев");
        teacher15.setPost(assistantProfessor);
        teacher15.setChair(appliedMath);
        teacher15.setEmail("nikolay.vorobiev@university.edu");
        teacher15.setPhone("+79999999985");
        teacherRepository.save(teacher15);

        Teacher teacher16 = new Teacher();
        teacher16.setFirstName("Ирина");
        teacher16.setSecondName("Павловна");
        teacher16.setLastName("Зайцева");
        teacher16.setPost(professor);
        teacher16.setChair(softwareEngineering);
        teacher16.setEmail("irina.zaitseva@university.edu");
        teacher16.setPhone("+79999999984");
        teacherRepository.save(teacher16);
    }
}