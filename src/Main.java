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

        // считаем количество несовершеннолетних (т.е. людей младше 18 лет)
        long numberOfChildren = persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + numberOfChildren);

        // Получаем список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> militaryList = persons.stream()
                .filter(Person -> Person.getAge() >= 18 && Person.getAge() <= 27
                        && Person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .toList();

        // Получаем список женщин с высшим образованием от 18 до 60 лет
        List<Person> womanList = persons.stream()
                .filter(Person -> Person.getAge() >= 18 && Person.getAge() <= 60
                        && Person.getSex().equals(Sex.WOMAN) && Person.getEducation().equals(Education.HIGHER))
                .toList();

        // Получаем список мужчин с высшим образованием от 18 до 65 лет
        List<Person> manList = persons.stream()
                .filter(Person -> Person.getAge() >= 18 && Person.getAge() <= 65
                        && Person.getSex().equals(Sex.MAN) && Person.getEducation().equals(Education.HIGHER))
                .toList();

        // Получаем объединенный список людей с высшим образованием
        List<Person> peopleList = new ArrayList<>();
        peopleList.addAll(womanList);
        peopleList.addAll(manList);

        // Получаем отсортированный по фамилии объединенный список людей с высшим образованием
        List<Person> sortedPeopleList = peopleList.stream()
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}