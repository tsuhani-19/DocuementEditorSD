
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class DocumentEditor {

    private List<String> documentElements;
    private String renderedDocument;

    public DocumentEditor() {
        documentElements = new ArrayList<>();
        renderedDocument = "";
    }

    public void addText(String text) {
        documentElements.add(text);
    }

    public void addImage(String imagepath) {
        documentElements.add(imagepath);
    }

    public String renderedDocument() {
        if (renderedDocument.isEmpty()) {
            StringBuilder result = new StringBuilder();

            for (String elements : documentElements) {
                if (elements.length() > 4
                        && (elements.endsWith(".jpg") || elements.endsWith(".png"))) {
                    result.append("[Image : ").append(elements).append("] \n");

                } else {
                    result.append(elements).append("\n");
                }
            }
            renderedDocument = result.toString();
        }
        return renderedDocument;
    }

    public void savetoFile() {
        try {
            FileWriter writer = new FileWriter("documents.txt");
            writer.write(renderedDocument());
            writer.close();

            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to open file for writing.");
        }
    }
}

public class InitialDesign {

    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();
        editor.addText("Hello World");
        editor.addImage("picture.jpg");

        editor.addText("This is Document Editor ");;
        editor.addText("This is the intital System design of an Document Editor.");
        editor.addImage("Student.png");
        editor.addImage("Images");
        editor.addText("Imagesss.png");

        System.out.println(editor.renderedDocument());
        editor.savetoFile();
        
    }
}
