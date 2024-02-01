package SUBEKCHHYA77356700;

/* 
 * Written By: SUBEKCHHYA BASHYAL
 * STUDENT ID : 77356700
 */


import uk.ac.leedsbeckett.oop.LBUGraphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MainClass extends LBUGraphics
{

    public static void main(String[] args) 
    {
        new MainClass();
        
    }
    
    
    
 // REQUIREMENT 1
    
    
    
    public MainClass()
    {
    
        JFrame MainFrame = new JFrame("SUBEKCHHYA - Turtle Experiment");        //creating frame for display the turtle panel 
        MainFrame.setLayout(new FlowLayout());  
        MainFrame.add(this);                    //"this" - object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        MainFrame.pack();                       //setting the frame size
        MainFrame.setVisible(true);   
        displayMessage("OOP ASSESTMENT L4 SEM:2");
        
        //about();  (calling about )
        
        
    }
    
    
//REQUIREMENT 2
    
   
    
    String command_file ="command.txt";

    
    // Method named processCommand takes a string parameter
     
    
    @Override
    public void processCommand(String s) 
    {

        if (!s.equals("Load Commands")) 
        {
            try (FileWriter file = new FileWriter(command_file, true))
            {
                file.write(s + System.lineSeparator());
            } 
            catch (Exception e) 
            {
                System.out.println("file not found. ERROR!"  + e);
                return;
            }
        }
        
    // commands description
        
        
        
        if (s.equals("penup"))
        {
            pen_up();
            return;
        }
        
        
        if (s.equals("pendown")) 
        {
            pen_down();
            return;
        }
        
        

            if(s.equals("about"))
            {
                about();
                return;
            }

            
            if (s.equals("reset"))
            {
                hard_reset();
                return;
            }

            
            if (s.equals("clear"))
            {
                clear_screen();
                return;
            }

          
            if (!s.equals(""))
            {

                if (s.equals("exit"))
                {
                    boolean val = check_save();
                    if (val){
                        System.exit(1);
                    }
                }
                
           

            if (s.equals("black"))
            {
                pen_colour(Color.black);
                return;
            }

            
            if (s.equals("green"))
            {
                pen_colour(Color.green);
                return;
            }
            

            if (s.equals("red")) 
            {
                pen_colour(Color.red);
                return;
            }

            
            if (s.equals("white")) 
            {
                pen_colour(Color.white);
                return;
            }
            

            if(s.equals("Load Commands"))
            {
                load_command();
                return;
            }
            

            String[] lst1 = s.split(" ");
            String cmd = lst1[0];

            if (cmd.equals("Save"))
            {
                String name = lst1[1];
                save_image(name);
                return;
            }
            

            if (cmd.equals("Load"))
            {
                String name = lst1[1];
                boolean check = check_save();

                if (!check) {
                    String message = "Do you want to save the image?";
                    int a = JOptionPane.showConfirmDialog(null, message, "Image not saved!", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(a==0)
                    {
                        String save = "Save "+ JOptionPane.showInputDialog("Enter the name for the file : ");
                        processCommand(save);
                        load_image(name);
                        return;
                    }
                    if(a==1)
                    {
                        load_image(name);
                        return;
                    }
                    if(a==2 || a==-1)
                    {
                        return;
                    }
                }
                load_image(name);
                return;
            }
            
            
            
// REQUIREMENT 3
            
        //checking with  valid commands that require  parameters
            
            try {
                String[] lst2 = lst1[1].split(",");
                int p1 = Integer.parseInt(lst2[0]);
                int p2 = Integer.parseInt(lst2[1]);
                int p3 = Integer.parseInt(lst2[2]);

                if (p1 < 0 || p2 < 0 || p3 < 0) {
                    System.out.println("Negative Parameter has been detected. Invalid. Try Again.");
                    return;
                }

                
                switch (cmd) 
                {
                    case "bg"->{
                        set_background(p1,p2,p3);
                    }
                    case "pencolour" -> {
                        pen_colour(p1, p2, p3);
                    }
                    case "triangle" -> {
                        draw_triangle(p1, p2, p3);
                    }
                }
            } 
            catch (Exception ignore1) 
            {
                try {
                    int p1 = Integer.parseInt(lst1[1]);
                    if (p1 < 0) 
                    {
                        System.out.println("Negative Parameter has been detected. Try Again.");
                        return;
                    }
                    
                    
                    switch (cmd)
                    {
                 //  
                        case "circle" -> 
                        {
                           draw_circle(p1);
                        }
                 //
                        case "turnleft" -> 
                        {
                            turn_left(p1);
                        }
                        case "turnright" ->
                        {
                            turn_right(p1);
                        }
                        case "forward" ->
                        {
                            go_forward(p1);
                        }
                        case "backward" -> 
                        {
                            go_backward(p1);
                        }
                        case "penwidth" ->
                        {
                            pen_size(p1);
                        }
                        
                        
                        case "square" -> 
                        {
                            draw_square(p1);
                        }
                        
                        
                        case "triangle" ->
                        {
                            draw_triangle(p1);
                        }
                        default -> System.out.println("Provided command is Invalid. Try Again.");
                    }
                } 
                
                
                catch (Exception ignore2) {
                    if (cmd.equals("pencolour") || cmd.equals("rbg"))
                    {
                        System.out.println("Provided parameter is Invalid  to " + cmd);
                        return;
                    }
            //
                    List<String> data = Arrays.asList("circle", "turnleft", "turnright", "forward", "backward", "triangle");
                    if (data.contains(cmd)) {
                        System.out.println("Parameter is Non-numeric ! Try Again!");
                    } 
            //        
                    else {
                        System.out.println("Provided command is Invalid! Try Again!");
                    }
                }
            }
        } 
        else
        {
            String message = "The input is not Valid! Please try again!";
            JOptionPane.showMessageDialog(null, message, "No Input!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
 
    
//REQUIREMENT 4
    
// saving and loading 
    
    
    	public void save_image(String name)
     
    	{
        BufferedImage bufImg = getBufferedImage();
        File output = new File("Image" + name + ".png");
        try {
            ImageIO.write(bufImg, "png", output);
            JOptionPane.showMessageDialog(null,"Image is Saved!");
        } catch (IOException e) {
            System.out.println("Cannot save the file named " + name + ".png.\nError : " + e);
       }
        
    }
    
    
    	public void load_image(String name) 
    	{
        try {
            File file = new File("images" + name + ".png");
            BufferedImage image = ImageIO.read(file);
            setBufferedImage(image);
            System.out.println("Loading the image : " + name);
        } catch (IOException e) 
        
        {
            System.out.println("Cannot load the file named " + name + ".png.\nError : " + e);
        }
    }
    
    
    public void hard_reset() 
    {
        reset();
        penDown();
        penSize = 1;
        setPenColour(Color.RED);
        System.out.println("Reset Entered! ");
    }

  
   
    public void clear_screen() 
    
    {
        clear();
        System.out.println("Cleared! commmand is entered successfully");
    }

    public void pen_down()
    
    {
        penDown();
        System.out.println("Pen Down ! commmand is entered successfully");
    }

    public void pen_up()
    
    {
        penUp();
        System.out.println("Pen Up ! commmand is entered successfully");
    }
    
    
    public void turn_right(int a) 
    
    {
        turnRight(a);
        System.out.println("Turning Right about" + a + " degree.");
    }
    
    public void turn_left(int a) 
    
    {
        turnLeft(a);
        System.out.println("Turning Left about " + a + " degree.");
    }
    

    public void go_backward(int a)
    
    {
        forward(-a);
        System.out.println("Moving " + a + " pixels backward.");
    }


    public void go_forward(int a)
    
    {
        forward(a);
        System.out.println("Moving " + a + " pixels forward.");
    }

    
    public void load_command()
    {
        String fileName = " command.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                processCommand(line);
            }
        } 
        
        catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    
  // checking  

    public boolean check_save(){
        try (BufferedReader reader = new BufferedReader(new FileReader(command_file))) 
        
        {
            String line;
            int i = 0;
            String[] arr = new String[100];
           
            while ((line = reader.readLine()) != null) 
            {
                arr[i] = line;
                i = i + 1;
            }
            
            
            String val = arr[i - 2];
            int space_index = val.indexOf(" ");

            if (space_index != -1) 
            {
                String save = val.substring(0, space_index);
                if (save.equals("Save"))
                    return true;
            }
        } 
        
        
        catch (IOException e)
        {
            String message="File CANNOT be Opened ! ";
            JOptionPane.showMessageDialog(null,message,"",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    
    
//REQUIREMENT 5    
    
       //1 overriding about command and writing name
    
    	@Override
    	
    public void about() {
        super.about();
        clear();
        penUp();
        penDown();
        setPenColour(Color.red);
    
   /*
    * FOR CREATING MY NAME      
    */
    
 //FOR LETTER "S"

    penUp();
    
    turnRight(90);
    
    setPenColour(Color.cyan);

    forward(300);

    penDown();

    forward(60);

    turnLeft();
    
    forward(60);

    turnLeft();
    
    forward(60);

    turnRight();
    
    forward(60);

    turnRight();
    
    forward(60);

    

  //for letter "U"
    
    
    penUp();
    
    setPenColour(Color.pink);
    
    turnLeft(180);
    
    forward(130);
    
    penDown();
    
    turnRight(270);
    
    forward(120);
    
    forward(-120);
    
    turnLeft(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(120);   
   

        
  //for letter "B"
    
    
    penUp();

    setPenColour(Color.yellow);
  
    turnRight(90);
    
    forward(70);
   
    penDown();
    
    turnRight(90);
    
    forward(120);
    
    turnLeft(90);
    
    forward(60);
    
    turnLeft(90);
    
    forward(60);
    
    turnLeft(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(-60);
    
    turnRight(90);
    
    forward(60);
    
    turnLeft(90);
    
    forward(60);
    
    turnLeft(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    
  
    
  //for letter E
    
    
    penUp();
    
    setPenColour(Color.white);
   
    forward(60);
    
    turnLeft(90);
    
    forward(70);
    
    penDown();
   
    turnRight(180);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    turnRight(180);
    
    forward(60);
   
    turnRight(90);
    
    forward(60);
    
    turnRight(90);
    
    forward(60);
    
    
    

  // for letter "K"
    
    
    penUp();
    
    setPenColour(Color.blue);

    forward(15);
    
    turnRight();
    
    forward(0);
    
    penDown();
    
    forward(120);
    
    forward(-50);
    
    turnLeft(60);
    
    forward(80);
    
    forward(-80);
    
    turnLeft(70);
    
    forward(80);
    
    
 
    
  //for letter "C"
    
    
   penUp();
   setPenColour(Color.orange);

   forward(30);
   
   turnRight(130);
   
   forward(5);
  
   penDown();
   
   turnLeft(90);
   
   forward(60);
   
   turnLeft(180);
   
   forward(60);
   
   turnLeft(90);
   
   forward(120);
   
   turnLeft(90);
   
   forward(60);
   
   

  // FOR LETTER "H"
   
   

    penUp();
    setPenColour(Color.cyan);


    forward(10);

    turnLeft(90);

    penDown();

    forward(120);

    forward(-60);

    turnRight(90);

    forward(60);

    turnLeft(90);

    forward(60);

    forward(-120);
    
    

 // FOR LETTER "H"

    
    penUp();
    setPenColour(Color.green);

    turnRight(90);
    forward(10);

    turnLeft(90);

    penDown();

    forward(120);

    forward(-60);

    turnRight(90);

    forward(60);

    turnLeft(90);

    forward(60);

    forward(-120);
    
    

  //for letter "Y"
    
    
    penUp();
    setPenColour(Color.gray);
    
    turnRight(90);
    
    forward(20);
    
    penDown();
    
    turnLeft(60);
    
    forward(130);
    
    forward(-70);
    
    turnLeft(60);
    
    forward(70);
    
    
    
  //for letter "A"
    

    penUp();

    setPenColour(Color.red);

    turnRight(115);

    forward(90);

    turnRight(120);

    penDown();

    forward(130);

    forward(-60);

    turnLeft(100);

    forward(60);

    turnLeft(125);

    forward(74);

    forward(-125);
      
        
        reset();
    }
    
    
   //2    
    	
    	/*
    	 * drawing Square
    	 */
    
    public void draw_square(int a)
    {
        penUp();
        forward(a / 2);
        penDown();
        turnRight();
        forward(a / 2);
        turnRight();
        forward(a);
        turnRight();
        forward(a);
        turnRight();
        forward(a);
        turnRight();
        forward(a / 2);
        reset();
        System.out.println("Square is CREATED of side :" + a);
    }

   
 
    
  //3 
    
    /*
     * pen color command with 3 parameters
     */
    
    public void pen_colour(Color name) 
    {
        setPenColour(name);
        System.out.println("pen-colour is CHANGED to : " + getPenColour());
    }

    
    public void pen_colour(int a, int b, int c) 
    {
        Color temp_color = new Color(a, b, c);
        setPenColour(temp_color);
        System.out.println("pen-colour is CHANGED to " + getPenColour());
    }
 
  
    
    
  //4
    
    /*
     * pen width command
     */
    
    public void pen_size(int a)
    {
        penSize  = a;
    }

   public void pen_width(int a)
   {
	   int penWidth = a;
   }
    
   
   
   //5
   
   /*
    * Drawing equilateral triangle
    * */
    
    
    public void draw_triangle(int a) 
    {
        int h = (int) ((a * Math.sqrt(3)) / 2);
        
        penUp();
        forward(h / 2);
        penDown();
        turnRight();
        forward(a / 2);
        turnRight(120);
        forward(a);
        turnRight(120);
        forward(a);
        turnRight(120);
        forward(a / 2);
        reset();
        
        System.out.println("Triangle  is CREATED of side : " + a);
    }
    
    

    
    // 6
    
    /*
     * drawing any size triangle
     */
    
    public void draw_triangle(int a, int b, int c)
    {
        double angleA = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2.0 * b * c)));
        double angleB = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2.0 * a * c)));
        double angleC = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)));
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        double h = (2 * area) / a;

        penUp();
        forward((int) (h / 2));
        penDown();
        turnLeft();
        forward(a / 2);
        turnLeft((int) (180 - angleC));
        forward(b);
        turnLeft((int) (180 - angleA));
        forward(c);
        turnLeft((int) (180 - angleB));
        forward(a);
        reset();
        
        System.out.println("Triangle is CREATED of side " + a + ", " + b + " and " + c);
    }
    
    // extra shape circle
    
    public void draw_circle(int a) 
    {
        circle(a);
        System.out.println("Circle is CREATED with radius : " + a  );
    }

    
  

    
    private void penUp(int i) {
		// TODO Auto-generated method stub
		
	}

	public void set_background(int a, int b, int c){
        Color col = new Color(a,b,c);
        setBackground_Col(col);
        clear();
    }
	
	
	
	//REQUIREMENT 6
	

	
	/*additional shape circle 
	 *as creativity i have drawn a circle with necessary command above as per the requirement. To run enter pendown and then circle 90 
  	 *as well as i have added additional commands and colors highlighting my name including the mentioned one in the program above .
  	 *i have added a GUI interface (i.e display box ) 
	 *that works when the filter is empty and pressed OK.
	 *after Save Commands is pressed and may more ...
	 *likewise there are several GUI interface according to the provided requirement and more....
	*/
    
    
    
}
