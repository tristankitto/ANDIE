# **User Guide**

This is __ANDIE__, __A__  <b>N</b>on <b>D</b>estructive <b>I</b>mage <b>E</b>ditor. `cosc202.andie.ANDIE`. 

__ANDIE__ loads images and edits them with operations such as colour filters, brightness/contrast adjustment, rotations and reflections etc. These operations are stored in a stack to enable undoing, allowing the changes to be __non-destructive__ ie. reversible. Undone operations are stored on another stack, enabling redo.

 A list of functions and their uses can be found below:

## File
<img src=README_screenshots/file_screenshot.PNG width = "300" height = "325" align = "top">

- Open
    - The open option brings up a menu for a user to select an image to be opened and edited in **ANDIE**. Any image that is invalid (such as corrupt images), or any non-image file, will display an error to the user and prompt them to choose a different file instead. 
- Save
    - This function saves the edits made to an image in a new file (imagename.ops) alongside the original (unedited) image. Using the save function will not overwrite the original image with the new changes, it will only allow for the original image to be reopened inside **ANDIE** with its changes still showing and allowing the user to continue to remove or add more filters.
- Save As
    - This function is the same as save but gives the user the option to save their image to a different directory with a different name. This function prompts the user to type a name for their file and choose where to save it. It will then create a copy of the original (unedited) image and give it the name inputted by the user. It will also create a .ops file with the new name entered by the user.
- Export
    - Export allows the user to save the changes made to their image in a permanent manner. It will create a new image in a directory of the user's choice with a new name of the user's choice. This new image will have all of the filters applied in **ANDIE** showing and they will not be able to be removed. Using export does not destroy the original (unedited) image used before exporting so the user can still make changes and create a new exported image if they wish.
- Macro
    - Record Macro
        - Record Macro starts and stops the recording function for macros. When started, a red recording dot will be displayed in the top right corner of the application and any image operations applied to an image while recording will be added to a separate macro stack.
    - Export Macro
        - Export Macro functions similarly to Export and Save As. It will take the current Macro stack and export it to a new .ops file in your computer at a location and name of the user's choice.
    - Apply Macro
        - Apply Macro will prompt the user to choose a macro file from their computer. This file will then be applied to the image, allowing for all image operations stored within the macro to be applied to any image.
    - Reset Macro
        - Reset Macro will reset the macro stack, allowing a user to start their macro recording from scratch.
- Exit
    - Exit will exit **ANDIE**. If the user's image is unsaved then they will be prompted to save before closing, but have the option to decline.

## Edit
<img src=README_screenshots/edit_screenshot.PNG width = "300" height = "325" align = "top">

- Undo
    - The undo function allows the user to undo their most recent change to the current image. If there is nothing to undo then the user will be made aware of this with an informative pop-up box.
- Redo
    - The redo function allows the user to redo their most recently undone change to the current image. If there is nothing to redo then the user will be made aware of this with an informative pop-up box.

## Insert

- Draw
    - The draw function gives the user the ability to draw different shapes as well as lines and free drawing. Starting this operation puts **ANDIE** into drawing mode, allowing the user to draw as much as they like and converting the Tool Bar items into options for drawing. Drawing allows the user to draw rectangles, filled rectangles, ovals, filled ovals, lines and free draw as well as changing the colour and line width of all of these options as well. Any image operation that the user performs while in drawing mode, or if the users presses the "ESCAPE" key, it will end drawing mode.

- Text
    - Text allows the user to select an area of the image and then add text to that area. The text can have its font, font size and colour edited by the user.

## View
<img src=README_screenshots/view_screenshot.PNG width = "300" height = "325" align = "top">

- Change Zoom
    - This function will change the zoom of the image. The user is prompted with a slider which they can then move to adjust the zoom of the image. The zoom updates automatically as the slider is moved allowing the user to see the zoom without having to accept first. This does not make permanent changes to the image, it only changes the way the image is displayed inside of **ANDIE**.
- Zoom Full
    - This function will change the zoom of the user's image to be at 100% (i.e. it will be displayed in the image's true size). This does not make permanent changes to the image, it only changes the way the image is displayed inside of **ANDIE**.
- Rotate Clockwise
    - This will rotate the image clockwise by 90 degrees.
- Rotate Anticlockwise
    - This will rotate the image anticlockwise by 90 degrees.
- Rotate 180 Degrees
    - This will rotate the image by 180 degrees.
- Flip Horizontally
    - This will flip the image along its vertical axis, making the image horizontally flipped.
- Flip Vertically
    - This will flip the image along its horizontal axis, making the image vertically flipped.
