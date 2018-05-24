import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author xueshufeng
 * @date 18-5-22 下午4:02
 */
public class Person02 {

    private String firstName, lastName, job, gender;
    private int salary, age;

    public Person02(String firstName, String lastName, String job,
                    String gender, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }else {
            if(this.getClass() == obj.getClass()){
                Person02 u = (Person02) obj;
                if(this.getLastName().equals(u.getLastName())){
                    return true;
                }else{
                    return false;
                }

            }else{
                return false;
            }
        }
    }

    public void setAge(int age) {
        this.age = age;
    }

    // version 1
    public void iterate(List<String> collection) {
        for (String element :
            collection) {
            process(element);
        }
    }

    private static void process(String element) {
        System.out.println(element);
    }

    public void iterate2(List<String> collection) {
        for (String element :
            collection) {
            process2(element);
        }
    }

    private void process2(String element) {

    }

    public void drive() {
        iterateGeneral(new ArrayList<>(), new MyConsumer() {
            @Override
            public void consume(String element) {
                //...
            }
        });
    }

    // version 2 - interface
    public void iterateGeneral(List<String> collection, MyConsumer consumer) {
        for (String element :
            collection) {
            consumer.consume(element);
        }
    }

    interface MyConsumer {
        void consume(String element);
    }

    public class MyConsumerPrintScreen implements MyConsumer {

        @Override
        public void consume(String element) {
            System.out.println(element);
        }
    }

    //public class MyConsumerLogToFile implements MyConsumer {
    //    //...
    //}


    // version 3 - syntax sugar
    public void drive2() {
        iterateGeneral(new ArrayList<>(), (String element) -> {
            System.out.println((element))
            ;
        });
    }


    // version 3 - syntax sugar evolve 2, single statement optimization
    public void drive3() {
        iterateGeneral(new ArrayList<>(), (String element) ->
            System.out.println((element))
        );
    }

    // version 3 - syntax sugar evolve 2，　 type inference
    public void drive４() {
        iterateGeneral(new ArrayList<>(), element ->
            System.out.println((element))
        );
    }


    // version 4 - syntax sugar evolve 4, method reference
    public void drive5() {
        iterateGeneral(new ArrayList<>(), x -> process(x)
        );
    }

    public void drive6() {
        iterateGeneral(new ArrayList<>(), Person02::process
        );
    }

    public static void main(String[] args) {
        List<Person02> javaProgrammers = new ArrayList<Person02>() {
            {
                add(new Person02("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person02("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person02("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person02("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person02("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person02("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person02("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person02("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person02("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person02("Addison", "Pam", "Java programmer", "female", 34, 1300));
                add(new Person02("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person02("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person02("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        List<Person02> phpProgrammers = new ArrayList<Person02>() {
            {
                add(new Person02("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person02("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person02("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person02("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person02("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person02("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person02("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person02("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person02("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person02("Evonne", "Shari", "PHP programmer", "female", 40, 1800));

            }
        };

        System.out.println(javaProgrammers.contains(phpProgrammers));

        // Union
        List<Person02> allProgrammer = new ArrayList<>();
        for(int i=0;i<javaProgrammers.size();i++){
            allProgrammer.add(javaProgrammers.get(i));
        }

        for (Person02 programmer :
            phpProgrammers) {
            allProgrammer.add(programmer);
        }

        //
        List<Object> allpeople = Stream.concat(Stream.of(javaProgrammers),
            phpProgrammers.stream()).collect(Collectors.toList());

        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        List<Integer> list=new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list=list.stream().distinct().collect(Collectors.toList());
        // intersect
        Person02 nullPerson = new Person02(null, null, null, null, 0, 0);
        // diff
        System.out.println("diff");
        javaProgrammers.stream()
             .filter(t -> phpProgrammers.contains(t))
             //.forEach(System.out::println);
             .findFirst()
             .orElse(nullPerson).getClass().getName();



        // for all person, increase age + 1
           Stream.of(Stream.of(javaProgrammers), Stream.of(phpProgrammers))
            .flatMap(e->e)
            .collect(Collectors.toList());





        // find java programmer, whose salary is greater than 1000

        // lay off java programmer who is over xxx years old

        // find first java programmer whose name is Flyod

        // [*Optional, elseDefault] return php programmer whose last name is Wang, returns its age, if not found, return 0

       int age= phpProgrammers.stream().filter(programmer -> programmer.lastName.equals("Wang")).findFirst().orElse(nullPerson).getAge();
        System.out.println("dhfgsdhfjdsfjdhsj"+age);



        //javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        //phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        //
        //javaProgrammers.stream()
        //.filter(p->p.getSalary()>1900)
        //.forEach(person -> System.out.println(person.getAge()));
        //

        javaProgrammers.stream()
            .filter(p->p.getGender().equals("male"))
            .sorted((n1,n2)->(n1.getSalary()-n2.getSalary()))
            .collect(toList())
            .forEach(p-> System.out.println(p.getFirstName()));

        int sum=javaProgrammers.stream()
            .mapToInt(Person02::getSalary)
            .parallel()
            .reduce(0,Integer::sum);
        System.out.println(sum);





        //求最低的工资
        Person02 person=javaProgrammers.stream()
            .max((p1,p2)->(p1.getSalary()-p2.getSalary()))
            .get();
        //Stream还可以是并行的
        javaProgrammers.parallelStream().mapToInt(p->p.getSalary()).count();
        Runnable runnable=new Thread(()-> System.out.println("thresf"));
        ((Thread)runnable).start();
        Runnable runnable01=()-> System.out.println("hhhhh");
        runnable01.run();
    }

    private Double sum(List<Person02> listPerson){
        Double sum=0.0;
        for(Person02 person:listPerson){
            sum+=Optional.ofNullable(person.getSalary()).orElse((int)0.0);
            sum= Double.valueOf(Optional.ofNullable(person.getSalary()).orElseGet(()-> Integer.valueOf(getGender())));
          //  sum= Double.valueOf(Optional.ofNullable(person.getSalary()).isPresent(System.out::println));
        }
        return sum;
    }





}