import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author xueshufeng
 * @date 18-5-22 下午4:02
 */
public class Person01 {

    private String firstName, lastName, job, gender;
    private int salary, age;

    public Person01(String firstName, String lastName, String job,
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

    public String getLastName() {
        return lastName;
    }

    public String getJob() {
        return job;
    }

    public String getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
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
                Person01 u = (Person01) obj;
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
   public void showProcess(){

   }

    public static void main(String[] args) {
        List<Person01> javaProgrammers = new ArrayList<Person01>() {
            {
                add(new Person01("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person01("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person01("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person01("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person01("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person01("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person01("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person01("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person01("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person01("Addison", "Pam", "Java programmer", "female", 34, 1300));
                add(new Person01("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person01("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person01("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        List<Person01> phpProgrammers = new ArrayList<Person01>() {
            {
                add(new Person01("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person01("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person01("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person01("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person01("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person01("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person01("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person01("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person01("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person01("Evonne", "Shari", "PHP programmer", "female", 40, 1800));

            }
        };






        /**
         * 以下是lambda 的具体操作
         */

        System.out.println(javaProgrammers.contains(phpProgrammers));

        // Union
        List<Person01> allProgrammer = new ArrayList<>();
        for (int i = 0; i < javaProgrammers.size(); i++) {
            allProgrammer.add(javaProgrammers.get(i));
        }

        for (Person01 programmer : phpProgrammers) {
            allProgrammer.add(programmer);
        }

        //
        List<Object> allpeople = Stream.concat(Stream.of(javaProgrammers), phpProgrammers.stream()).collect(Collectors.toList());

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        List<Integer> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list = list.stream().distinct().collect(Collectors.toList());
        // intersect
        Person01 nullPerson = new Person01(null, null, null, null, 0, 0);

        // diff
        System.out.println("diff");
        javaProgrammers.stream().filter(t -> phpProgrammers.contains(t))
                //.forEach(System.out::println);
                .findFirst().orElse(nullPerson).getClass().getName();

        javaProgrammers.addAll(phpProgrammers);
        // for all person, increase age + 1
         javaProgrammers.stream().map(person01->(person01.getSalary()+1)).forEach(System.out::println);
        // find java programmer, whose salary is greater than 1000
        javaProgrammers.stream()
                 .filter(person01 -> person01.getSalary()>=2000)
                 .collect(Collectors.toList())
                 .forEach(p->System.out.println(p.getLastName()));

        // lay off java programmer who is over xxx years old
        javaProgrammers.stream()
                .filter(person01 -> person01.getAge()>34)
                .collect(Collectors.toList())
                .forEach(p->System.out.println("these people's age > 50    "+p.getLastName()));

        // find first java programmer whose name is Flyod
        String name=
                javaProgrammers.stream()
                .filter(person01 -> person01.getFirstName().equals("Floyd"))
                .findFirst()
                .orElse(nullPerson)
                .getLastName();
        System.out.println("name   "+name);

        // [*Optional, elseDefault] return php programmer whose last name is Wang, returns its age, if not found, return 0
        String phpProgram= phpProgrammers.stream()
                .filter(person01 -> person01.getLastName().equals("Wang"))
                .findFirst()
                .orElse(nullPerson).getLastName();
        System.out.println(phpProgram);

        int age = phpProgrammers.stream().filter(programmer -> programmer.lastName.equals("Wang")).findFirst().orElse(nullPerson).getAge();
    }


}