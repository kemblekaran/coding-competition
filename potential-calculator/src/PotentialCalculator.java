import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PotentialCalculator {
    public static void main(String[] args) {

        String firstInput = "a_solar.txt";
        List<String> files = Arrays.asList(firstInput);

        files.forEach(file -> {
            try {
                List<Developer> developers = new ArrayList<>();
                List<Manager> managers = new ArrayList<>();
                List<String> lines = Files.lines(Paths.get("F:\\Git\\reply-code-challenge\\" + file)).collect(Collectors.toList());

                long availableSeats = 0;
                int developerEnd = 0, managerEnd = 0;
                Integer width = 0, height = 0, noOfDevelopers = 0, noOfManagers = 0, d = 0;
                for (Integer l = 0; l < lines.size(); l++) {
//                    System.out.println("line " + l + " = " + lines.get(l));
                    long developersSeats = 0, managersSeats = 0;
                    String seat = lines.get(l);
                    if (l == 0) {
                        String[] count = seat.split(" ");
                        width = Integer.valueOf(count[0]);
                        height = Integer.valueOf(count[1]);


//                        System.out.println("developers = " + developers.size() + " managers = " + managers.size());
                        System.out.println("width = " + width + " height = " + height);
                    }


                    if (l >= 1 && l <= height) {

                        if (seat.contains("_")) {
                            developersSeats = seat.chars().filter(ch -> ch == '_').count();
//                            System.out.println("developer's seat = " + developersSeats);
                            availableSeats += developersSeats;

                            managersSeats = seat.chars().filter(ch -> ch == 'M').count();
//                            System.out.println("manager's seat = " + managersSeats);
                            availableSeats += managersSeats;
                        } else if (seat.contains("M")) {
                            developersSeats = seat.chars().filter(ch -> ch == '_').count();
//                            System.out.println("developer's seat = " + developersSeats);
                            availableSeats += developersSeats;

                            managersSeats = seat.chars().filter(ch -> ch == 'M').count();
//                            System.out.println("manager's seat = " + managersSeats);
                            availableSeats += managersSeats;
                        } else {

                        }

//                        System.out.println("available seats = " + availableSeats);
                    }


                    if (l == (height + 2)) {
                        d = height + 1;
                        noOfDevelopers = Integer.valueOf(lines.get(d));
                        developerEnd = noOfDevelopers + 4;
                    }

                    //developers
                    if ((l >= (d + 1) && l <= developerEnd) && d != 0) {
                        String[] developerSpec = lines.get(l).split(" ");

                        Set<String> skillSet = new HashSet<>(Integer.parseInt(developerSpec[2]));
                        for (int i = 3; i < developerSpec.length; i++) {
                            skillSet.add(developerSpec[i]);
                        }

//                        System.out.println("skillset size " + skillSet.size());
                        Developer developer = new Developer(developerSpec[0], Integer.parseInt(developerSpec[1]), skillSet);
                        developers.add(developer);
                    }

                    //managers
                    if (l == (developerEnd + 1) && developerEnd != 0) {
                        managerEnd = l;
                    }


                    if (l >= (managerEnd + 1) && managerEnd != 0) {
                        String[] managerSpec = lines.get(l).split(" ");
                        Manager manager = new Manager(managerSpec[0], Integer.parseInt(managerSpec[1]));
                        managers.add(manager);
                    }


                }

                System.out.println("noOfManagers = " + managers.size());
                System.out.println("noOfDevelopers = " + developers.size());

//                for (int w = 0; w < width; w++) {
//                    for (int h = 0; h < height; h++) {
////                                System.out.println("pair = (" + w + ", " + h + ")");
//                    }
//                }

                List<Integer> adjacent = new ArrayList<>();
                int[][] matrix = new int[width][height];// = new int[width][height];

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((i != 0) ^ (j != 0)) {
                            if (width + i >= 0 && height + j >= 0 && width + i < matrix[0].length && height + j < matrix.length) {
                                adjacent.add(matrix[width + i][height + j]);
                            }
                        }
                    }
                }

                String s = Arrays.toString(adjacent.toArray());
                System.out.println("adj = " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
