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
- They will require a simple GUI which is straightforward to navigate on first use
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
### Prototype 1

#### GUI
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

#### Canvas
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

#### Mouse Inputs
For the program to listen for the mouse dragging across the screen, the JFrame requires a MouseMotionListener.
```java
private static void initialiseGUI() {
    // ...
    
    FRAME.addMouseMotionListener(new MouseMotionListener());
}
```
The MouseMotionListener class is shown below. When the mouse is dragged, the pixel at the mouse's position is set to red.
```java
public class MouseMotionListener extends MouseAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {
        Canvas.IMAGE_GRAPHICS.setColor(Color.RED);
        Canvas.IMAGE_GRAPHICS.fillRect(e.getX(), e.getY(), 1, 1);
    }
}
```
This works fine when the mouse is dragged slowly, as shown below:

![mouseDraggedSlowly.png](repo/mouseDraggedSlowly.png)

However, when the mouse is moved quickly, there are clear gaps:

![clearGaps.png](repo/clearGaps.png)

This happens because the mouseDragged MouseEvent is not called for each pixel the mouse has moved.
The event is called when a change is detected in the mouse movement.
With a high DPI mouse, it is possible to move the mouse more than a pixel before the listeners detects the movement, causing the holes.
To fix this issue, I store the position of the previous mouse movement, and draw a line from the previous position to the current position.
```java
private static int lastX, lastY;
public void mouseDragged(MouseEvent e) {
    // ...
    lastX = e.getX(); lastY = e.getY();
}
```
This fixes the issue, and the line is now drawn smoothly.

![smoothLine.png](repo/smoothLine.png)

However, the lastX and lastY variables are not unset when the mouse is released.
This means that upon next pressing the mouse, the line interpolates from the previous.
This is also most visible as lastX and lastY default to 0, causing the first line to interpolate from 0,0 as shown below.

![lineConnectsFrom00.png](repo/lineConnectsFrom00.png)

To fix this, we need to know if this is the first frame of the mouse being pressed, and if it is, do not interpolate it.
This requires knowing when the mouse is released and setting the values to -1,-1 to indicate that they should not interpolate.
To do this, I created a MouseListener which notifies the program when the mouse is released, as shown below.
```java
public class MouseListener extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        MouseMotionListener.setLastMouseDragX(-1);
        MouseMotionListener.setLastMouseDragY(-1);
    }
}
```
Then in the MouseMotionListener, I use encapsulation to allow MouseListener to access the private lastX and lastY variables.
The variables have been appropriately renamed due to their scope increasing, requiring more specific names.
I also changed their default values to -1, -1 to indicate that they should not interpolate.
```java
@Setter private static int lastMouseDragX = -1, lastMouseDragY = -1;
```
I then handle drawing the line as shown below
```java
public void mouseDragged(MouseEvent e) {
    // ...
    Canvas.IMAGE_GRAPHICS.drawLine(
            // if lastMouseDragX and lastMouseDragY are -1, then this is the first frame of the mouse being pressed
            lastMouseDragX == -1 ? e.getX() : lastMouseDragX,
            lastMouseDragY == -1 ? e.getY() : lastMouseDragY,
            e.getX(),
            e.getY()
    );
    // ...
}
```
This allows for the mouse to draw smooth lines to a canvas, as shown below.

![smileyFace.png](repo/smileyFace.png)

This concludes my first prototype as I believe this is a good starting point, but some systems already need to be improved upon.

### Prototype 2
Beginning this prototype, the GUI initialisation code is fine for now, and so is the idea of the canvas.
However, significant changes will be made to the input handling through this prototype.
Object-orientated programming is perfect for a program like this.

