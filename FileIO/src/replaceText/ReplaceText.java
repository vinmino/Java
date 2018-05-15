package replaceText;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReplaceText {

    public static void main(String[] args) {
        File sourceFile;
        File targetFile;
        String oldString;
        String newString;
        if (args.length != 0) {
            sourceFile = new File(args[0]);
            targetFile = new File(args[1]);
            oldString = args[2];
            newString = args[2];
        } else {
            Scanner commandLine = new Scanner(System.in);

            System.out.print("Input the source file: ");
            sourceFile = new File(commandLine.nextLine());

            System.out.print("Input the target file: ");
            targetFile = new File(commandLine.nextLine());

            System.out.print("Input the string to find: ");
            oldString = commandLine.nextLine();

            System.out.print("Input the string to replace with: ");
            newString = commandLine.nextLine();
        }
        try {
            try (Scanner fileReader = new Scanner(sourceFile); PrintWriter fileWriter = new PrintWriter(targetFile)) {
                while(fileReader.hasNextLine()) {
                    fileWriter.write(fileReader.nextLine().replace(oldString, newString) + "\n");
                }
                System.out.print("\n-----The file was read and written-----\n");
            }
        } catch (Exception ex) {
            System.out.print("\n-----Something went horribly wrong-----\n");
            System.exit(666);
        }
        System.out.print("\n-----Successful Scanner and PrintWriter closure-----\n");
    }

}
