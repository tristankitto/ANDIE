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
- Image Rotations (Clockwise, Anticlockwise, 180)
- Image Flip (Horizontal and Vertical)

## Shayna - UI/UX Designer
- Brightness Adjustment
- Contrast Adjustment


## Tristan - Toolsmith
- Soft Blur Filter
- Invert Colours
- Sharpen Filter
- Gaussian Blur Filter
- Multilingual Support
- Error Avoidance/Handling


# Testing
Cross-platform testing was performed on Windows and Mac. Testing was done concurrently with software development, then scaled upwards in the week preceding the deadline for the first lot of deliverables. 

Every team member was involved with testing to some degree. Team members tested their own code as it was written and after it was completed to confirm it was robust and satisfied the specifications. Team members also tested each others' code to double check integrity. After the bulk of the developing was completed, the team worked together to devise test cases and methods for a wide-scale approach.

## Test image
![Test image](./pixilart-drawing.png)

A white 100x100 pixels image was created, then black pixels were dotted around sporadically with sections of varying density of black pixels. This was used to test the following filters:
- Mean filter: the output should have the black pixels "bleeding" outwards, expanding in a small cloud of grey as the mean of black and white pixels is grey. The expected output was observed.
- Median filter: the output should have sparse black pixels being "outvoted" and turned white, while larger clumps of black pixels remain black. The expected output was observed.

## Image flip bug
![Penguin Image](./transparentPenguin_test.png)

Image flip and rotation operations were tested with square and rectangular images and worked without any issues, however a bug was encountered when vertically flipping the penguin image which had an odd height of pixels where a horizontal white line was created bisecting the output image. 

This bug was recreated by resizing the penguin image to also have an odd width of pixels and horizontally flipping it, which caused a vertical white line to appear down the centre. 

This bug was patched with additional code that runs if width or height is odd and ensures the middle line of pixels are copied over correctly.

## Gaussian Blur
Gaussian Blur on opaque images creates a black border and on transparent images, it cuts off that border. This is because the operation looks at a pixel's neighbours to determine the output colour, and pixels on the borders will not have enough of these neighbours. This bug will be handled in part 2 of this assignment. 

## Components with no issues during testing:
 -

# Exceptions Handled


# Errors Handled

## Opening and Exiting when an image is unsaved
**ANDIE** now prompts the user to save their image before opening a new one or before exiting the program using the exit button in the file menu. The user can either save and continue, not save and continue, or cancel the action.

## Filters applied to newly opened images
When opening a new image, after already having an edited image open, the newly opened image would have the same filters now applied to it. This has been remedied and now any image that is opened will have no filters applied to it unless it has a **.ops** file.


# User Guide


# Deviations from Specifications

## Language Preferences
Support for the user to save their language of choice has been implemented. This is done through writing to a text file each time a language is selected in the GUI, this file is then read every time the program is opened to check the last selected language and set the UI elements to that language by default. This allows a user to only have to change the language once upon first use of the program, as the default language is set to English on first run.