- Resize
    - This function will change the size of the image. This, unlike zooming, will make a permanent change to the image (once exported or saved). This function uses a slider in the same way as zoom, allowing instant feedback to the user as it is moved. The minimum value allowed is 0% and the maximum is 200%.
- Crop
    - This function will put **ANDIE** into cropping mode. This dims the image and changes the cursor to a crosshair to make it easier for the user to see where they will be selecting on the image. The user can then click and drag their mouse over a section of their image which will then give them a subimage of the original with the same area as the one selected. Any image operation that the user performs while in cropping mode, or if the users presses the "ESCAPE" key, it will end cropping mode.

## Filter
<img src=README_screenshots/filter_screenshot.PNG width = "300" height = "325" align = "top">

- Mean Filter
    - This filter will apply a blur to the image based on the average colour of each pixel's neighbouring pixels. This filter depends on a radius input. The minimum value for the radius is 1 and the maximum is 10. The user selects the radius using a slider, once they have finished adjusting the slider the filter will apply to the image and then the user can either accept, continue adjusting, or cancel the action.
- Soft Blur
    - This filter will apply a soft blur that is constant for any image (i.e. it will blur any image by the same amount every time it is applied).
- Sharpen Filter
    - This filter will make an image appear sharper. This is done by increasing the differences between different colours.
- Gaussian Blur
    - This filter will apply a blur to the image based on a Gaussian formula. This filter depends on a radius input. The minimum value for the radius is 1 and the maximum is 10. The user selects the radius using a slider, once they have finished adjusting the slider the filter will apply to the image and then the user can either accept, continue adjusting, or cancel the action.
- Median Filter
    - This filter will apply a blur to the image based on the median colour of each pixel's neighbouring pixels. This filter depends on a radius input. The minimum value for the radius is 1 and the maximum is 10. The user selects the radius using a slider, once they have finished adjusting the slider the filter will apply to the image and then the user can either accept, continue adjusting, or cancel the action.
- Emboss
    - Emboss
        - There are 8 Emboss filters, each with a different direction (N, NE, E, etc.). Each filter applies an embossed effect to the image.
    - Sobel
        - Similar to the emboss filters the Sobel filters have a horizontal or vertical direction. These filters also apply a similar effect to emboss.

## Colour
<img src=README_screenshots/colour_screenshot.PNG width = "300" height = "325" align = "top">

- Greyscale
    - This filter will change the image's colours to be in greyscale instead of full colour.
- Invert colours
    - This filter will invert the image's colours. This is done by taking the RGB value of each pixel and setting the new RGB value to 255 (the maximum value for RGB) minus the current RGB value.
- Brightness/Contrast
    - This filter will change the brightness and contrast of the image. The user selects the brightness and contrast using 2 sliders. These sliders will automatically update the images brightness and contrast as they are moved and then the user can either accept, continue adjusting, or cancel the action.
- Remove solid background
    - This operation attempts to remove the background of an image. It will set the pixels that it determines to be the background as transparent pixels. This operation works best for images with solid, mostly monochromatic backgrounds.
- Remove transparency
    - This operation removes any transparent pixels in an image and replaces them with white pixels.

## Settings
<img src=README_screenshots/settings_screenshot.PNG width = "300" height = "325" align = "top">

- Theme
    - **ANDIE** has 2 theme options, light mode and dark mode. By default **ANDIE** is set to light mode. Changing the theme will make all GUI elements of **ANDIE** change into that selected theme.
- Language
    - Each option in the language menu changes the language of each UI element of **ANDIE**. For example, changing the language to Māori will display every menu name, menu item, and pop-up box (for errors or for user inputs) in Te Reo Māori. Each language option displays the language name in the current language, and beside that the name in the language itself. For example, if the current language is Spanish then the option to choose the English language will display as `Inglés (English)`, or if the current language is Japanese then the option to choose French will display as `フランス語 (Français)`. This allows people who only know one language to change **ANDIE** to their native language.
    - Language Preference
        - Whenever a user selects a language that language is automatically saved as the user's new default language. Every time the user reopens **ANDIE** they will find the program in the last language they selected and it will stay as that language until a new language is selected.

## Tool Bar
<img src=README_screenshots/toolbar_screenshot.PNG align = "top">

- The tool bar in **ANDIE** is used for multiple purposes. By default **ANDIE**'s Tool Bar displays with Open, Save, Undo, Redo, Crop, Change Zoom, Macros pop-up menu, Language pop-up menu and exit buttons. When entering drawing mode or text mode the tool bar will update to have new options more relevant to those modes, and then will revert back when those modes are exited.

