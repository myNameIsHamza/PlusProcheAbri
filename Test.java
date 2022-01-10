import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Test {

    public static void main(String[] args) {
        getResult("files/test.txt");
    }

    private static void getResult(String testPath) {
        List<Box> boxList = new ArrayList<Box>();
        List<Child> childList = new ArrayList<Child>();
        try {
            File file = new File(testPath); // creates a new file instance
            FileReader fr = new FileReader(file); // reads the file
            BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
            String line;
            int lineIndex = 0;
            int nombreAbris = 0;
            int nombreEnfants = 0;
            while ((line = br.readLine()) != null) {
                if (lineIndex == 0) {
                    nombreAbris = Integer.parseInt(line);
                } else if (lineIndex <= nombreAbris) {
                    boxList.add(new Box(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]),
                            Integer.parseInt(line.split(" ")[2])));
                } else if (lineIndex == nombreAbris + 1) {
                    nombreEnfants = Integer.parseInt(line);
                } else if (lineIndex <= nombreEnfants + nombreAbris + 1) {
                    childList.add(new Child(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]),
                            Integer.parseInt(line.split(" ")[2])));
                }
                lineIndex++;
            }
            fr.close(); // closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        getResultFile(boxList, childList);
    }

    private static void getResultFile(List<Box> boxList, List<Child> childList) {
        double distance;
        double minDistance;
        int minIndex;
        List<String> listResult = new ArrayList<String>();
        for (int c = 0; c < childList.size(); c++) {
            minDistance = Math
                    .sqrt(Math.pow(childList.get(c).x - boxList.get(0).x, 2)
                            + Math.pow(childList.get(c).y - boxList.get(0).y, 2));
            minIndex = 0;
            for (int b = 0; b < boxList.size(); b++) {
                distance = Math
                        .sqrt(Math.pow(childList.get(c).x - boxList.get(b).x, 2)
                                + Math.pow(childList.get(c).y - boxList.get(b).y, 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    minIndex = b;
                }
            }
            listResult.add(new Result(childList.get(c).nom, boxList.get(minIndex).nom).toString());
        }
        writeTo(String.join("\n", listResult), "files/result.txt"); // Write results to a file
        System.out.println(String.join("\n", listResult)); //Sysout Results
    }

    public static void writeTo(String text, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
