## Model

### Interfaces:

Pixel - represents a single pixel in an image.
PixelImage - an 2D image of pixels that can be interacted with in the processor.
ImageProcessor - The main model of this project where images can be loaded and processed.

### Classes:

StdPixel - implements the Pixel interface and represents pixels as rgb values
GridPixelImage - implements the PixelImage interface and represents an image as a 2D array.
BasicImageProcessor - implements the ImageProcessor and represents the loaded images and processed
images as
a hashmap of strings and PixelImages.


### Filters:

This folder is a new addition to HW5. It contains different kinds of filter matrices that can be
applied to the image.

---

## View

### Interfaces:

PixelImageView - so far only purpose of view in this processor is to transmit messages to the user,
so this
interface is fairly simple with just one method.

GUIView - An extension of PixelImageView that has methods to implement the GUI.

### Classes:

TerminalView - implements PixelImageView, transmits messages to the user via the terminal by default
or
any output provided to it.

SwingImageProcessorView - An implementation of the GUIView using the JSwing library.

HistoPanel - The JPanel housing the histogram for an image.

---

## Controller

### Interfaces:

ImageProcessorController - A controller that starts the image processor and conveys the user
commands to
the model.

### Classes:

ImageProcessorControllerImpl - implements ImageProcessorController, a non GUI type based controller
that accepts
input via a readable.

GUIController - An controller that works with any GUIView.

ButtonListener - An ActionListener for the buttons on the GUI.

Runnables - Runnables for every button provided.

---

## Command Design Pattern

### Interface:

ImageProcessorCmds - Commands on the ImageProcessorController that execute on the given model.

### Classes:

A command class for every command listed below and an abstract class that they inherit from
which implements ImageProcessorCmds.

---

### All specification in the "What to do" section of the assignment description have been completed.

---

## Design Changes

- Changed the load and save commands so that the ImageIO is handled in the controller, not the model.

---
# Using the Text Image Processor & Scripting

## Commands

**Note:-** if using relative paths all file paths are relative to the jar location which is in res.

### Our script that should be given as an input is name script.txt

### Do not provide scriptTest.txt as input to the jar(it only exists to test the main method)

| To do this      | Type this                          |
|-----------------|------------------------------------|
| Loading Image   | load filepath imagename            |
| Red Component   | red-component imagename destname   |
| Green Component | green-component imagename destname |
| Blue Component  | blue-component imagename destname  |
| Value           | value imagename destname           |
| Intensity       | intensity imagename destname       |
| Luma            | luma imagename destname            |
| Vertical Flip   | flip-vertical imagename destname   |
| Horizontal Flip | flip-horizontal imagename destname |
| Brighten Image  | brighten imagename destname factor |
| Darken Image    | darken imagename destname factor   |
| Blur Image      | blur imagename destname            |
| Sharpen Image   | sharpen imagename destname         |
| Greyscale Image | greyscale imagename destname       |
| Sepia Image     | sepia imagename destname           |
| Save Image      | save filepath imagename            |

---
The commands can be entered via an interactive terminal when run with one cmd line
argument as "-text",
or via a script file with the filePath as a string in the second cmd line argument 
followed by "-file".

To quit the image processor type 'q' or 'quit' irrespective of the case. The process also quits at
the end of
file when given a script as a command line argument.
---

## Citation Note

All the ppm files in the res folder have been created by the authors, and they are authorizing its
use
in this project.