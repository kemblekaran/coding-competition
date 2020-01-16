import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\DeLL\\Downloads\\hashcode-2020\\e_also_big.in");
        try {
            Stream<String> input = Files.lines(path);
            Object[] objects = input.toArray();

            if (objects != null) {
//                System.out.println(objects.length);
                String fLine = objects[0].toString();
                String sLine = objects[1].toString();

                int[] f = Stream.of(fLine.split(" "))
                        .mapToInt(sliceAndType -> Integer.parseInt(sliceAndType))
                        .toArray();
                int[] s = Stream.of(sLine.split(" "))
                        .mapToInt(noOfSlices -> Integer.parseInt(noOfSlices))
                        .toArray();

//                System.out.println("Input Set");
//                System.out.println(Arrays.toString(f));
//                System.out.println(Arrays.toString(s));

                int maxSlices = f[0];
                int typeOfPizza = f[1];
//                int[] orderingPizza = new int[typeOfPizza];
                List<Integer> orderingPizza = new ArrayList<>();
                int slices = 0;
//                System.out.println("maxSlices[" + maxSlices + "], typeOfPizza[" + typeOfPizza + "]");
                if (typeOfPizza == s.length) {
                    int index = s.length - 1;
                    for (int i = 0; i < typeOfPizza; i++) {
//                        System.out.println("slices in pizza " + i + " are " + s[i]);
//                        System.out.println("elementAtIndex[" + index + "] is [" + s[index] + "]");
                        int search = Arrays.binarySearch(s, s[index]);

                        if (search >= 0) {
                            slices += s[search];

                            if (slices > maxSlices)
                                slices -= s[search];
                            else {
                                orderingPizza.add(index);
                            }
                        }
                        index--;
                    }
                }

                Collections.sort(orderingPizza);
                System.out.println("maximum slices from the set are [" + slices + "]");
                System.out.println("noOfPizzaToOrder[" + orderingPizza.size() + "], orderingPizza" + orderingPizza.toString());
                String output = String.valueOf(orderingPizza.size())
                        .concat("\n")
                        .concat(orderingPizza.toString().replaceAll("[\\[\\]^,]", "").trim());
                //write output to a file
                Files.write(Paths.get("C:\\Users\\DeLL\\Downloads\\hashcode-2020\\e_also_big.out"), output.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