## Keyboard Shortcuts
- A list of keyboard shortcuts for operations within **ANDIE** can be found below:
    - Ctrl + 1 = Rotate 180
    - Ctrl + A = Rotate Anticlockwise
    - Ctrl + Alt + Shift + A = Apply Macro
    - Ctrl + B = Brightness & Contrast
    - Ctrl + C = Rotate Clockwise
    - Ctrl + D = Median Filter
    - Ctrl + E = Export
    - Ctrl + F = Zoom Full
    - Ctrl + G = Gaussian Blur
    - Ctrl + Shift + G = Greyscale
    - Ctrl + H = Horizontal Flip
    - Ctrl + I = Invert Colours
    - Ctrl + M = Mean Filter
    - Ctrl + O = Open
    - Ctrl + P = Sharpen Filter
    - Ctrl + Q = Exit
    - Ctrl + R = Resize
    - Ctrl + Alt + Shift + R = Record Macro
    - Ctrl + S = Save
    - Ctrl + Shift + S = Save As
    - Ctrl + Alt + Shift + E = Export Macro
    - Ctrl + T = Soft Blur
    - Ctrl + V = Vertical Flip
    - Ctrl + Y = Redo
    - Ctrl + Z = Undo
    - Ctrl + Shift + Z = Change Zoom
    - Ctrl + Alt + L = Light Mode
    - Ctrl + Alt + D = Dark Mode
    - Ctrl + Alt + N = North emboss
    - Ctrl + Alt + E = East emboss
    - Ctrl + Alt + S = South emboss
    - Ctrl + Alt + W = West emboss
    - Ctrl + Alt + H = Horizontal sobel
    - Ctrl + Alt + V = Vertical sobel
    - Ctrl + Shift + D = Draw
    - Ctrl + Shift + T = Text
    - Ctrl + Shift + R = Remove background
    - Ctrl + Alt + T = Remove transparency

