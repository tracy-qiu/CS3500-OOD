This ImageProcessor program follows a model, view, controller design.
The fundamental building block of an image is a Pixel. A Pixel class was designed to store the red,
green, and blue values, and max value of an individual Pixel. A 2D array of Pixels is used to
represent the pixels of an image and is passed into the Image class. An image object is defined by
the width, height, maxValue of the image and a 2D array of Pixels. All the images are stored in the
model using a hashmap. The hashmap stores the image labeled by a file name.

The controller communicates with the model to get the state of the images. The controller stores the
command interface which follows the command design pattern. The command interface only contains one
function to run the command. The greyscale, flip, and brighten commands implement the command
interface. The commands grayscale, flip, and brighten process the image and output a new image based
on the input parameters specific to the command such as the component type for grayscale and the
brightness change value for brighten. Since the commands are processing the images based on
functionality defined by the model, the strategy design pattern was used to abstract the command
functionality to the model. Within the model, the ImageProcessCommand interface runs the command
taking in the target image. The greyscale, flip, and brighten commands each implement the
ImageProcessCommand and apply the image and pixel methods on the input image. The run method of the
ImageProcessCommand returns the new processed image, such as a new vertically flipped image. In the
controller, each command gets the target starting image by fetching it from the model’s hashmap. An
instance of the ImageProcessCommand is created to apply the corresponding command to the image. The
image returned by the ImageProcessCommand is then added to the model’s hashmap of images. By
creating an interface for the image processing in the model, in the future, if we ever want to add
more image processing functionality, we can create a new class that implements the
ImageProcessCommand.

The load and save commands do not rely on model functionality and therefore do not implement the
ImageProcessCommand. The load and save command functionality are in the ImageUtils class in the
controller. The load command reads in a PPM file and stores the parsed rgb values into a Pixel array
which is then used to create an image object. This image object is then added to the model’s hashmap
of images. The save command gets the target image from the model’s hashmap of images and writes it
to a new ppm file through ImageUtils.

The view renders the state of the controller executing the commands. The view takes in an Appendable
that is appended to for each command called by the controller. For each command, the controller
calls the view to render a message if the command was successful or not. If the command was not
successful, the appropriate error message is rendered to the view.


#type this script when the program runs, or alternatively provide this file as a command line
argument

#load 2x2.ppm and call it 'original'
load src/2x2.ppm original

#flip original vertically
vertical-flip original vertical

#brighten original by adding 10
brighten 10 original brighten10

#create a greyscale using only the blue component, as an image bear-greyscale
blue-component original blue-greyscale

#flip the vertically flipped original horizontally
horizontal-flip vertical vertical-horizontal

#darken blue-greyscale by 10
brighten -10 blue-greyscale blue-greyscale-darken10

#save vertical-horizontal
save src/res/vertical-horizontal.ppm vertical-horizontal

#save blue-greyscale-darken10
save src/res/blue-greyscale-darken10.ppm blue-greyscale-darken10

#overwrite bear with another file
load src/2x3.ppm original


CITATION: 2x2 image is our own creation which we give permission to be used in this project.
