import java.util.Scanner;

public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');

        // Ensure dot exists and is not the last character
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        // Extract and isolate extension
        String extension = filename.substring(lastDotIndex + 1);

        // Compare extension case-insensitively against valid criteria
        if (extension.equalsIgnoreCase("pdf") || 
            extension.equalsIgnoreCase("docx") || 
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        
        String result = validateFileExtension(filename);
        System.out.println(result);
        
        scanner.close();
    }
}
