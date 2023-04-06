# ANDIE

This is __ANDIE__, __A__  <b>N</b>on <b>D</b>estructive <b>I</b>mage <b>E</b>ditor. `cosc202.andie.ANDIE`. 

__ANDIE__ loads images and edits them with  operations such as colour filters, brightness/contrast adjustment, rotations and reflections etc. These operations are stored in a stack to enable undoing, allowing the changes to be __non-destructive__ ie. reversible. Undone operations are stored on another stack, enabling redo.

More information on this software and what it does can be found in the [COSC 202 LabBook](https://cosc202.cspages.otago.ac.nz/lab-book/COSC202LabBook.pdf#page=124).

# Individual member contributions

## Ada - Architect
- Image Resizing

## Joshua - Developer
- Exception Handling

## Matthew - Team Lead
- Median Filter
- Clockwise Rotation
- Anticlockwise Rotation
- 180 Degrees Rotation
- Horizontal Flip 
- Vertical Flip

## Shayna - UI/UX Designer
- Brightness Adjustment
- Contrast Adjustment
- Image Export


## Tristan - Toolsmith
- Soft Blur Filter
- Invert Colours
- Sharpen Filter
- Gaussian Blur Filter
- Multilingual Support
- Exception and Error Avoidance/Handling
- Javadoc commenting


# Testing
Cross-platform testing was performed on Windows and Mac. Testing was done concurrently with software development, then scaled upwards in the week preceding the deadline for the first lot of deliverables. 

Every team member was involved with testing to some degree. Team members tested their own code as it was written and after it was completed to confirm it was robust and satisfied the specifications. Team members also tested each others' code to double check integrity. After the bulk of the developing was completed, the team worked together to devise test cases and methods for a wide-scale approach.

## Test image
![Test Image](./pixilart-drawing.png)

A white 100x100 pixels image was created, then black pixels were dotted around sporadically with sections of varying density of black pixels. This was used to test the following filters:
- Mean filter: the output should have the black pixels "bleeding" outwards, expanding in a small cloud of grey as the mean of black and white pixels is grey. The expected output was observed.
- Median filter: the output should have sparse black pixels being "outvoted" and turned white, while larger clumps of black pixels remain black. The expected output was observed.

## Image flip bug
![Penguin Image](./transparentPenguin_test.png)

Image flip and rotation operations were tested with square and rectangular images and worked without any issues, however a bug was encountered when vertically flipping the penguin image where a horizontal white line was created bisecting the output image. It was found that this bug occurred because the image had an odd number of pixels in its height.

This bug was recreated by resizing the penguin image to also have an odd width of pixels and horizontally flipping it, which caused a vertical white line to appear down the centre. 

This bug was patched with additional code that runs if width or height is odd and ensures the middle line of pixels is copied over correctly.

## Gaussian Blur
Gaussian Blur on opaque images creates a black border and on transparent images, it cuts off that border. This is because the operation looks at a pixel's neighbours to determine the output colour, and pixels on the borders will not have enough of these neighbours. This bug will be handled in part 2 of this assignment. 

## 4k Images
![Cosmic Cat](./cosmic_cat.jpg)
This 3840 x 2160 image was used to test some of the more process intensive filters like Median Filter. Although they took a while to run, no issues were encountered.

## Corrupt Images
Corrupt images fail to open in ANDIE. This has been fixed with exception handling, and an informative pop-up box will be prompted when it occurs.

## GIFs
Animated GIFs open up as mostly monochrome still images with pixels of one other colour in the top portion of the image. This image can be edited as normal, but exporting it will leave it as this strange still.

Inanimate GIFs can be edited without issue.

## Components with no issues during testing:
 - Sharpen Filter
 - Median Filter
 - Brightness Adjustment
 - Contrast Adjustment
 - Multilingual Support
 - Image Resize
 - Image Rotations
 - Image Export
 - Exception Handling
 - Other Error Avoidance/Handling

# Exceptions Handled

## Performing any unintended function when a file is not open
If an image has not yet been opened in **ANDIE** then a pop-up will be displayed if the user tries to do anything that would normally effect an image. This includes applying any filter or transformation, save, export, etc.

## Attempting to Undo or Redo with empty stacks
If the Ops stack is empty and the user tries to undo, and the same for redoOps and redo, then a pop-up will be displayed informing the user that there is no action to undo or redo.

## Opening non-supported files
Attempting to open any file that is not supported by **ANDIE** will result in a pop-up displaying this exception to the user and prompting them to open a different file. This works for non-image files as well as corrupted image files.

## Miscellaneous Exceptions
Any other exceptions such as a filter failing to apply or an image failing to export for an unknown or unpredictable reason are also handled. Pop-up boxes displaying what went wrong should appear to the user if an unforseen exception is to occur to inform them that their action has not worked correctly.

# Errors Handled

## Opening and Exiting when an image is unsaved
**ANDIE** now prompts the user to save their image before opening a new one or before exiting the program using the exit button in the file menu. The user can either save and continue, not save and continue, or cancel the action.

## Filters applied to newly opened images
When opening a new image, after already having an edited image open, the newly opened image would have the same filters now applied to it. This has been remedied and now any image that is opened will have no filters applied to it unless it has a **.ops** file.

## Overwriting an exisiting image
When a user tried to export an image using the same name as an already existing image in the current directory then **ANDIE** would simply overwrite the original image. Now, **ANDIE** will prompt the user that they are trying to export an image with a name that already exists and confirm if they want to overwrite the original image or cancel the action.

## Inputting an invalid value for a filter
Filters with a radius or a percentage now have some slight error handling wherein the user is now informed that if they enter an invalid value then the value passed to the filter will default to a specific value instead.


# Deviations from Specifications

## Language Preferences
Support for the user to save their language of choice has been implemented. This is done through writing to a text file each time a language is selected in the GUI, this file is then read every time the program is opened to check the last selected language and set the UI elements to that language by default. This allows a user to only have to change the language once upon first use of the program, as the default language is set to English on first run.

# Significant Code Refactoring

## createMenuBar
In the `Andie` class parts of the `createAndShowGUI` method have been moved out into a new public method called `createMenuBar`. This has been done to let classes other than `Andie` to remake the menu bar, allowing for multilingual support to take effect as soon as the language is chosen, rather than requiring the program to be restarted entirely.


# **User Guide**
**ANDIE** has many filters and functions to be used to transform and apply filters to images. A list of functions and their uses can be found below:

## File
- Open
    - The open option brings up a menu for a user to select an image to be opened and edited in **ANDIE**. Any image that is invalid (such as corrupt images), or any non-image file, will display an error to the user and prompt them to choose a different file instead. 
- Save
    - This function saves the edits made to an image in a new file (imagename.ops) along side the original (undedited) image. Using the save function will not overwrite the original image with the new changes, it will only allow for the original image to be reopened inside **ANDIE** with its changes still showing and allowing the user to continue to remove or add more filters.
- Save As
    - This function is the same as Save but gives the user the option to save their image to a different directory with a different name. This function prompt the user to type a name for their file and choose where to save it. It will then create a copy of the original (unedited) image and give it the name inputted by the user. It will also create a .ops file with the new name entered by the user.
- Export
    - Export allows the user to save the changes made to their image in a permanent manner. It will create a new image in a directory of the users choice with a new name of the user's choice. This new image will have all of the filters applied in **ANDIE** showing and they will not be able to be removed. Using export does not destroy the original (unedited) image used before exporting so the user can still make changes and create a new exported image if they wish.
- Exit
    - Exit will exit **ANDIE**. If the user's image is unsaved then they will be prompted to save before closing, but have the option to decline.

## Edit
- Undo
    - The undo function allows the user to undo their most recent change to the current image. If there is nothing to undo then the user will be made aware of this.
- Redo
    - The redo function allows the user to redo their most recently undone change to the current image. If there is nothing to redo then the user will be made aware of this.

## View
- Zoom In
    - This function will zoom in the user's image by 10%. This does not make permanent changes to the image, it rather just changes the way the image is displayed inside of **ANDIE**.
- Zoom Out
    - This function will zoom out the user's image by 10%. This does not make permanent changes to the image, it rather just changes the way the image is displayed inside of **ANDIE**.
- Zoom Full
    - This function will change the zoom of the user's image to be at 100$ (i.e. it will be displayed in the image's true size). This does not make permanent changes to the image, it rather just changes the way the image is displayed inside of **ANDIE**.
