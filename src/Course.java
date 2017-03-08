
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Course implements Serializable{
    
    //studentNames hods the permanent names of the students 
    private ArrayList studentNames;
    
    //questionList will copy studentNames each time the instructor goes through
    //the whole list. Each time an instructor asks a question, the student selected
    //will be removed from this list.
    private Queue questionList;
    
    private String courseName;
    
    //custructor
    public Course(){
    
        studentNames = new ArrayList();
        
        questionList = new LinkedList();
        
        courseName = null;
    
    }//End of constructor
    
    //--------------------------------------------------------------------------
    
    //newCourse method allows the user to create a class from a text file of names
    public void newCourse(File fileLocation, String courseName){
    
        this.courseName = courseName;
        
        try{
        
            File file = new File(this.courseName + ".dat");
        
            if(!file.exists()){
        
                file.createNewFile();
        
            }
        
            String newLine = null;
        
            String[] name = new String[2];
        
            FileReader fileReader = new FileReader(fileLocation);
            
            BufferedReader input = new BufferedReader(fileReader);
            
            while((newLine = input.readLine()) != null){
            
                newLine = newLine.replace(",", "");
                
                name = newLine.split(" ");
                
                studentNames.add(name[1] + " " + name[0]);
            
            }
    
        }catch(FileNotFoundException ex){
        
            System.err.println("File not found.");
        
        }catch(IOException ex){
        
            System.err.println("An error occurred whilst reading from the file.");
        
        }
        
        
      
    }//End of newCourse method
    
    //--------------------------------------------------------------------------
    
    //saveCourse method allows users to save a course that that previously created.
    public void saveCourse(){
                
        try{
            
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(new File(this.courseName + ".dat")));
            
            objectOut.writeObject(this);
            
            objectOut.close();
                    
        }catch(FileNotFoundException ex){
            
            System.err.println("An error occurred while writing the file.");
            System.err.println("Caught FileNotFoundException: " + ex.getMessage());
        
        }catch(IOException ex){
            
            System.err.println("An error occurred while writing the file.");
            System.err.println("Caught IOException: " + ex.getMessage());
        
        }
    
    }//End of saveCourse method
    
    //--------------------------------------------------------------------------
    
    //loadCourse method allows users to load a previously saved course 
    public void outputNames(File loadFile){
    
        for(int x = 0;x < this.studentNames.size();x++){
        
            System.out.println(this.studentNames.get(x));
        
        }
    
    }//End of outputNames
    
    //--------------------------------------------------------------------------
    
    //loadCourse method allows users to load a previously saved course
    public Course loadCourse(String absolutePath){
    
        Course courseTemp = new Course();
        
        //Read in from file
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(absolutePath));){
           
            courseTemp = (Course)objectIn.readObject();
        
        }catch(IOException ex){
        
            ex.printStackTrace();
                
        }catch(ClassNotFoundException ex){
        
            ex.printStackTrace();
        
        }
        //Done reading from file
        
        return courseTemp;
        
    }//End of loadCourse
    
    //isEmpty checks if the 
    
}
