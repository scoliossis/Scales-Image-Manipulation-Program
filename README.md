# Scale's Image Manipulation Program
https://www.bhasvic.ac.uk/media/pdf/computer-science-al-170844-specification-accredited-a-level-gce-computer-science-h446-874.pdf

---

## <u>Table of Contents</u>
- [Analysis of the Problem](#Analysis-of-the-Problem)
    - [Problem Identification](#Problem-Identification)
      - etc (fill this at the end, im sure intellij doesn't have a thing to auto fill it)


# Analysis of the Problem
## Problem Identification
I am creating an image manipulation program.
This is a type of program which allows the user to scale, crop, rotate and draw on existing images or a blank canvas to produce new images.
This project is amenable to computational methods due to the possibility of performing actions which are challenging on paper.
This includes erasing parts of an image and editing separate layers, which is why graphic design jobs require an image manipulation program.
The aim of this project is to create a program which is straightforward to learn and use, yet still capable of complex image manipulation.
Image manipulation programs are useful for professional jobs requiring image creation and tweaking as well as hobbyists pursuing creative projects.
Since the program is intended to be used by a wide range of people, responses from users are imperative to the success of the project.

## Stakeholders
My project is made to be used as a general purpose image manipulation project.
This means that there is variety in the skill level and features of my stakeholders which all need to be considered.
Both hobbyists and professionals need to be accounted for when creating the project; this means features should be easily accessible with a high skill ceiling.
Different fields of creativity, such as graphic designers and artists, can be accounted for separately; however, it is challenging to create a program simple enough for beginners and complex enough for experts.
### General Purpose
- Being able to draw and erase is useful for artists and designers
- Cropping and scaling images are useful outside creative jobs, such as teachers wanting to resize handouts.
- Adding text to images is desired for news publications creating a lead image
### Hobbyists
- They will require a simple GUI which is easy to navigate on first use
- Runs well on low-quality setups as hobbyists do not necessarily have good hardware
- Everything should be easy to understand without guides
- Functions should be intuitive with experimentation with configuration
### Professionals
- Each action should be configurable to meet any desires of the user
- Complex effects should be easily applicable to the image
- Documentation should be written to assist professionals in finding how their desired goal is achievable

## Research
### GNU Image Manipulation Program (GIMP)
GIMP is a popular open source image manipulation program which is capable of complex effects.
It allows the user to perform any image manipulation they can imagine, as simple as cropping and as complex as blur and antialiasing.
The program has 10 headers which each have hundreds of options below them which allow the user to alter the image in some way.
The sidebar contains 16 modes, each containing submodules, which, when clicked, change the operation the cursor performs.
All these settings mean that with enough practice there are more than enough tools to do whatever is needed to an image.
An issue with all this customisation is that the GUI is cluttered.
There is far too much showing on the screen, which makes navigating the GUI extremely difficult and overwhelming to new users.
Finding specific features is a chore as the headings have subheadings not particularly related to the heading.
Each setting is far too configurable with many of the settings not having a use case or explanation.
Some settings which are extremely useful are only able to enable by keybinds.
Switching your cursor to be able to draw with the "pencil" mode is only possible by pressing N, which is only shown to the user when they hover over the paintbrush icon.
The app does come with a help section, however, I believe that it should be possible to create a program which is intuitive and does not require a help tab.
The program is not lightweight, it takes several seconds to a minute to start up as well as not running particularly well.
Below is a screenshot of the program.

![gimpscreenshot.png](repo/gimpscreenshot.png)

### Microsoft Paint
Microsoft's Paint program is the opposite end of the spectrum.
Paint is lightweight, launching without a noticable delay and features a very user friendly GUI.
It allows the user to draw on, crop, scale and rotate the image.
The GUI is very simple and easy to navigate, not requiring any help.
Paint is much less customizable than GIMP, features usually do not have many settings.
For example, when scaling an image, antialiasing is forcefully enabled, which can cause images to look blury.
Complex effects such as blurring are not a feature, and oddly opacity is applied seperately to the colour picker.
The GUI only features buttons at the top, which is compact and means you do not need to search through seperate GUI's to find the setting you are searching for.
Below is a screenshot of the program.

![mspaint11.png](repo/mspaint11.png)

## Problem Solution
The research done prior has informed me of the possible pitfalls of my project.
GIMP struggles with user-friendliness due to its verbosity, having buttons not described and common features hidden from easy access.
Paint struggles with the opposite, all the features are easily accessible, however, it allows very little to be done, making some simple image manipulation hard or impossible.
The layout of Paint is very nice, however, I believe it is still bloated compared to the number of features provided. GIMP has many useful features which, while complicated at first, are required for a useful program.
I believe that GIMP’s idea of each button having sub-buttons is a good idea for the compactness of the GUI.
I believe the optional sub-settings should be accessible but not forced to be configured on each use.
For my solution to thrive, I will need to narrow down essential features.

### Language choice
I will be using Java as my language of choice.
Java is an object-oriented language, allowing for easier maintainability and reusability of code.
It is compiled, which allows for faster execution as code optimisation can be performed at compile time.
The language is also cross-platform as the compiled java bytecode is interpreted to machine code by the Java Virtual Machine (JVM).
This means that the program can be written once and run on any operating system which supports the JVM.
It comes with a large standard library, which allows for a wide range of features.
This includes JavaFX, which is a framework which allows the user to create a GUI in a simple and intuitive way.

### Problem Limitations
The program is made with many different stakeholders' ideals at heart. 
They can each have conflicting desires, which makes it difficult to satisfy everyone.
Some users require a much wider range of specified features, which either are not essential or could take too much time. 
Since the program is being developed alone, time is a large limitation. 
Abstraction must be used to cull unnecessary features, and complex effects may be outside the timescale or outside my skill set. 
Image manipulation is a new field of research for me, which means that I will need to learn the basics for this project, which is another factor which takes time away.

### Success Criteria
|   Criteria    | Description                                                             |
|:-------------:|-------------------------------------------------------------------------|
| User Friendly | The user should be able to use the program without any difficulty.      |
| Customisable  | The user should be able to configure the program to fit their use case. |
|   Efficient   | The program should be able to perform well without powerful hardware    |
|   Reliable    | The program should be able to perform well under extreme conditions.    |

# Design of the Solution
## Decomposition
<!--Break down the problem into smaller parts suitable for computational solutions justifying any decisions made.-->
The program will be structured into subproblems which will be solved in turn.
- GUI
  - Initialising
  - Toolbar
  - Canvas
  - Transforming the canvas
    - Zooming in/out
    - Moving the canvas
- Handling Inputs
  - Clicking on elements of the GUI
    - Appropriately changing the cursor's position relative to transformations of canvas
  - Keyboard input
    - CTRL+Z / CTRL+Y
    - CTRL+C / CTRL+V
    - CTRL+A
    - CTRL+S
- Manipulating Images
  - Scaling
    - Cropping
    - Rotating
    - Drawing
- Saving Images

## Solution Description
### Justification of Structure
<!--Explain and justify the structure of the solution.-->
To come to this conclusion, I had to use computational thinking.
Abstraction, Decomposition and Problem Recognition are all computational methods.
#### Abstraction
Abstraction is the process of hiding the complexity of a problem by simplifying it.
This is done by removing unnecessary details from the problem and replacing them with simpler, more abstract terms.
For example, this program does not need to worry about the difficulties of creating a GUI from scratch, as JavaFX can handle this for us.

#### Decomposition
This program has been divided into subproblems which will be solved in turn, this is known as decomposition.
Each element can be solved independently, and the program can be built up from these subproblems.
It makes sense to tackle these subproblems from their smallest parts and then build up the program from there.

#### Problem Recognition
Each subproblem can be recognisable as a problem, which has previously been solved before.
Problem recognition can clarify the problem and how it is possible to be solved by computational methods.
It allows me to compare new problems to previously solved problems and see how they relate to each other and saves me from reinventing the wheel.
For example, Java comes packaged with a data type which can store an image, which saves me making my own image class.

<!--Describe the parts of the solution using algorithms justifying how these algorithms form a complete solution to the problem.-->
#### GUI
Creating the window is simply handled by the JavaFX framework.
An object with the JFrame class is created, and within the main function its "setVisible" method is called to display a blank GUI.
A size needs to be set, as it defaults to 0x0.
The window is centered on the screen by settings its position relative to null, it defaults to the top left of the screen.
The close operation needs to be set, which tells the program to end when the GUI is closed.
```java 
private static final JFrame FRAME = new JFrame("window title");

public static void main(String[] args) {
    FRAME.setSize(1000, 1000);
    FRAME.setLocationRelativeTo(null);
    FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    FRAME.setVisible(true);
}
```
Drawing to the window is more challenging.
The frame can be directly drawn to via its graphics library.
However, drawing directly to the screen can cause flickering between frames, which is undesirable.
The solution is to draw to create a buffer of the window and draw to the buffer.
When the buffer is ready to be displayed, the buffer is drawn to the screen.
The below code is an example code for drawing a moving rectangle to the screen.
```java
private static BufferStrategy getBufferStrategy() {
    FRAME.createBufferStrategy(2);
    return FRAME.getBufferStrategy();
}
private static Graphics2D getGraphics() {
    return (Graphics2D) getBufferStrategy().getDrawGraphics();
}

private static void drawLoop() {
    while (true) {
        getGraphics().drawRect((int) System.currentTimeMillis() % (FRAME.getWidth()-100), (int) System.currentTimeMillis() % (FRAME.getHeight()-100), 100, 100);
        getBufferStrategy().show();
    }
}
```
With just these steps, we can now already draw all the GUI needed with relative ease.
I believe it makes most sense to draw the canvas first, as it should be drawn below other parts of the interface, drawing it first allows for us to draw over it later.
Inputs should be able to modify the canvas, so those transformations should be undone before rendering other UI elements.
The toolbar can then be drawn on top of the canvas.
Below is an example code to draw a toolbar and a canvas.
```java
public static void drawUI() {
    drawCanvas();
    drawToolbar();
}

private static void drawCanvas() {
    Graphics2D g = getGraphics();

    // save the default transform so we can restore it later
    AffineTransform defaultTransform = g.getTransform();
    
    // move the canvas to where it should be drawn, these variables can be modified by the user
    g.translate(CANVAS_X, CANVAS_Y);
    g.scale(CANVAS_SCALE_X, CANVAS_SCALE_Y);

    // draw 500x500 rect, it will be scaled and translated to the correct position by the library.
    g.drawRect(0, 0, 500, 500);
    
    // restore the default transform
    g.setTransform(defaultTransform);
}

private static void drawToolbar() {
    // draws a rectangle which is 100 pixels high and fills the entire width of the frame
    getGraphics().drawRect(0, 0, FRAME.getWidth(), 100);
}
```

#### Handling Inputs
Inputs to the GUI can be easily handled by the JavaFX framework.
When the GUI is created, append a listener for the mouse/keyboard events.
This is done by creating a class which extends the MouseListener/KeyListener interfaces.
The listener will then be called when the event occurs.
Below is an example of a listener which prints the coordinates of the mouse when it is clicked.
```java
public class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
    }
}
```
The mouse listener will need to keep track of the current state of the UI.
For the keyboard listener, keys such as CTRL+Z will need to be fed a previous state of the UI to restore.
CTRL+V will need to access the clipboard, and CTRL+A will need to select the image.
Each of these keybinds needs access to the state of the canvas, so the canvas state should be a global variable.

#### Manipulating Images
Manipulating images can be done by using the Java ImageIO library.
This library allows for the reading and writing of images in a variety of formats.
Reading images is required for editing pre-existing images, and writing images is required for saving images.
The library also allows for the manipulation of images, such as drawing, scaling, cropping, rotating and drawing.
Below is an example of how to draw to an image. The image fed to the function will have a rectangle drawn to it.
```java
public static void drawRectToImage(BufferedImage image, int x, int y, int width, int height) {
    Graphics2D g = image.createGraphics();
    g.setColor(Color.RED);
    g.fillRect(x, y, width, height);
    g.dispose();
}
```
The following code can then save the resulting image to a file.
```java
public static void saveImage(BufferedImage image) throws IOException {
    ImageIO.write(image, "png", new File("image.png"));
}
```

With just these small steps, it is already possible to create a UI, handle an input, manipulate an image and output the modified image.

<!--TODO: Describe usability features to be included in the solution-->

## Key Variables
<!--Identify key variables / data structures / classes justifying choices and any necessary validation.-->
| Variable        | Type                            | Description                                                        |
|:----------------|:--------------------------------|:-------------------------------------------------------------------|
| `FRAME`         | `JFrame`                        | The frame which is drawn to                                        |
| `MouseListener` | `class extending MouseListener` | Class listening for and responding to mouse inputs                 |
| `KeyListener`   | `class extending KeyListener`   | Class listening for and responding to key inputs                   |
| `g`             | `Graphics2D`                    | The graphics context of the canvas, allows us to draw to the image |
| `CANVAS_IMAGE`  | `BufferedImage`                 | The image being manipulated.                                       |
| `CANVAS_X`      | `int`                           | The x position of the canvas.                                      |
| `CANVAS_Y`      | `int`                           | The y position of the canvas.                                      |
| `CANVAS_SCALE`  | `double`                        | The scale of the canvas.                                           |

## Testing
<!--Identify the test data to be used during the iterative development and post-development phases and justify the choice of this test data.-->
| Test Data                     | Reason                                                                          |
|:------------------------------|:--------------------------------------------------------------------------------|
| Large Image                   | Test RAM usage and performance of the program                                   |
| Small Image                   | Test the program's ability to handle scaling small images to be drawable on     |
| CTRL+Z with no undo history   | Tests if the program handles the history being a null pointer                   |
| Clicking outside image bounds | Tests if the program correctly handles the mouse being outside the image bounds |
| CTRL+V with invalid clipboard | Tests if the program handles the clipboard being a null pointer                 |

# Development
## Iterative Development Process
### GUI
The first stage of development is creating a GUI which I can draw to.
In Main.java I created a global constant which of the GUI 
```java
private static final JFrame FRAME = new JFrame();
```
When the program is run, I initalise the GUI with the "initialiseGUI" function shown below
```java
private static void initialiseGUI() {
    // frame size
    FRAME.setSize(1000, 500);
    // centres frame on screen
    FRAME.setLocationRelativeTo(null);
    // closes the program when the window is closed
    FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the frame visible
    FRAME.setVisible(true);
    // create a frame buffer, which will be drawn to as opposed to drawing directly to the screen
    FRAME.createBufferStrategy(2);
}
```
Then I call the main draw loop. The code below draws a moving rectangle to the screen at 60 frames per second.
```java
// constant value for time between frames being rendered
private static final int FRAME_TIME_GAP = 50;
// variable to save the time the last frame was drawn at
private static long lastFrameMS = 0;

private static void drawLoop() {
    BufferStrategy bufferStrategy = FRAME.getBufferStrategy();
    
    while (true) {
        // drawing the program with a frame cap makes the program run smoother than attempting to draw every frame
        if (System.currentTimeMillis() - lastFrameMS < FRAME_TIME_GAP) continue;
        // create graphics
        g = (Graphics2D) bufferStrategy.getDrawGraphics();
        
        // reset screen
        g.clearRect(0, 0, FRAME.getWidth(), FRAME.getHeight());
        // filler rendering, to test if it is working
        g.fillRect((int) System.currentTimeMillis() % (FRAME.getWidth()-100), (int) System.currentTimeMillis() % (FRAME.getHeight()-100), 100, 100);
        
        // the drawing is finished, display the buffer
        bufferStrategy.show();
        // dispose of graphics from this buffer to free up its memory
        g.dispose();
        
        // save time the frame is drawn at
        lastFrameMS = System.currentTimeMillis();
    }
}
```
This leaves us with a GUI which looks like this.
![guiCreated.png](repo/guiCreated.png)

### Canvas
To draw a canvas, we create a BufferedImage.
```java
public static BufferedImage CANVAS_IMAGE = new BufferedImage(FRAME.getWidth(), FRAME.getHeight(), BufferedImage.TYPE_INT_ARGB);
```
We can then draw this image within the draw loop.
```java
private static void drawLoop() {
    // ...
    g.drawImage(CANVAS_IMAGE, 0, 0, null);
    // ...
}
```
For future use, I have created variables which control where the canvas is drawn at.
```java
public static int CANVAS_X, CANVAS_Y;
public static double CANVAS_SCALE = 1;

public static void drawCanvas() {
    // store previous transformations
    AffineTransform currentTransform = g.getTransform();
    g.translate(CANVAS_X, CANVAS_Y);
    g.scale(CANVAS_SCALE, CANVAS_SCALE);
    
    // draw canvas
    g.drawImage(CANVAS_IMAGE, 0, 0, null);

    // restore previous transformations
    g.setTransform(currentTransform);
}
```
Now the GUI will display the drawing held in the BufferedImage CANVAS_IMAGE every 50 milliseconds.
The image cannot yet be edited, however.

## Testing to Inform Development
[annotated evidence for testing!? and show "remedial" actions taken (how fancy)]

# Evaluation
## Testing to Inform Evaluation
### Functional Testing
[the word "robust" is really cool]

### Usability Testing
[user feedback]

## Success of the Solution
[wowow reference back to](#success-criteria)

## Description of the Final Product
[annotated picture, show the effectiveness of usability features]

## Maintenance and Development
### Maintainability

### Potential Extensions