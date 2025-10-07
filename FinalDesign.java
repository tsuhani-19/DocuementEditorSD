import java.io.FileWriter;
import java.util.*;

// Interface for document elements
interface DocumentElement {
    String render();
}

// Text element
class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    public String render() {
        return text;
    }
}

// Image element
class ImageElement implements DocumentElement {
    private String imagepath;

    public ImageElement(String imagepath) {
        this.imagepath = imagepath;
    }

    public String render() {
        return "[Image: " + imagepath + "]";
    }
}

// New line element
class NewLine implements DocumentElement {
    public String render() {
        return "\n";
    }
}

// Tab element
class TabLine implements DocumentElement {
    public String render() {
        return "\t";
    }
}

// Document class
class Document {
    private List<DocumentElement> documentElements = new ArrayList<>();

    public void addElements(DocumentElement element) {
        documentElements.add(element);
    }

    public String render() {
        StringBuilder result = new StringBuilder();
        for (DocumentElement element : documentElements) {
            result.append(element.render());
        }
        return result.toString();
    }
}

// Persistence interface
interface Persistence {
    void save(String data);
}

// File-based persistence
class FileStorage implements Persistence {
    public void save(String data) {
        try {
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("✅ Document saved to document.txt");
        } catch (Exception e) {
            System.out.println("❌ Error: Unable to save the document.");
        }
    }
}

// Document editor
class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String cachedDocument = "";

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElements(new TextElement(text));
    }

    public void addImage(String imagepath) {
        document.addElements(new ImageElement(imagepath));
    }

    public void addNewLine() {
        document.addElements(new NewLine());
    }

    public void addTab() {
        document.addElements(new TabLine());
    }

    public String renderDocument() {
        if (cachedDocument.isEmpty()) {
            cachedDocument = document.render();
        }
        return cachedDocument;
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}

// Final test class
public class FinalDesign {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document, persistence);

        editor.addText("Hello, world!");
        editor.addNewLine();
        editor.addText("This is a real-world document editor example.");
        editor.addNewLine();
        editor.addTab();
        editor.addText("Indented text after a tab space.");
        editor.addNewLine();
        editor.addImage("picture.jpg");

        // Render and display the final document
        System.out.println("Rendered Document:\n" + editor.renderDocument());

        // Save document
        editor.saveDocument();
    }
}
