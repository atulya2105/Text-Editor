import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class SimpleTextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    SimpleTextEditor(){
        //Created the frame
        frame=new JFrame("Simple Text Editor");
        //Created the texted area
        textArea=new JTextArea();
        //Adding the textArea to our Frame
        frame.add(textArea);
        // we are giving size to our frame
        frame.setSize(800,800);

        frame.setVisible(true);
        // Creating Menu bar
        JMenuBar menuBar=new JMenuBar();
        // Created menu that is file menu and Edit menu
        JMenu FileMenu=new JMenu("File Menu");
        JMenu EditMenu=new JMenu("Edit Menu");
        // added these to menubar
        menuBar.add(FileMenu);
        menuBar.add(EditMenu);
        // created menu item for File menu
        JMenuItem Open=new JMenuItem("Open File");
        JMenuItem Save=new JMenuItem("Save File");
        JMenuItem Print=new JMenuItem("Print File");
        JMenuItem New=new JMenuItem("New File");
        Save.addActionListener(this);
        Open.addActionListener(this);
        Print.addActionListener(this);
        New.addActionListener(this);
        // added all the menu item to menu thart is file menu
        FileMenu.add(Open);
        FileMenu.add(Print);
        FileMenu.add(Save);
        FileMenu.add(New);
        // creating the menu item for edit files
        JMenuItem Cut=new JMenuItem("Cut");
        JMenuItem Copy=new JMenuItem("Copy");
        JMenuItem Paste=new JMenuItem("Paste");
        JMenuItem Close=new JMenuItem("Close");
        // adding all menu Items to the edit file
        EditMenu.add(Cut);
        EditMenu.add(Copy);
        EditMenu.add(Paste);
        EditMenu.add(Close);

        //Adding the functionality to  edit menu items
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);
        // adding the menubar to the frame
        frame.setJMenuBar(menuBar);
        frame.show();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SimpleTextEditor simpleTextEditor=new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s=="Cut")
        {
            //This will cut the selected text and pin that part in the storage
            textArea.cut();
        }else if(s=="Copy")
        {
            //this will copy the selected area and pin that part in the storage
            textArea.copy();
        }else if(s=="Paste")
        {
            // this will paste the pin text to the provided area
            textArea.paste();
        }else if(s=="Save File")
        {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer= null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s=="Print File")
        {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if(s=="Open File")
        {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file= new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textArea.setText(s2);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s=="New File")
        {
            textArea.setText("");
        }else if(s=="Close")
        {
            frame.setVisible(false);
        }
    }
}