#### Element Class
Instead of the Canvas class being its own stand-alone class, it should extend a parent Element class.
The element class will hold all GUI elements which need to be drawn and clicked.
With this in mind, the element class is shown below:
```java
public abstract class Element {
    // static list of elements, to be used for handling each element
    public static ArrayList<Element> elements = new ArrayList<>();

    public int x, y, width, height;

    /// lower priority means it is drawn first and handled last
    public int priority;

    public Element(int x, int y, int width, int height, int priority) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.priority = priority;

        // when the element is initialised, it is added to the list for other parts of the program to handle it.
        elements.add(this);
        elements.sort(Comparator.comparingInt(a -> a.priority));
    }

    public abstract void draw(Graphics2D g);

    /// return true if it wishes to block the click from registering to others
    public abstract boolean handleClick(MouseEvent g);
    public abstract boolean handleDrag(MouseEvent g);
    public abstract boolean handleHover(MouseEvent g);
}
```
I later decided to remove the priority variable.
This is because I believe it is more readable to have a handwritten list of each element in order, as opposed to allowing each to define its own priority.
Canvas is changed to extend Element.
The draw function was already implemented, so I just need to implement the handleClick, handleDrag and handleHover functions.
The handleDrag function for the canvas is currently handled by the MouseMotionListener, so I moved its code to the Canvas class.
This makes the Canvas class change to below:
```java
public class Canvas extends Element {
    private static final int DEFAULT_CANVAS_WIDTH = 500, DEFAULT_CANVAS_HEIGHT = 500;

    public static BufferedImage CANVAS_IMAGE = new BufferedImage(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();

    public Canvas() {
        // priority 0 means it is drawn first
        super(0, 0, DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, 0);
    }

    @Override
    public void draw(Graphics2D g) {
        g.scale((double) this.width / CANVAS_IMAGE.getWidth(), (double) this.height / CANVAS_IMAGE.getHeight());

        g.drawRect(0, 0, CANVAS_IMAGE.getWidth(), CANVAS_IMAGE.getHeight());
        g.drawImage(CANVAS_IMAGE, 0, 0, null);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return false;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        if (MouseMotionListener.lastMouseDragX == -1) return false;

        Canvas.IMAGE_GRAPHICS.setColor(Color.RED);
        Canvas.IMAGE_GRAPHICS.drawLine(MouseMotionListener.lastMouseDragX, MouseMotionListener.lastMouseDragY, e.getX(), e.getY());

        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        return false;
    }

}
```
Since lastMouseDragX and lastMouseDragY from MouseMotionListener are both now needed in Canvas, it doesn't make sense to encapsulate them any more.
lastMouseDragX and lastMouseDragY are made public, which changes MouseListener's mouseReleased function to:
```java
public void mouseReleased(MouseEvent e) {
    MouseMotionListener.lastMouseDragX = MouseMotionListener.lastMouseDragY = -1;
}
```
With these changes, we now need to call each Element function in their correct places.
The draw function is called within Main.java's drawLoop function, shown below:
```java
private static void drawLoop() {
    // ...

    // draw each element
    Canvas.elements.forEach(element -> {
        AffineTransform currentTransform = FRAME_GRAPHICS.getTransform();
        FRAME_GRAPHICS.translate(element.x, element.y);

        element.draw(FRAME_GRAPHICS);

        FRAME_GRAPHICS.setTransform(currentTransform);
    });
    
    // ..
}
```
And the handleDrag is handled by MouseMotionListener, as shown below:
```java
@Override
public void mouseDragged(MouseEvent e) {
    for (Element element : Element.elements.reversed()) {
        // breaks out of the loop if the element wants to stop other elements from handling the drag event
        if (element.handleDrag(e)) break;
    }

    lastMouseDragX = e.getX();
    lastMouseDragY = e.getY();
}
```
The other listeners were not previously implemented, so I will need to create new listeners for them.
The handleHovered event is handled in the MouseMotionListener as shown below:
```java
@Override
public void mouseDragged(MouseEvent e) {
    // ...
    handleMouseMoveEvent(e);
    // ...
}

@Override
public void mouseMoved(MouseEvent e) {
    handleMouseMoveEvent(e);
}

private boolean isElementHovered(MouseEvent e, Element element) {
    return e.getX() >= element.x && e.getX() <= element.x + element.width && e.getY() >= element.y && e.getY() <= element.y + element.height;
}

private void handleMouseMoveEvent(MouseEvent e) {
    // reset the cursor to the default cursor, the element can then handle if it wants to change the cursor to something else
    Main.FRAME.setCursor(Cursor.getDefaultCursor());
    
    for (Element element : Element.elements.reversed()) {
        // breaks out of the loop if the element wants to stop other elements from handling the hover event
        if (isElementHovered(e, element) && element.handleHover(e)) break;
    }
}
```
While writing the mouseClicked event, I realised that it makes sense to set the dragging element in there.
I changed the handleDrag event to be posted as so:
```java
@Override
public void mouseDragged(MouseEvent e) {
    if (CURRENTLY_DRAGGING_ELEMENT != null) {
        CURRENTLY_DRAGGING_ELEMENT.handleDrag(e);
        CURRENTLY_DRAGGING_ELEMENT.handleHover(e);
    }

    lastMouseDragX = e.getX();
    lastMouseDragY = e.getY();
}
```
Where CURRENTLY_DRAGGING_ELEMENT is set inside MouseListener's mousePressed function, shown below:
```java
@Override
public void mousePressed(MouseEvent e) {
    boolean setDraggingElement = false;
    for (Element element : Main.ELEMENTS.reversed()) {
        // breaks out of the loop if the element currently hovered wants to stop other elements from handling the click event
        if (MouseUtil.isMouseHovering(e, element)) {
            if (!setDraggingElement) {
                MouseMotionListener.CURRENTLY_DRAGGING_ELEMENT = element;
                setDraggingElement = true;
            }
            if (element.handleClick(e)) break;
        }
    }
}
```
With all these changes made to the Canvas class, the functionality of the program is the same, with the code now much more robust.

#### Resizing the canvas
To test the new Element class, I decided to make the Canvas resizable.
With the Element clas it was made incredibly simple to change the mouse cursor and change the size of the canvas.
From what could have been 100+ lines of code, to what is shown below:
```java
public class ResizeCanvasButton extends Element {
    public ResizeCanvasButton() {
        super(495, 495, 10, 10);
    }

    @Override
    public void draw(Graphics2D g) {
        updatePosition();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    @Override
    public boolean handleClick(MouseEvent e) {
        return false;
    }

    @Override
    public boolean handleDrag(MouseEvent e) {
        Main.CANVAS.resizeCanvas(e.getX(), e.getY());
        updatePosition();
        return true;
    }

    @Override
    public boolean handleHover(MouseEvent e) {
        Main.FRAME.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        return true;
    }

    private void updatePosition() {
        this.x = Canvas.CANVAS_IMAGE.getWidth() - (this.width / 2);
        this.y = Canvas.CANVAS_IMAGE.getHeight() - (this.height  / 2);
    }
}
```
Canvas.resizeCanvas was created too which is shown below:
```java
public void resizeCanvas(int width, int height) {
    // store old image so it can be redrawn to the new resized image.
    BufferedImage oldImage = CANVAS_IMAGE;
    IMAGE_GRAPHICS.dispose();

    CANVAS_IMAGE = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    IMAGE_GRAPHICS = (Graphics2D) CANVAS_IMAGE.getGraphics();
    IMAGE_GRAPHICS.drawImage(oldImage, 0, 0, null);
    this.width = width;
    this.height = height;
}
```
Below is a screenshot of me hovering the resize button.
![resizeButtonWow.png](repo/resizeButtonWow.png)

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