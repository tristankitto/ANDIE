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


# User Guide


# Deviations from Specifications

## Language Preferences
Support for the user to save their language of choice has been implemented. This is done through writing to a text file each time a language is selected in the GUI, this file is then read every time the program is opened to check the last selected language and set the UI elements to that language by default. This allows a user to only have to change the language once upon first use of the program, as the default language is set to English on first run.

# Significant Code Refactoring

## createMenuBar
In the `Andie` class parts of the `createAndShowGUI` method have been moved out into a new public method called `createMenuBar`. This has been done to let classes other than `Andie` to remake the menu bar, allowing for multilingual support to take effect as soon as the language is chosen, rather than requiring the program to be restarted entirely.