More information on this software and what it does can be found in the [COSC 202 LabBook](https://cosc202.cspages.otago.ac.nz/lab-book/COSC202LabBook.pdf#page=124).

# Additional Features

## Language Preferences
- Support for the user to save their language of choice has been implemented. This is done through writing to a text file each time a language is selected in the GUI, this file is then read every time the program is opened to check the last selected language and set the UI elements to that language by default. This allows a user to only have to change the language once upon first use of the program, as the default language is set to English on first run.

## Changeable themes
- **ANDIE** incorporates the FlatLaf library which provides the ability to change the theme of the GUI between light mode and dark mode.

## Free Draw
- The drawing function in **ANDIE** includes an extra "Free Draw" mode which allows the user to draw a line freely, similar to using a pen or paint brush.

## Remove Solid Background
- The remove solid background function tries to remove the background of an image as best as it can. This function is intended to be used on images with solid, monochromatic background rather than multicoloured backgrounds or backgrounds that blend in with the foreground.

## Remove Transparency
- This will remove all transparency in an image, making any transparent pixels into opaque, white pixels. This is effectively equivalent to converting an image from PNG to a different image format and is used mainly for exporting PNG images to non-PNG images within **ANDIE**.

## Text
- The text function in **ANDIE** allows the user to select an area to write text on then prompts them to insert their text. This text can have its font, font size and colour changed before being inputted.

# Individual member contributions

## Ada - Architect
- Image Resizing
- Filters with negative results
- Emboss and edge detection filters

## Joshua - Developer
- Exception Handling
- Mouse selection of rectangular regions
- Drawing rectangles, ovals and lines

## Matthew - Team Lead
- Median Filter
- Clockwise Rotation
- Anticlockwise Rotation
- 180 Degrees Rotation
- Horizontal Flip 
- Vertical Flip
- README Testing Documentation
- Macros for record and replay of operations
- Adding text to an image

## Shayna - UI/UX Designer
- Brightness Adjustment
- Contrast Adjustment
- Image Export
- Toolbar for common operations
- Theme 
- README acknowlegements 

## Tristan - Toolsmith
- Soft Blur Filter
- Invert Colours
- Sharpen Filter
- Gaussian Blur Filter
- Multilingual Support
- Exception and Error Avoidance/Handling
- Javadoc commenting
- README User Guide
- Keyboard shortcuts
- Extended filters
- Mouse selection of rectangular regions
- Crop to selection
- Drawing functions
- Free draw function
- Remove background
- Remove transparency

# Testing
Cross-platform testing was performed on Windows and Mac. Testing was done concurrently with software development, then scaled upwards in the week preceding the deadlines. 

Every team member was involved with testing to some degree. Team members tested their own code as it was written and after it was completed to confirm it was robust and satisfied the specifications. Team members also tested each other's code to double check integrity. After the bulk of the developing was completed, the team worked together to devise test cases and methods for a wide-scale approach.

## Test image
<img src=Test_images/pixilart-drawing.png width = "300" height = "300">

A white 100x100 pixels image was created, then black pixels were dotted around sporadically with sections of varying density of black pixels. This was used to test the following filters:
- Mean filter: the output should have the black pixels "bleeding" outwards, expanding in a small cloud of grey as the mean of black and white pixels is grey. The expected output was observed.
- Median filter: the output should have sparse black pixels being "outvoted" and turned white, while larger clumps of black pixels remain black. The expected output was observed.

## Image flip bug
![Penguin Image](Test_images/transparentPenguin_test.png)

Image flip and rotation operations were tested with square and rectangular images and worked without any issues, however a bug was encountered when vertically flipping the penguin image where a horizontal white line was created, bisecting the output image. It was found that this bug occurred because the image had an odd number of pixels in its height.

This bug was recreated by resizing the penguin image to also have an odd width of pixels and horizontally flipping it, which caused a vertical white line to appear down the centre. 

This bug was patched with additional code that runs if width or height is odd and ensures the middle line of pixels is copied over correctly.

## Gaussian Blur
Gaussian Blur on opaque images creates a black border and on transparent images, it cuts off that border. This is because the operation looks at a pixel's neighbours to determine the output colour, and pixels on the borders will not have enough of these neighbours. This bug will be handled in part 2 of this assignment. 

## 4k Images
![Cosmic Cat](Test_images/cosmic_cat.jpg)
This 3840 x 2160 image was used to test some of the more process intensive filters like Median Filter. Although they took a while to run, no issues were encountered.

## Corrupt Images
Corrupt images fail to open in ANDIE. This has been fixed with exception handling, and an informative pop-up box will be prompted when it occurs.

## GIFs
Animated GIFs open up as mostly monochrome still images with pixels of one other colour in the top portion of the image. This image can be edited as normal, but exporting it will leave it as this strange still.

Inanimate GIFs can be edited without issue.

# Some of the Exceptions Handled

## Performing any unintended function when a file is not open
If an image has not yet been opened in **ANDIE** then a pop-up will be displayed if the user tries to do anything that would normally affect an image. This includes applying any filter or transformation, save, export, etc.

## Attempting to Undo or Redo with empty stacks
This error was handled and a popup was shown to the user explaining that the undo or redo failed. After testing we decided that the popup was not user friendly and is not something that is commonly used when an undo or redo fails due to an empty stack. Because of this the popup was removed for empty stack exceptions while the error was still handled. A popup will still occur if undo or redo fails for reasons other than an empty stack.

## Opening non-supported files
Attempting to open any file that is not supported by **ANDIE** will result in a pop-up displaying this exception to the user and prompting them to open a different file. This works for non-image files as well as corrupted image files.

## Miscellaneous Exceptions
Any other exceptions such as a filter failing to apply or an image failing to export for an unknown or unpredictable reason are also handled. Pop-up boxes displaying what went wrong should appear to the user if an unforseen exception is to occur to inform them that their action has not worked correctly.

# Some of the Errors Handled

## Opening and Exiting when an image is unsaved
**ANDIE** now prompts the user to save their image before opening a new one or before exiting the program using the exit button in the file menu or the JFrame's exit button. The user can either save and continue, not save and continue, or cancel the action.

## Filters applied to newly opened images
When opening a new image, after already having an edited image open, the newly opened image would have the same filters now applied to it. This has been remedied and now any image that is opened will have no filters applied to it unless it has a **.ops** file.

## Overwriting an exisiting image/file
When a user tried to export an image or save a macro using the same name as an already existing file in the current directory then **ANDIE** would simply overwrite the original file. Now, **ANDIE** will prompt the user that they are trying to export a file with a name that already exists and confirm if they want to overwrite the original file or cancel the action.

## Inputting an invalid value for a filter
Filters with a radius or a percentage now use a slider for selection. This slider allows for a user to easily choose a value within the allotted range without any worry of misinput that could cause errors.

# Acknowlegements 
**ANDIE**  uses open source code library FlatLaf version 3.1.1 by FormDev Software GmbH
 https://www.formdev.com/flatlaf/

 **ANDIE** uses free icons from https://www.flaticon.com/free-icons/free 

## Attributions
 **ANDIE** uses a 'record icon' attributed to: <a href="https://www.flaticon.com/free-icons/record" title="record icons">Record icons created by Andrean Prabowo - Flaticon</a>