- Rotate Clockwise
    - This will rotate the image clockwise by 90 degrees.
- Rotate Anticlockwise
    - This will rotate the image anticlockwise by 90 degrees.
- Rotate 180 Degrees
    - This will rotate the image by 180 degrees.
- Flip Horizontally
    - This will flip the image along its vertical axis, make the image be horizontally flipped.
- Flip Vertically
    - This will flip the image along its horizontal axis, make the image be vertically flipped.
- Resize
    - This function will change the size of the image. This, unlike zooming, will make a permanent change to the image (if the image is exported or saved). This function uses an input taken from the user, the minimum value allowed is 0% and the maximum is 200%.

## Filter
- Mean Filter
    - This filter will apply a blur to the image based on the average colour of each pixel's neighbouring pixels. This filter depends on a radius input, the minimum value for the radius is 1 and the maximum is 10.
- Soft Blur
    - This filter will apply a soft blur that is constant for any image (i.e. it will blur any image by the same amount every time it is applied).
- Sharpen Filter
    - This filter will make an image appear sharper. That is, the pixels of the image will be made to have greater differences between different colours.
- Gaussian Blur
    - This filter will apply a blur to the image based on a Gaussian formula. This filter depends on a radius input, the minimum value for the radius is 1 and the maximum is 10.
- Median Filter
    - This filter will apply a blur to the image based on the median colour of each pixel's neighbouring pixels. This filter depends on a radius input, the minimum value for the radius is 1 and the maximum is 10.

## Colour
- Greyscale
    - This filter will change the image's colours to be in greyscale instead of full colour.
- Invert colours
    - This filter will invert the image's colours. That is, it will take the RGB value of each pixel and set the new RGB value to 255 (the maximum value for RGB) minus the current RGB value.
- Brightness/Contrast
    - This filter will change the brightness and contrast of the image. The user will be prompted to input a percentage change for the brightness and then a percentage change for the contrast. The minimum percentage for both brightness and contrast is -100% and the maximum for both is 100%. 0% brightness or contrast will leave the image unchanged.

## Language
- Languages
    - Each option in the language menu simply changes the language of each UI element of **ANDIE**. For example, changing the language to Māori will display every menu name, menu item, and pop-up box (for errors or for user inputs) in Te Reo Māori. The same goes for all languages available. Each language option displays the language name in the current language, and beside that the name in the language itself. For example, if the current language is Spanish then the option to choose the English language will display as `Inglés (English)`, or if the current language is Japanese then the option to choose French will display as `フランス語 (Français)`.
- Language Preference
    - Whenever a user selects a language that language is automatically saved as the user's new default language. Every time the user reopens **ANDIE** they will find the program in the last language they selected and it will stay as that language until a new language is selected.