We were able to successfully implement the image mosaic functionality utilizing this group's
current design. This functionality is supported through the terminal/command script as well
as the GUI.

We first created a new interface and class in the filters package to contain the functionality
for the image mosaic command. This followed a similar structure to how this group implemented
the color transformation and filter functionality. Within the ImageMosaic function object, there is
a createMosaic method that returns a new mosaic-ed PixelImage. This method is then called in
the GridPixelImage class similar to matrixify and filterify. However, we decided to have the
functionality contained in the ImageMosaic function object, rather than having it present in the
GridPixelImage class like greyscale, brighten and flip.


The mosaic transformation functionality was added to the controller by creating a new command object
that extends the abstract commands. A new MosaicCmd was created that extends the AbstractCmd and
is used for text scanner user input. A new MosaicButtonAction was created that extends the
AbstractButtonAction class and is used for the GUI button. Within the MosaicCmd and
MosaicButtonAction in the controller, the mosaicImage method was called on the model to apply the
mosaic transformation to either the specified image or the image currently being displayed by the
GUI. The mosaic functionality was added to the list of known commands for the text input as well as
the GUI as a button.