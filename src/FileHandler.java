import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;
//C:\Users\Matthew\Documents\File04.txt
//\\bex-file-01\studenthome$\16\16Faulkner_M
public class FileHandler {
    String filename;
    int rowWidth;

    public FileHandler(String filename, int rowWidth) {
        this.filename = filename;
        this.rowWidth = rowWidth;
    }

    public static void appendLine(String fileName, String data) {
        // write text to end of the file
        boolean append = true;
        try (PrintWriter pr = new PrintWriter(new FileWriter(fileName, append))) {
            pr.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readLineAt(String fileName, int startPoint) {
        // grab the line from position "start" in the file
        try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
            rf.seek(startPoint);
            return rf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void writeLineAt(String fileName, String data, int start) {
        // overwrite a line from position "start" in the file
        // doesn't check that the start position is actually
        // the beginning of the file. This will not behave well
        // unless every line is the same length
        try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
            rf.seek(start);
            rf.writeBytes(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countLines() {
        // return the number of lines in the file
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                count++;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }


    public void appendRecord(String data, int rowwidth) {

        if (data.length() != rowwidth) {
            if (data.length() > rowwidth) {
                System.out.println("Tried to write " + data + " to field width of " + rowwidth);

            }
            while (data.length() != rowwidth) {
                data = data + " ";

            }
        }
        if (data.length() == rowwidth) {
            FileHandler.appendLine(filename, data);

        }


    }

    public String getRecord(int rowNumber) {
        if (rowNumber == 0) {
            return FileHandler.readLineAt(filename, (rowNumber * rowWidth)).trim();
        } else {
            return FileHandler.readLineAt(filename, (rowNumber * (rowWidth+1)) + rowNumber).trim();
        }
    }

    public boolean findRecordFromKeyword(String SearchData) {
        boolean foundData = false;
        int counter = 0;
        for (int i = 0; i < countLines(); i++) {
            for (int j = 0; j < rowWidth; j++) {
                System.out.println("Looking on line " + i + "at character " + j);
                System.out.println(readLineAt(filename, j + i * rowWidth));
                if (readLineAt(filename, j + i * rowWidth).startsWith(SearchData)) {
                    System.out.println("Found one");
                    return true;
                }
            }
        }
        System.out.println("Nothing found");
        return false;
    }

    public String[] findAllRecordsFromKeyword(String SearchData) {
        String[] LineWhereWordFound = new String[countLines()];
        boolean foundData = false;
        int counter = 0;
        for (int i = 0; i < countLines(); i++) {
            for (int j = 0; j < rowWidth; j++) {
                System.out.println(readLineAt(filename, j + i * rowWidth));
                if (readLineAt(filename, j + i * rowWidth).startsWith(SearchData)) {
                    System.out.println("Found one on line " + i);
                    LineWhereWordFound[counter] = getRecord(i);
                    counter++;
                    if(readLineAt(filename, j + (i+1) * rowWidth) == null){
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        //TODO:Change the Question input to add a Space after Subject topic ect. as it will spit out the word "malteaser"
        // as an answer when searching for "malt" cause doesnt require spaces

        System.out.println(counter);
        return LineWhereWordFound;
    }
    public boolean findRecord(String SearchData) {
        boolean foundData = false;
        for (int i = 0; i < countLines(); i++) {
            String FoundData = getRecord(i).trim();

            if (Objects.equals(SearchData, FoundData)) {
                System.out.println("Record found on line " + i);
                foundData = true;

                break;
                //breaks out of loop one a mathc is found

            } else {
                System.out.println("Compared " + SearchData + " to " + getRecord(i));
            }
            // search for a record matching data
            // return true if found

        }
        return foundData;
    }
}
