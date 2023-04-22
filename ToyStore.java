// Промежуточная контрольная работа по блоку специализация №2.
// Написать проект, для розыгрыша в магазине игрушек. Функционал должен
// содержать добавление новых игрушек и задания веса для выпадения игрушек.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyStore {
    private String[] toyIds;
    private String[] toyNames;
    private int[] toyFrequencies;
    private PriorityQueue<Toy> toyQueue;

    public ToyStore(String[] ids, String[] names, int[] frequencies) {
        this.toyIds = ids;
        this.toyNames = names;
        this.toyFrequencies = frequencies;
        this.toyQueue = new PriorityQueue<>();
        for (int i = 0; i < ids.length; i++) {
            Toy toy = new Toy(ids[i], names[i], frequencies[i]);
            toyQueue.add(toy);
        }
    }

    public void getToys() {
        for (int i = 0; i < 10; i++) {
            Toy toy = toyQueue.poll();
            System.out.println(toy.getName() + " s idintifikatorom " + toy.getId() + " bil vibran.");
            appendToFile(toy.getName(), toy.getId());
        }
    }

    private void appendToFile(String name, String id) {
        try {
            File file = new File("picked_toys.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(name + " s idintifikatorom " + id + " bil vibran.\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] ids = { "1", "2", "3" };
        String[] names = { "Rabbit", "Ball", "Bear" };
        int[] frequencies = { 10, 20, 30 };

        ToyStore toyStore = new ToyStore(ids, names, frequencies);
        toyStore.getToys();
    }
}

class Toy implements Comparable<Toy> {
    private String id;
    private String name;
    private int frequency;

    public Toy(String id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Toy toy) {
        return Integer.compare(this.frequency, toy.getFrequency());
    }
}