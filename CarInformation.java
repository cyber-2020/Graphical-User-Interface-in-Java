/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment3;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;

public class CarInformation extends JFrame implements ActionListener
{
    private JButton    exitButton;
    private JButton    addButton;
    private JButton    removeButton;
    private JButton    showButton;
    private JButton    resetButton;
    private JButton    helpButton;
    private JTextField vinTextField;
    private JTextField makeTextField;
    private JTextField modelTextField;
    private JTextField yearTextField;
    private JTextArea  messageTextArea;
    private JTextArea  showTextArea;
    private JLabel     vinLabel;
    private JLabel     makeLabel;
    private JLabel     modelLabel;
    private JLabel     yearLabel;
    private JLabel     messageLabel;
    private JMenu      file;
    private JMenuItem  item1;
    private JMenuBar   menuBar;
    private String vin;
    private int yearEntered;

    Inventory carmax = new Inventory();
    

     public CarInformation()
    {
        super("Car Information System 1.0" );


        this.setSize(300, 450);
        this.setLayout( new GridLayout(8,2));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        file = new JMenu("File");

        item1 = new  JMenuItem("Reset");
        item1.addActionListener(this);

        file.add(item1);

        menuBar = new JMenuBar();

        menuBar.add(file);
        this.setJMenuBar(menuBar);




        vinLabel = new JLabel("VIN");
        this.add(vinLabel);

        vinTextField = new JTextField(17);
        this.add(vinTextField);


        makeLabel = new JLabel("Make");
        this.add(makeLabel);

        makeTextField = new JTextField(24);
        this.add(makeTextField);


        modelLabel = new JLabel("Model");
        this.add(modelLabel);

        modelTextField = new JTextField(24);
        this.add(modelTextField);


        yearLabel = new JLabel("Year");
        this.add(yearLabel);

        yearTextField = new JTextField(24);
        this.add(yearTextField);


        messageLabel = new JLabel("Message");
        this.add(messageLabel);

        messageTextArea = new JTextArea(30,30);
        this.add(messageTextArea);
        messageTextArea.setLineWrap(true);
        
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        this.add(addButton);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        this.add(removeButton);

        showButton = new JButton("Show");
        showButton.addActionListener(this);
        this.add(showButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        this.add(resetButton);

        helpButton = new JButton("Help");
        helpButton.addActionListener(this);
        this.add(helpButton);

        exitButton = new JButton("Close");
        exitButton.addActionListener(this);
        this.add(exitButton);

        vinTextField.requestFocus();

        
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("Close");
        button.addActionListener(this);

        showTextArea = new JTextArea(10,20);
        showTextArea.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(showTextArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.
                VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.
                HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroller);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setSize(350,300);
        frame.setVisible(false);


        try
        {
            
            
            if( e.getActionCommand().equals("Close") )
            {
                System.exit(0);
            }


            else if( e.getActionCommand().equals("Reset") )
            {
                frame.setVisible(false);
                vinTextField.setText("");
                makeTextField.setText("");
                modelTextField.setText("");
                yearTextField.setText("");
                showTextArea.setText("");
                messageTextArea.setText("");
                vinTextField.requestFocus();
            }


            else if( e.getActionCommand().equals("Add") )
            {
                frame.setVisible(false);
                if( vinTextField.getText().equals(""))
                {
                    vinTextField.requestFocus();
                    throw new Exception("Please enter vin!");
                }
                else if(makeTextField.getText().equals("") )
                {
                    makeTextField.requestFocus();
                    throw new Exception("Please enter make!");
                }
                else if(modelTextField.getText().equals(""))
                {
                    modelTextField.requestFocus();
                    throw new Exception("Please enter model!");
                }
                else if(yearTextField.getText().equals(""))
                {
                    yearTextField.requestFocus();
                    throw new Exception("Please enter year!");
                }
                else
                {
                    vin = vinTextField.getText().toUpperCase();
                    yearEntered = Integer.parseInt(yearTextField.getText());

                    // VINs consist of 17 characters which
                    // do not include the letters I (i), O (o), or Q (q)
                    if( (vin.length()    == 17) && (vin.indexOf("I")==-1 ) &&
                        (vin.indexOf("O")==-1 ) && (vin.indexOf("Q")==-1 ))
                    {
                        vinTextField.setText("");
                        makeTextField.setText("");
                        modelTextField.setText("");
                        yearTextField.setText("");
                        vinTextField.requestFocus();
                        messageTextArea.setText(carmax.addRecord(new Car
                            (vinTextField.getText(),yearEntered,
                            makeTextField.getText(),modelTextField.getText())));
                    }
                    else
                    {
                        throw new Exception("Invalid VIN: " + vin + "!");
                    }
                }
            }


            else if( e.getActionCommand().equals("Remove") )
            {
                frame.setVisible(false);
                if( vinTextField.getText().equals("") )
                {
                    vinTextField.requestFocus();
                    throw new Exception("Please enter vin!");
                }
                else
                {
                    vin = vinTextField.getText().toUpperCase();

                    // VINs consist of 17 characters which
                    // do not include the letters I (i), O (o), or Q (q)
                    if( (vin.length()    == 17) && (vin.indexOf("I")==-1 ) &&
                        (vin.indexOf("O")==-1 ) && (vin.indexOf("Q")==-1 ))
                    {
                        vinTextField.setText("");
                        makeTextField.setText("");
                        modelTextField.setText("");
                        yearTextField.setText("");
                        messageTextArea.setText(carmax.removeRecord(
                                vinTextField.getText()));
                        vinTextField.requestFocus();
                    }
                    else
                    {
                        vinTextField.requestFocus();
                        throw new Exception("Invalid VIN: " + vin + "!");
                    }
                }
            }


            else if( e.getActionCommand().equals("Show") )
            {
                
                if(vinTextField.getText().equals("")&&makeTextField.getText().
                        equals("")&&modelTextField.getText().equals("")&&
                        yearTextField.getText().equals(""))
                {
                    frame.setVisible(true);
                    vinTextField.setText("");
                    makeTextField.setText("");
                    modelTextField.setText("");
                    yearTextField.setText("");
                    messageTextArea.setText("");

                    showTextArea.requestFocus();
                    showTextArea.setText(carmax.showRecords());
                    
                }


                else if(modelTextField.getText().equals(""))
                {
                    modelTextField.requestFocus();
                    throw new Exception("Please enter model!");
                }
                else if(yearTextField.getText().equals(""))
                {
                    yearTextField.requestFocus();
                    throw new Exception("Please enter year!");
                }
                else
                {
                    frame.setVisible(true);
                    yearEntered = Integer.parseInt(yearTextField.getText());
                    vinTextField.setText("");
                    makeTextField.setText("");
                    modelTextField.setText("");
                    yearTextField.setText("");
                    messageTextArea.setText("");
                    
                    showTextArea.requestFocus();
                    showTextArea.setText(carmax.showRecords(yearEntered,
                            modelTextField.getText()));
                }
            }
        

           
            else if( e.getActionCommand().equals("Help") )
            {
                vinTextField.setText("");
                makeTextField.setText("");
                modelTextField.setText("");
                yearTextField.setText("");
                messageTextArea.setText("E-mail oybekee@yahoo.com for help!");
            }
        }

        catch(Exception exp)
        {
            messageTextArea.setText(exp.getMessage());
        }



    }




public class Inventory
{
	// PROBLEM: -3
	private MyArrayList     data;
    //private ArrayList<Car> data;

	// PROBLEM: -3
    public Inventory()
    {
		data = new MyArrayList();
	  //data = new ArrayList<Car>();
    }

   public boolean isExist(String vin)
   {
        if (data.size()>0)
        {
            for(int i=0; i<data.size(); i++)
            {
                if(data.get(i).getVIN().equals(vin))
                {
                    return true;
                }
            }
        }
        else
        {
            return false;
        }
        return false;
   }

    public String addRecord(Car record)
    {
        if ( data.size()<20)
        {
            boolean found = false;
            for(int i=0; i<data.size(); i++)
            {
                if( data.get(i).equals(record) )
                {
                    found = true;
                }
            }

            if( (!found) && (record.getYear()>1980))
            {
                // VIN Check
                data.add(record);
                return "Successfully added!";
            }
            else if (found)
            {
                return "Existing Record: record not added!";
            }
        }
        
            return "Inventory Full:" + record +"!";
        

    }

    public String removeRecord(String vin)
    {
        if (data.size()>0)
        {
            if( isExist(vin))
            {

                for(int i=0; i<data.size(); i++)
                {
                    if( data.get(i).getVIN().equals(vin) )
                    {
                        data.remove(i);
                        return "Car removed successfully!";
                        
                    }
                }
            }
        }
        
        return "No record in the inventory!";
        
     }

    public String showRecords()
    {
        if (data.size()>0 )
        {
            for(int i=0; i<data.size(); i++)
            {
                return "" + data.get(i);
            }
        }
        
        return "No record in the inventory!";
       
    }

    public String showRecords(int year, String model)
    {
        if (data.size()>0 )
        {
            for(int i=0; i<data.size(); i++)
            {
                if( (data.get(i).getYear()==year) && (data.get(i).getModel().
                        equals(model) ) )
                {
                    return data.get(i).toString();
             
                }
            }
        }
        return "No record in the inventory!";
        
    }
}




    // car1 == car2
public class Car extends Object
{
    private String vin;
    private String make;
    private String model;
    private int    year;

    public Car(String vin, int year, String make,String model)
    {

        this.setVIN(vin);
        setMake(make);
        setModel(model);
        setYear(year);


    }

    public String toString()
    {
        int length = make.length();
        String tmpMake  = make;
        String tmpModel = model;

        for(int i=0; i<12-length; i++)
        {
            tmpMake = tmpMake + " ";
        }
        length = model.length();
        for(int i=0; i<20-length; i++)
        {
            tmpModel = tmpModel + " ";
        }
        return  year + " " + tmpMake + " " + tmpModel + " "  + vin;

    }

    // car1.equals(car2)
    public boolean equals(Car otherObject)
    {
        if( this.vin.equals( otherObject.vin) )
            return true;
        else
            return false;
    }

    public boolean equals(Object object)
    {
        if (object instanceof Car)
        {
            Car carObject = (Car) object;
            if (this.vin.equals(carObject.vin))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear()
    {
        return this.year;
    }

    public String getVIN()
    {
        return vin;

    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setYear(int year)
    {
        /* Prior to 1981, there was no accepted standard for VINs,
           so different manufacturers used different formats. */
        if( year > 1980)
        {
            this.year = year;
        }
        else
        {
            System.out.println("Too old car: " + year + "!");
        }
    }

    public void setVIN(String vin)
    {
        this.vin = vin;
    }

}





public class MyArrayList
{

	// IF A SIMILAR VARIABLE DOES NOT EXIST: -3
	// IF PUBLIC -1
	private int numberOfCurrentRecords;
    // IF A SIMILAR VARIABLE DOES NOT EXIST: -3
	// IF PUBLIC -1
    private int databaseCapacity;
    // IF INITIALAZING IS WRONG -3
    public Car[] list;

	// Initialization should be done inside the constructor
	public MyArrayList()
	{
		numberOfCurrentRecords = 0;
		databaseCapacity = 2;
		list = new Car[databaseCapacity];
	}

    //IF THERE IS A PROBLEM: -2
    int size()
	{
        return this.numberOfCurrentRecords;
    }

    //IF THERE IS A PROBLEM: -2
    public Car get(int index)
	{
        return list[index];
    }

    public void add(Car record)
	{
	// ONE ADDITIONAL ARRAY IS ENOUGH TO COPY, IF MORE ARRAYS USED : -5

        if( (numberOfCurrentRecords >= databaseCapacity)  )// NOT EXIST: -3
		{
			// COPY FROM list TO TEMP
			Car[] expansionArray = new Car[databaseCapacity];
			for(int i=0; i<numberOfCurrentRecords; i++)
			{
				expansionArray[i] = list[i];
			}

			databaseCapacity *= 2; 
			list = new Car[databaseCapacity];

			// COPY FROM TEMP to list
            for(int i=0; i<numberOfCurrentRecords; i++)
			{
				list[i] = expansionArray[i];
			}
			expansionArray=null;
        }
		list[numberOfCurrentRecords] = record;
		numberOfCurrentRecords++; //IF NOT EXISTS: -3
    }

    void remove(int index)
	{
		// IF THERE IS A PROBLEM: -4
		for(int i=index; i< numberOfCurrentRecords-1;i++)
		{
			list[i] = list[i+1];
		}
		list[numberOfCurrentRecords-1] = null;
    	//IF NOT EXISTS -3
		numberOfCurrentRecords--;


	}
}








   

}
