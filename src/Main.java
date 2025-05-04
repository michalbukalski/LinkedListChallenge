import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    record Place(String name, int distance) {

        @Override
        public String toString() { // nadpisuje, żeby zmodyfikować wyświetlanie. Bez tej modyfikacji lista wyglądała bardzo nie czytelnie
            return String.format("%s - %dkm", name, distance);
        }
    }

    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();

        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide);
        addPlace(placesToVisit, new Place("Hamilton", 999));

        addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("Radom", 1373));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));

        placesToVisit.addFirst(new Place("Sydney",0));
        System.out.println(placesToVisit);

        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while (!quitLoop) {
//            printMenu();

            if(!iterator.hasPrevious()) {
                System.out.println("Originating: " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final: " + iterator.previous());
                forward = false;
            }

            System.out.println("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0,1);

            switch (menuItem) {
                case "F" -> {
                    System.out.println("User wants to go forward");
                    if (!forward) { //kod 56 - 60 linijka - służy do odwracania kierunków z tyłu na przód
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                case "B" -> {
                    System.out.println("User wants to go backwards");
                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                }
                case "M" -> printMenu();
                case "L" -> System.out.println(placesToVisit);
                default -> quitLoop = true;
            }
        }
    }

    private static void addPlace(LinkedList<Place> list, Place place) {
        if(list.contains(place)){ //kod do sprawdzenia duplikatów, czy place już jest na liście
            System.out.println("Found duplicate: " + place);
            return;
        }

        for (Place p:list){
            if(p.name().equalsIgnoreCase(place.name())){
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace: list) {
            if(place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    private static void printMenu() {
        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");

    }
}
