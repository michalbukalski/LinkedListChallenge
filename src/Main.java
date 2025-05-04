import java.util.LinkedList;

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
}
