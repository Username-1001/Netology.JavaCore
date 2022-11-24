import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //п. 1
        long teenCount = persons.stream().filter(person -> person.getAge() < 18).count();
        // п.2
        List<String> recruitNames = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() > 18 && person.getAge() < 27)
                .map(person -> person.getName())
                .collect(Collectors.toList());
        //п.3
        List<Person> employables = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.MAN && person.getAge() < 65) ||
                        (person.getSex() == Sex.WOMAN && person.getAge() < 60)))
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());

    }
}
