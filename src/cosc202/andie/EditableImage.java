package cosc202.andie;

import java.util.*;
import java.io.*;
import java.awt.Cursor;
import java.awt.image.*;
import javax.imageio.*;

import javax.swing.*;

/**
 * <p>
 * An image with a set of operations applied to it.
 * </p>
 * 
 * <p>
 * The EditableImage represents an image with a series of operations applied to
 * it.
 * It is fairly core to the ANDIE program, being the central data structure.
 * The operations are applied to a copy of the original image so that they can
 * be undone.
 * This is what is meant by "A Non-Destructive Image Editor" - you can always
 * undo back to the original image.
 * </p>
 * 
 * <p>
 * Internally the EditableImage has two {@link BufferedImage}s - the original
 * image
 * and the result of applying the current set of operations to it.
 * The operations themselves are stored on a {@link Stack}, with a second
 * {@link Stack}
 * being used to allow undone operations to be redone.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
class EditableImage {

    /** The original image. This should never be altered by ANDIE. */
    private BufferedImage original;
    /**
     * The current image, the result of applying {@link ops} to {@link original}.
     */
    private BufferedImage current;
    /** The sequence of operations currently applied to the image. */
    private Stack<ImageOperation> ops;
    /** A memory of 'undone' operations to support 'redo'. */
    private Stack<ImageOperation> redoOps;
    /** The file where the original image is stored/ */
    private String imageFilename;
    /** The file where the operation sequence is stored. */
    private String opsFilename;
    /** ResourceBundle for multilingual support */
    ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.LanguageResources.LanguageBundle");
    /** String to store the extension of the image file, e.g. jpg, png, gif */
    private String extension;
    /**
     * Boolean to check if the save as operation is being performed instead of a
     * regular save operation
     */
    private boolean saveAs;
    /** The file where the macro is scored */
    private String macroFileName;
    /** Boolean to check if image operations need to be added to a macro */
    private boolean isMacroRecording = false;
    /** The stack used to store a recording macro */
    private static Stack<ImageOperation> macro = new Stack<ImageOperation>();
    /** The label containing the recording icon for macros */
    private static JLabel recordLabel;
    /** The last performed operation */
    private static ImageOperation lastOp;
    /** Boolean to check if an apply is because of the redo operation */
    Boolean redo = false;

    /**
     * <p>
     * Create a new EditableImage.
     * </p>
     * 
     * <p>
     * A new EditableImage has no image (it is a null reference), and an empty stack
     * of operations.
     * </p>
     */
    public EditableImage() {
        original = null;
        current = null;
        ops = new Stack<ImageOperation>();
        redoOps = new Stack<ImageOperation>();
        imageFilename = null;
        opsFilename = null;
    }

    /**
     * <p>
     * Creates a copy of an EditableImage object.
     * </p>
     * 
     * @param image The original input image.
     * @return The copy of the input image.
     */
    public static EditableImage copyImage(EditableImage image) {
        EditableImage imageCopy = new EditableImage();
        imageCopy.original = image.original;
        imageCopy.current = image.current;
        imageCopy.imageFilename = image.imageFilename;
        imageCopy.opsFilename = image.opsFilename;
        imageCopy.ops = image.ops;
        imageCopy.redoOps = image.redoOps;

        return imageCopy;
    }

    /**
     * <p>
     * Check if there is an image loaded.
     * </p>
     * 
     * @return True if there is an image, false otherwise.
     */
    public boolean hasImage() {
        return current != null;
    }

    /**
     * <p>
     * Make a 'deep' copy of a BufferedImage.
     * </p>
     * 
     * <p>
     * Object instances in Java are accessed via references, which means that
     * assignment does
     * not copy an object, it merely makes another reference to the original.
     * In order to make an independent copy, the {@code clone()} method is generally
     * used.
     * {@link BufferedImage} does not implement {@link Cloneable} interface, and so
     * the
     * {@code clone()} method is not accessible.
     * </p>
     * 
     * <p>
     * This method makes a cloned copy of a BufferedImage.
     * This requires knoweldge of some details about the internals of the
     * BufferedImage,
     * but essentially comes down to making a new BufferedImage made up of copies of
     * the internal parts of the input.
     * </p>
     * 
     * <p>
     * This code is taken from StackOverflow:
     * <a href=
     * "https://stackoverflow.com/a/3514297">https://stackoverflow.com/a/3514297</a>
     * in response to
     * <a href=
     * "https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage</a>.
     * Code by Klark used under the CC BY-SA 2.5 license.
     * </p>
     * 
     * <p>
     * This method (only) is released under
     * <a href="https://creativecommons.org/licenses/by-sa/2.5/">CC BY-SA 2.5</a>
     * </p>
     * 
     * @param bi The BufferedImage to copy.
     * @return A deep copy of the input.
     */
    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * <p>
     * Open an image from a file.
     * </p>
     * 
     * <p>
     * Opens an image from the specified file.
     * Also tries to open a set of operations from the file with <code>.ops</code>
     * added.
     * So if you open <code>some/path/to/image.png</code>, this method will also try
     * to
     * read the operations from <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param filePath The file to open the image from.
     * @throws Exception If something goes wrong.
     */
    public void open(String filePath) throws Exception {
        imageFilename = filePath;
        opsFilename = imageFilename + ".ops";
        File imageFile = new File(imageFilename);

        original = ImageIO.read(imageFile);
        current = deepCopy(original);

        try {
            FileInputStream fileIn = new FileInputStream(this.opsFilename);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            // Silence the Java compiler warning about type casting.
            // Understanding the cause of the warning is way beyond
            // the scope of COSC202, but if you're interested, it has
            // to do with "type erasure" in Java: the compiler cannot
            // produce code that fails at this point in all cases in
            // which there is actually a type mismatch for one of the
            // elements within the Stack, i.e., a non-ImageOperation.
            @SuppressWarnings("unchecked")
            Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();
            ops = opsFromFile;
            redoOps.clear();
            objIn.close();
            fileIn.close();
        } catch (Exception ex) {
            // do nothing, image just has no .ops file
        }
        this.refresh();
        extension = Andie.imageFilepath.substring(1 + Andie.imageFilepath.lastIndexOf(".")).toLowerCase();
    }

    /**
     * <p>
     * Save an image to file.
     * </p>
     * 
     * <p>
     * Saves an image to the file it was opened from, or the most recent file saved
     * as.
     * Also saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save
     * the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @throws Exception If something goes wrong.
     */
    public void save() throws Exception {
        try {
            if (this.opsFilename == null) {
                this.opsFilename = Andie.imageFilepath + ".ops";
            }
            // Write image file based on file extension
            ImageIO.write(original, extension, new File(imageFilename + (saveAs ? ("." + extension) : "")));
            // Write operations file
            FileOutputStream fileOut = new FileOutputStream(this.opsFilename);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(ops);
            objOut.close();
            fileOut.close();
            Andie.saved = true;
        } catch (NullPointerException e) {
            Tools.errorMessage(e, "fileUnopenedError");
        } catch (Exception e) {
            Tools.errorMessage(e, "fileSaveError");
        }
        saveAs = false;
    }

    /**
     * <p>
     * Exports an image to chosen file
     * </p>
     * 
     * <p>
     * Exports an image to the file location of the user's choosing. This method
     * will save the file to the same file type as the extension inputted by the
     * user when exporting the file.
     * </p>
     * 
     * @param imageFilename The file location to export the image to.
     * @param extension     The file type/file extension to export the image as.
     * @throws Exception If something goes wrong.
     */
    public void exportImage(String imageFilename, String extension) throws Exception {
        try {
            ImageIO.write(current, extension, new File(imageFilename));
        } catch (NullPointerException e) {
            Tools.errorMessage(e, "fileUnopenedError");
        } catch (Exception e) {
            Tools.errorMessage(e, "fileExportError");
        }
    }

    /**
     * <p>
     * Exports an image to chosen file
     * </p>
     * 
     * <p>
     * Exports an image to the file location of the user's choosing. This method
     * will save the file to the same file type as the original image.
     * </p>
     * 
     * @param imageFilename The file location to export the image to.
     * @throws Exception If something goes wrong.
     */
    public void exportImage(String imageFilename) throws Exception {
        try {
            ImageIO.write(current, this.extension, new File(imageFilename + "." + this.extension));
        } catch (NullPointerException e) {
            Tools.errorMessage(e, "fileUnopenedError");
        } catch (Exception e) {
            Tools.errorMessage(e, "fileExportError");
        }
    }

    /**
     * <p>
     * Save an image's operations to a speficied file.
     * </p>
     * 
     * <p>
     * Saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param imageFilename The file location to save the image to.
     * @throws Exception If something goes wrong.
     */
    public void saveAs(String imageFilename) throws Exception {
        this.imageFilename = imageFilename;
        this.opsFilename = imageFilename + "." + extension + ".ops";
        saveAs = true;
        save();
    }

    /**
     * <p>
     * Apply an {@link ImageOperation} to this image.
     * </p>
     * 
     * @param op The operation to apply.
     */
    public void apply(ImageOperation op) {
        try {
            Andie.frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            current = op.apply(current);
            ops.add(op);
            if (isMacroRecording) {
                macro.add(op);
            }
            Andie.saved = false;
            if (!redo) {
                redoOps.clear();
            }
            redo = false;
            Andie.frame.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            Andie.frame.setCursor(Cursor.getDefaultCursor());
            Tools.errorMessage(e, "fileApplyError");
        }
    }

    /**
     * <p>
     * Apply a temporary {@link ImageOperation} to this image.
     * </p>
     * 
     * <p>
     * Applies image operations to an image but does not add the operation to the
     * stack.
     * </p>
     * 
     * @param op The operation to apply.
     */
    public void tempApply(ImageOperation op) {
        Andie.frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        current = op.apply(current);
        redoOps.clear();
        lastOp = op;
        Andie.frame.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Adds the last performed {@link ImageOperation} to the ops stack. This is used
     * to make a temporary apply into a permanent one without reapplying the whole
     * operation.
     */
    public void addLastOp() {
        ops.add(lastOp);
    }

    /**
     * <p>
     * Undo the last {@link ImageOperation} applied to the image.
     * </p>
     */
    public void undo() {
        try {
            redoOps.push(ops.pop());
            refresh();
            Andie.saved = false;
            if (isMacroRecording) {
                macro.pop();
            }
        } catch (EmptyStackException e) {
            System.out.println("Failed to undo or nothing to undo: " + e);
        } catch (Exception ex) {
            Tools.errorMessage(ex, "fileUndoError");
        }
    }

    /**
     * <p>
     * Reapply the most recently {@link undo}ne {@link ImageOperation} to the image.
     * </p>
     */
    public void redo() {
        try {
            redo = true;
            apply(redoOps.pop());
        } catch (EmptyStackException e) {
            System.out.println("Failed to redo or nothing to redo: " + e);
        } catch (Exception ex) {
            Tools.errorMessage(ex, "fileRedoError");
        }
    }

    /**
     * <p>
     * Get the current image after the operations have been applied.
     * </p>
     * 
     * @return The result of applying all of the current operations to the
     *         {@link original} image.
     */
    public BufferedImage getCurrentImage() {
        return current;
    }

    /**
     * <p>
     * Reapply the current list of operations to the original.
     * </p>
     * 
     * <p>
     * While the latest version of the image is stored in {@link current}, this
     * method makes a fresh copy of the original and applies the operations to it in
     * sequence.
     * This is useful when undoing changes to the image, or in any other case where
     * {@link current}
     * cannot be easily incrementally updated.
     * </p>
     */
    private void refresh() {
        current = deepCopy(original);
        for (ImageOperation op : ops) {
            current = op.apply(current);
        }
    }

    /**
     * <p>
     * Clears {@link ops} and {@link redoOps} stacks.
     * </p>
     * 
     * <p>
     * This method will remove any filters currently in the {@link ops} or
     * {@link redoOps} stacks.
     * This is useful for removing any edits on the stacks before opening a new
     * image.
     * </p>
     */
    public static void clearStacks(EditableImage image) {
        image.ops.clear();
        image.redoOps.clear();
    }

    /**
     * <p>
     * Records and stops recording a macro.
     * <p>
     */
    public void recordMacro() {
        isMacroRecording = !isMacroRecording;

        ImageIcon recordIcon;
        recordIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icons/record.png"));
        recordLabel = new JLabel(recordIcon);


        if (isMacroRecording) {
            // add recording icon to tool bar
            Andie.menuBar.add(Box.createHorizontalGlue());
            Andie.menuBar.add(recordLabel);
            Andie.frame.setVisible(true);
        } else {
            // remove recording icon from tool bar
            Andie.menuBar.remove(recordLabel);
            Andie.createMenuBar();
            Andie.frame.setVisible(true);
        }

    }

    /**
     * <p>
     * Export a macro.
     * <p>
     */
    public void exportMacro(String macroFileName) {
        this.macroFileName = macroFileName + ".ops";

        try {
            FileOutputStream fileout = new FileOutputStream(this.macroFileName);
            ObjectOutputStream objout = new ObjectOutputStream(fileout);
            objout.writeObject(EditableImage.macro);
            objout.close();
            fileout.close();

            // stop recording macros
            isMacroRecording = false;
            Andie.toolBar.remove(recordLabel);
            Andie.createToolBar();
            Andie.frame.setVisible(true);
        } catch (Exception e) {
            Tools.errorMessage(e, "fileMacroExportError");
        }
    }

    /**
     * <p>
     * Apply a macro.
     * <p>
     */
    public void applyMacro(String macroPath) throws Exception {
        try {
            FileInputStream fileIn = new FileInputStream(macroPath);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();
            ops.addAll(opsFromFile);
            refresh();
            objIn.close();
            fileIn.close();
        } catch (Exception e) {
            Tools.errorMessage(e, "fileMacroApplyError");
        }
    }

    /**
     * Resets macro stack so the macros can be recorded from scratch.
     */
    public void resetMacro() {
        try {

            macro.clear();
        } catch (Exception e) {
            Tools.errorMessage(e, "resetMacroError");
        }
    }
}
