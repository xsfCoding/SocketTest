import java.util.*;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }



    //version1:process
    public void processJava(List<Person> javaProgrammer) {
        javaProgrammer = new ArrayList() {

            {
                add(new Person("java1", 12));
                add(new Person("java2", 13));
            }
        };

        for (Person p : javaProgrammer) {
            processMethod01(p);
        }

    }

    private void processMethod01(Person p) {
        //...other operation
        System.out.println("java" + p.getName());
    }


    public void processPhp(List<Person> phpProgrammer) {
        phpProgrammer = new ArrayList() {
            {
                add(new Person("php1", 12));
                add(new Person("php2", 13));
            }
        };

        for (Person p : phpProgrammer) {
            processMethod02(p);
        }
    }

    private void processMethod02(Person p) {
        //...other operation about phpProgrammer
        System.out.println("php" + p.getName());
    }

    //version2:interface
    interface IProcess {
        void processMethod(Person p);
    }


    public void processPerson(List<Person> personList, IProcess process) {

        for (Person p : personList) {
            process.processMethod(p);
        }

    }

    public void processJava(){
       processPerson(new ArrayList(), new IProcess() {
        @Override
        public void processMethod (Person p){
            System.out.println("thid is javaProgrammer");
        }

    });
}
    public void processDriver(){
        processPerson(new ArrayList(), new IProcess() {
            @Override
            public void processMethod (Person p){
                System.out.println("thid is phpProgrammer");
            }

        });
    }

    public class javaProcess implements IProcess {

        @Override
        public void processMethod(Person p) {
            System.out.println("hello,this is javaProgrammer");
        }
    }


    public class phpProcess implements IProcess {

        @Override
        public void processMethod(Person p) {
            System.out.println("hello,this is phpProgrammer");
        }
    }

    //version lambda

    public void processDriver02(){
        processPerson(new ArrayList(), (Person p)
                ->
        {
            System.out.println(p.getName());
        });

    }

    public void processDriver03(){
        processPerson(new ArrayList(), p->
            System.out.println(p.getName())
        );
    }

    private void process() {
        System.out.println(".......");
    }



    public void processDriver04(){
        processPerson(new ArrayList(), p->
                System.out.println(p.getName())
        );
    }

    public void processDriver06(){
        processPerson(new ArrayList(), Person::getName);
    }


    public static void main(String[] args) {

    }
}
