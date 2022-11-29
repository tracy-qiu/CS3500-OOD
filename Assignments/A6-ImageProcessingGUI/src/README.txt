This ImageProcessor program follows a model, view, controller design.
The fundamental building block of an image is a Pixel. A Pixel class was designed to store the red,
green, and blue values, and max value of an individual Pixel. A 2D array of Pixels is used to
represent the pixels of an image and is passed into the Image class. An image object is defined by
the width, height, maxValue of the image and a 2D array of Pixels. All the images are stored in the
model using a hashmap. The hashmap stores the image labeled by a file name.

The controller communicates with the model to get the state of the images. The controller stores the
command interface which follows the command design pattern. The command interface only contains one
function to run the command. The greyscale, flip, brighten, blur, sharpen, and sepia commands
implement the command interface. The commands grayscale, flip, brighten, blur, sharpen, and sepia
process the image and output a new image based on the input parameters specific to the command such
as the component type for grayscale and the brightness change value for brighten. Since the commands
are processing the images based on functionality defined by the model, the strategy design pattern
was used to abstract the command functionality to the model. Within the model,
the ImageProcessCommand interface runs the command taking in the target image.
The greyscale, flip, brighten, blur, sharpen, and sepia commands each implement the
ImageProcessCommand and apply the image and pixel methods on the input image. The run method of the
ImageProcessCommand returns the new processed image, such as a new vertically flipped image. In the
controller, each command gets the target starting image by fetching it from the model’s hashmap. An
instance of the ImageProcessCommand is created to apply the corresponding command to the
image. The image returned by the ImageProcessCommand is then added to the model’s hashmap of images.
By creating an interface for the image processing in the model, in the future, if we ever want to
add more image processing functionality, we can create a new class that implements the
ImageProcessCommand.

The greyscale and sepia commands use color transformations to implement.
The new greyscale command is the equivalent of the luma-component command. The greyscale can be
represented in matrix form and the linear color transformation equation is shown below.
r' = g' = b' = 0.2126r + 0.7152g + 0.0722b
The sepia command also uses color transformation with a different matrix to multiply the pixel
values with.

The blur and sharpen commands are implemented using filtering. Filtering changes the value of a
pixel depending on the values of its neighbors. This is done by placing a kernel over each channel
(red, green, blue) of each pixel of the image to calculate the new pixel value. When the kernel is
placed over an image, the result of the filter is calculated by multiplying each corresponding
number in the kernel with the value of the pixel and summing them all to get the new value of that
pixel's channel. If the kernel overlaps outside of the bounds of the image, those values are not
included in the sum. Blur and sharpen each have their own defined kernels that are used to filter
the images.

The load and save commands do not rely on model functionality and therefore do not implement the
ImageProcessCommand. The load and save command functionality are in the ImageUtils class in the
controller. The load command can read in a PPM, PNG, JPEG, or BMP file and stores the parsed rgb
values into a Pixel  array which is then used to create an image object. This image object is then
added to the model’s hashmap of images. The save command gets the target image from the model’s
hashmap of images and writes it to a new file of specified file type through ImageUtils.

The view renders the state of the controller executing the commands. The view is displayed using a
graphical user interface (GUI) with buttons to choose specific functionality that the user wants to
apply to an image and the model. Essentially, the GUI is able to communicate with the controller
by sending ActionEvents triggered by the user. These are then transmitted to the controller,
causing it to execute certain commands on the model, ultimately changing a given image. The load,
save, and brighten commands require an input from the user, so the view has a method to transmit
the input to the controller which is used to execute the image processing function object. If there
are any errors in the program, they will be caught and displayed as a popup window to the user. For
example if the user puts in a string instead of an integer to the brighten text box, this will
display an error message asking the user to try again. This design utilizes an action listener to
detect clicks and inputs from the view and transmits that to the controller to execute the image
processing object which in turn calls the model to transform the image.

CITATION: 2x2 image and stick figure image is our own creation which we give permission to be used
in this project.

The milky-way JPG image that we used is from Google. Here is the link to the image (Citation)
It is a free image from Pexel.com (can be used by anyone).
https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fbackground%2520image%2F&
psig=AOvVaw1f1_2QjTgQkszVwSORGQgR&ust=1669312046289000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCKDH
g47uxPsCFQAAAAAdAAAAABAE
